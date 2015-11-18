package lilliurlian.entities;

import com.mongodb.BasicDBObject;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Entity class for data returned from the server. 
 * An UrlFromServer contains all the infos and stats about a shortUrl.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class UrlFromServer {
    private String longUrl;
    private String shortUrl;
    private Date createdOn;
    private boolean isCustom;
    private long totalClicks;
    private double clicksPerDay;
    private double clicksPerMonth;
    private double clicksPerYear;
    private String clicksPerBrowser;
    private String clicksPerOS;
    private String clicksPerCountry;

    /**
     * Creates a new UrlFromServer object.
     * 
     * @param dbObject The DB object returned after the query.
     */
    public UrlFromServer(BasicDBObject dbObject) {
        this.longUrl = dbObject.getString("longUrl");
        this.shortUrl = dbObject.getString("shortUrl");
        this.createdOn = dbObject.getDate("createdOn");
        this.isCustom = dbObject.getBoolean("isCustom");
        this.totalClicks = dbObject.getLong("totalClicks");
        this.clicksPerDay = clicksPerDayCalculator();
        this.clicksPerMonth = clicksPerMonthCalculator();
        this.clicksPerYear = clicksPerYearCalculator();
        this.clicksPerBrowser = dbObject.get("browser").toString().replace("{", "").replace("}", "").replace("\"", "");
        this.clicksPerOS = dbObject.get("OS").toString().replace("{", "").replace("}", "").replace("\"", "").replace("_", ".");
        this.clicksPerCountry = dbObject.get("country").toString().replace("{", "").replace("}", "").replace("\"", "");
    }

    /**
     * Calculates clicks/day stats.
     * 
     * @return clicks/day number.
     */
    private double clicksPerDayCalculator(){
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerDay;
    	double diffDays = (double) Days.daysBetween(creationDate, now).getDays();
    	
    	if (diffDays < 1)
    		clicksPerDay = totalClicks;
    	else
    		clicksPerDay = totalClicks / diffDays;
    	
    	return Math.round(clicksPerDay * 100d) / 100d;
    }
    
    /**
     * Calculates clicks/month stats.
     * 
     * @return clicks/month number.
     */
    
    private double clicksPerMonthCalculator(){
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerMonth;
    	double diffMonths = ((double) Days.daysBetween(creationDate, now).getDays()) / 30.416;
    	
    	if (diffMonths < 1)
    		clicksPerMonth = totalClicks;
    	else
    		clicksPerMonth = totalClicks / diffMonths;
    	
    	return Math.round(clicksPerMonth * 100d) / 100d;
    }
    
    /**
     * Calculates clicks/year stats.
     * 
     * @return clicks/year number.
     */
    private double clicksPerYearCalculator(){
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerYear;
    	double diffYears = ((double) Days.daysBetween(creationDate, now).getDays()) / 365;
    	
    	if (diffYears < 1)
    		clicksPerYear = totalClicks;
    	else
    		clicksPerYear = totalClicks / diffYears;

    	return Math.round(clicksPerYear * 100d) / 100d;
    }
    
    /**
     * Gets the longUrl.
     * 
     * @return the longUrl.
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Gets the shortUrl.
     * 
     * @return the shortUrl.
     */
    public String getShortUrl() {
        return shortUrl;
    }
    
    /**
     * Gets the creation date.
     * 
     * @return creation date.
     */
    public Date getCreatedOn(){
    	return createdOn;
    }
    
    /**
     * Gets the isCustom flag.
     * 
     * @return true if the shortUrl is custom, false if it is random-generated.
     */
    public boolean getIsCustom(){
    	return isCustom;
    }
    
    /**
     * Gets the number of total clicks.
     * 
     * @return total clicks number.
     */
    public long getTotalClicks(){
    	return totalClicks;
    }
    
    /**
     * Gets the number of clicks/day.
     * 
     * @return clicks/day number.
     */
    public double getClicksPerDay(){
    	
    	return clicksPerDay;
    }
    
    /**
     * Gets the number of clicks/month.
     * 
     * @return clicks/month number.
     */
    public double getClicksPerMonth(){
    	return clicksPerMonth;
    }
    
    /**
     * Gets the number of clicks/year.
     * 
     * @return clicks/year number.
     */
    public double getClicksPerYear(){
    	return clicksPerYear;
    }
    
    /**
     * Gets the number of clicks/browser.
     * 
     * @return clicks/browser number.
     */
    public String getClicksPerBrowser(){
    	return clicksPerBrowser;
    }
    
    /**
     * Gets the number of clicks/OS.
     * 
     * @return clicks/OS number.
     */
    public String getClicksPerOS(){
    	return clicksPerOS;
    }
    
    /**
     * Gets the number of clicks/country. 
     * 
     * @return clicks/country number.
     */
    public String getClicksPerCountry(){
    	return clicksPerCountry;
    }
}
