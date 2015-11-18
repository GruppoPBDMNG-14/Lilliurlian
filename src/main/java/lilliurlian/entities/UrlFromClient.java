package lilliurlian.entities;

/**
 * Entity class for urls used in the process of creation of a new shortUrl.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class UrlFromClient {
	private String longUrl;
    private String shortUrl;

    /**
     * Creates a new UrlFromClient.
     * 
     * @param longUrl The longUrl to shorten.
     * @param shortUrl The shortUrl associated to the longUrl.
     */
    public UrlFromClient(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
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
     * Gets the shortUrl. Same as getShortUrl() but with different name to specify the URL returned is a custom one.
     * @return the shortUrl.
     */
    public String getCustomUrl() {
        return shortUrl;
    }
    
    /**
     * Gets the shortUrl. Same as getCustomUrl() but with different name to specify the URL returned is a random-generated one.
     * 
     * @return the shortUrl.
     */
    public String getShortUrl(){
    	return shortUrl;
    }
}
