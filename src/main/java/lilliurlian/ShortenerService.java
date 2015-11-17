package lilliurlian;

import com.google.gson.Gson;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.mongodb.*;
import org.apache.commons.validator.routines.UrlValidator;
import java.io.IOException;
import java.util.Date;
import lilliurlian.entities.UrlFromClient;
import lilliurlian.entities.UrlFromServer;
import lilliurlian.exceptions.CustomUrlExistsException;
import lilliurlian.exceptions.CustomUrlNotValidException;
import lilliurlian.exceptions.PageNotFoundException;
import lilliurlian.exceptions.WrongUrlException;
import lilliurlian.utility.BadContentChecker;
import lilliurlian.utility.EmptyStringChecker;
import lilliurlian.utility.IPGeoloc;
import lilliurlian.utility.ShortStringGenerator;
import lilliurlian.utility.SpecialCharFinder;

public class ShortenerService {
	String browserField = "browser.";
	String oSField = "OS.";
	String countryField = "country.";
	String totalClicksField = "totalClicks";
	private static final String UNDEFINED_COUNTRY = "NaN";
	private static final String SHORT_URL = "shortUrl";
	private static final String LONG_URL = "longUrl";
	private static final String IS_CUSTOM = "isCustom";
	private static final String CREATED_ON = "createdOn";
	private final DB db;
    private final DBCollection collection;

    public ShortenerService(DB db) {
        this.db = db;
        this.collection = db.getCollection("urls");
    }

    public UrlFromClient createNewUrl(String body) throws CustomUrlExistsException, 
    WrongUrlException, CustomUrlNotValidException  {
        UrlFromClient url = new Gson().fromJson(body, UrlFromClient.class);
        UrlFromClient returnUrl = null;
        UrlValidator urlValidator = new UrlValidator(){         
        	// allow missing scheme
        	@Override
        	public boolean isValid(String value) {
        		return super.isValid(value) || super.isValid("http://" + value);
        	}
        };
        
        if(urlValidator.isValid(url.getLongUrl())){
        	
        	if (EmptyStringChecker.isBlank(url.getCustomUrl())){
        		ShortStringGenerator gen = new ShortStringGenerator();
        		boolean isShortUrlUnique = false;
        		String shortUrlCreated = null;
        	
        		if(collection.findOne(new BasicDBObject(LONG_URL, url.getLongUrl())
        				.append(IS_CUSTOM, false)) == null ){
        			
        			while(isShortUrlUnique == false){
        				shortUrlCreated = gen.nextString();
        		
        				if(collection.findOne(new BasicDBObject(SHORT_URL, 
        						shortUrlCreated)) == null){
        					isShortUrlUnique = true;
        				}
        			}
        	
        			returnUrl = new UrlFromClient(url.getLongUrl(), shortUrlCreated);
        			
        			collection.insert(new BasicDBObject(LONG_URL, url.getLongUrl())
        					.append(SHORT_URL, shortUrlCreated).append(IS_CUSTOM, false).append(CREATED_ON, new Date()));
        		
        		} else {
        			returnUrl = new UrlFromClient(url.getLongUrl(), 
        					(String) (collection.findOne(new BasicDBObject(LONG_URL, url.getLongUrl())
        							.append(IS_CUSTOM, false))).get(SHORT_URL));
        		}
        	
        	} else {
        		
        		if(SpecialCharFinder.isFound(url.getCustomUrl()) == false && BadContentChecker.check(url.getCustomUrl()) == false ){
        			
        			if(collection.findOne(new BasicDBObject(SHORT_URL, url.getCustomUrl())) == null){
        				returnUrl = new UrlFromClient(url.getLongUrl(), url.getCustomUrl());
        				
        				collection.insert(new BasicDBObject(LONG_URL, url.getLongUrl())
        						.append(SHORT_URL, url.getCustomUrl()).append(IS_CUSTOM, true).append(CREATED_ON, new Date()));
        	
        			}else {
        				throw new CustomUrlExistsException("This custom url already exists");
        			}
        			
        		} else{
        			throw new CustomUrlNotValidException("Special chars or bad content found in custom url");
        		}	
        	}
        	
        } else {
        	throw new WrongUrlException("The long url is not correct");
        }
        
        return returnUrl;
    }

    
    public String searchShortUrl(String shortUrl, String agentName, 
    		String agentOS, String agentIP) throws PageNotFoundException {
    	String country;
    	String ret = null;
    	
    	try{
    		ret = (String) collection.findOne(new BasicDBObject(SHORT_URL, shortUrl)).get(LONG_URL);
    		
    		BasicDBObject newTotalClicks = 
    				new BasicDBObject().append("$inc", 
    				new BasicDBObject().append(totalClicksField, 1));
    		collection.update(new BasicDBObject().append(SHORT_URL, shortUrl), newTotalClicks);
    			
    		BasicDBObject newBrowserClicks = 
        			new BasicDBObject().append("$inc", 
        			new BasicDBObject().append(browserField.concat(agentName), 1));
        	collection.update(new BasicDBObject().append(SHORT_URL, shortUrl), newBrowserClicks);
        	
        	BasicDBObject newOSClicks = 
            		new BasicDBObject().append("$inc", 
           			new BasicDBObject().append(oSField.concat(agentOS.replace(".", "_")), 1)); //Need to replace dots because they are parsed like a nested document
            collection.update(new BasicDBObject().append(SHORT_URL, shortUrl), newOSClicks);	
           	
			try {
				IPGeoloc geo = new IPGeoloc();
				country = geo.getCountryName(agentIP);
				
			} catch (IOException e) {	
				country = UNDEFINED_COUNTRY;
				
			} catch(GeoIp2Exception e){
				country = UNDEFINED_COUNTRY;
				
			}
			
           	BasicDBObject newCountryClicks = 
           			new BasicDBObject().append("$inc", 
           			new BasicDBObject().append(countryField.concat(country), 1));
            collection.update(new BasicDBObject().append(SHORT_URL, shortUrl), newCountryClicks);	
    		
    	} catch (NullPointerException e){	
    		throw new PageNotFoundException("Short url not found");
    		
    	}
    	
        return ret;
    }    
    
    public UrlFromServer getStats(String body) throws PageNotFoundException{
    	UrlFromClient url = new Gson().fromJson(body, UrlFromClient.class);
        UrlFromServer stats = null;
        
        try{
        	
        	BasicDBObject dbObject = (BasicDBObject) collection.findOne(new BasicDBObject(SHORT_URL, url.getShortUrl()));
        
        	stats = new UrlFromServer(dbObject);
        	
        } catch (NullPointerException e){
        	
        	throw new PageNotFoundException("Short url not found");
        }
        
        return stats;
    }
}
