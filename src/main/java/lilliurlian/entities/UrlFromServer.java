package lilliurlian.entities;

import com.mongodb.BasicDBObject;

import java.text.DecimalFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

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

    private double clicksPerDayCalculator(){
    	
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerDay;
    	
    	double diffDays = (double) Days.daysBetween(creationDate, now).getDays();
    	
    	if (diffDays < 1)
    		clicksPerDay = totalClicks;
    	else
    		clicksPerDay = totalClicks / diffDays;
    	System.out.println(clicksPerDay + " " + diffDays);
    	return clicksPerDay;
    }
    
    private double clicksPerMonthCalculator(){
    	
    	DecimalFormat df = new DecimalFormat("0.##");
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerMonth;
    	
    	double diffMonths = ((double) Days.daysBetween(creationDate, now).getDays()) / 30.416;
    	
    	if (diffMonths < 1)
    		clicksPerMonth = totalClicks;
    	else
    		clicksPerMonth = totalClicks / diffMonths;
    	
    	System.out.println(clicksPerMonth + " " + diffMonths);
    	return clicksPerMonth;
    }
    
    private double clicksPerYearCalculator(){
    	
    	DecimalFormat df = new DecimalFormat("0.##");
    	DateTime now = new DateTime(new Date());
    	DateTime creationDate = new DateTime(createdOn);
    	double clicksPerYear;

    	double diffYears = ((double) Days.daysBetween(creationDate, now).getDays()) / 365;
    	
    	if (diffYears < 1)
    		clicksPerYear = totalClicks;
    	else
    		clicksPerYear = totalClicks / diffYears;
    	
    	System.out.println(clicksPerYear + " " + diffYears); 
    	
    	return clicksPerYear;
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
    
    public long getTotalClicks(){
    	return totalClicks;
    }
    
    public double getClicksPerDay(){
    	
    	return clicksPerDay;
    }
    
    public double getClicksPerMonth(){
    	return clicksPerMonth;
    }
    
    public double getClicksPerYear(){
    	return clicksPerYear;
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
