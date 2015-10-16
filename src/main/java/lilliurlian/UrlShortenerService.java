package lilliurlian;

import com.google.gson.Gson;
import com.mongodb.*;

import org.apache.commons.validator.routines.UrlValidator;
import org.bson.types.ObjectId;

import java.util.Date;

import lilliurlian.entities.UrlFromClient;
import lilliurlian.entities.UrlFromServer;
import lilliurlian.exceptions.CustomUrlExistsException;
import lilliurlian.exceptions.CustomUrlNotValidException;
import lilliurlian.exceptions.WrongUrlException;
import lilliurlian.utility.BadContentChecker;
import lilliurlian.utility.EmptyStringChecker;
import lilliurlian.utility.ShortStringGenerator;
import lilliurlian.utility.SpecialCharFinder;


public class UrlShortenerService {

    private final DB db;
    private final DBCollection collection;

    public UrlShortenerService(DB db) {
        this.db = db;
        this.collection = db.getCollection("urls");
    }

    public UrlFromClient createNewUrl(String body) throws CustomUrlExistsException, WrongUrlException, CustomUrlNotValidException  {
    	
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
        	
        		if(collection.findOne(new BasicDBObject("longUrl", url.getLongUrl()).append("isCustom", false)) == null ){
        	
        			while(isShortUrlUnique == false){
        		
        				shortUrlCreated = gen.nextString();
        		
        				if(collection.findOne(new BasicDBObject("shortUrl", shortUrlCreated)) == null){
        			
        					isShortUrlUnique = true;
        				}
        	
        			}
        	
        			returnUrl = new UrlFromClient(url.getLongUrl(), shortUrlCreated);
        			collection.insert(new BasicDBObject("longUrl", url.getLongUrl()).append("shortUrl", shortUrlCreated)
        					.append("isCustom", false).append("createdOn", new Date()));
        		
        		
        		
        		} else {
        		
        			returnUrl = new UrlFromClient(url.getLongUrl(), (String) (collection.findOne(new BasicDBObject("longUrl", url.getLongUrl())
        				.append("isCustom", false))).get("shortUrl"));
        		
        		}
        	
        	} else {
        		
        		
        		if(SpecialCharFinder.isFound(url.getCustomUrl()) == false && BadContentChecker.check(url.getCustomUrl()) == false ){
        			
        		
        			if(collection.findOne(new BasicDBObject("shortUrl", url.getCustomUrl())) == null){
        		
        				returnUrl = new UrlFromClient(url.getLongUrl(), url.getCustomUrl());
        				collection.insert(new BasicDBObject("longUrl", url.getLongUrl()).append("shortUrl", url.getCustomUrl()).append("isCustom", true).append("createdOn", new Date()));
        	
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

    //dummy
    public UrlFromServer find(String id) {
        return new UrlFromServer((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

}
