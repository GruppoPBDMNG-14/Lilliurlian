package lilliurlian.entities;

public class UrlFromClient {
	private String longUrl;
    private String shortUrl;

    public UrlFromClient(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getCustomUrl() {
        return shortUrl;
    }
    
    public String getShortUrl(){
    	return shortUrl;
    }
}
