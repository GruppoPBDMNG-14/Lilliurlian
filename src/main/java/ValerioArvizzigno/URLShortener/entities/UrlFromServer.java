package ValerioArvizzigno.URLShortener.entities;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class UrlFromServer {

    private String longUrl;
    private String shortUrl;
    private Date createdOn;

    //Da aggiungere parametri
    public UrlFromServer(BasicDBObject dbObject) {
       
        this.longUrl = dbObject.getString("longUrl");
        this.shortUrl = dbObject.getString("shortUrl");
        this.createdOn = dbObject.getDate("createdOn");
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

}
