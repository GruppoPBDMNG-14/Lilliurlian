package lilliurlian.entities;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.bson.types.ObjectId;

import java.util.Date;

public class UrlFromServer {

    private String longUrl;
    private String shortUrl;
    private Date createdOn;
    private boolean isCustom;
    private int totalClicks;
    private String clicksPerBrowser;
    private String clicksPerOS;
    private String clicksPerCountry;

    public UrlFromServer(BasicDBObject dbObject) {
       
        this.longUrl = dbObject.getString("longUrl");
        this.shortUrl = dbObject.getString("shortUrl");
        this.createdOn = dbObject.getDate("createdOn");
        this.isCustom = dbObject.getBoolean("isCustom");
        this.totalClicks = dbObject.getInt("totalClicks");
        this.clicksPerBrowser = dbObject.get("browser").toString().replace("{", "").replace("}", "").replace("\"", "");
        this.clicksPerOS = dbObject.get("OS").toString().replace("{", "").replace("}", "").replace("\"", "").replace("_", ".");
        this.clicksPerCountry = dbObject.get("country").toString().replace("{", "").replace("}", "").replace("\"", "");
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
    
    public Date getCreatedOn(){
    	return createdOn;
    }
    
    public boolean getIsCustom(){
    	return isCustom;
    }
    
    public int getTotalClicks(){
    	return totalClicks;
    }
    
    public String getClicksPerBrowser(){
    	return clicksPerBrowser;
    }
    
    public String getClicksPerOS(){
    	return clicksPerOS;
    }
    
    public String getClicksPerCountry(){
    	return clicksPerCountry;
    }

}
