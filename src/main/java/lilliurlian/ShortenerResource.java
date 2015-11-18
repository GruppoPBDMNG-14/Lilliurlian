package lilliurlian;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import lilliurlian.entities.UrlFromClient;
import lilliurlian.entities.UrlFromServer;
import lilliurlian.exceptions.CustomUrlExistsException;
import lilliurlian.exceptions.CustomUrlNotValidException;
import lilliurlian.exceptions.PageNotFoundException;
import lilliurlian.exceptions.WrongUrlException;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * This class works as server interface for client requests using Spark methods.
 * 
 * 
 * @author GruppoPDBMNG-14
 *
 */
public class ShortenerResource {
	
    private static final String API_CONTEXT = "/api/v1";
    private static final int CUSTOM_URL_NOT_VALID_RESPONSE_ERROR_CODE = 503;
    private static final int WRONG_URL_RESPONSE_ERROR_CODE = 502;
    private static final int CUSTOM_URL_EXISTS_RESPONSE_ERROR_CODE = 501;
    private static final int SHORT_URL_NOT_FOUND_ERROR_CODE = 504;
    private static final int NEW_URL_RESPONSE_SUCCESS_CODE = 201;
    private static final int GET_STATS_RESPONSE_SUCCESS_CODE = 202;
    private final ShortenerService shortenerService;

    /**
     * Constructs a new listening service for incoming requests, initializing the DAO and setting endpoints.
     * 
     * @param shortenerService Access to DAO is given to let the server manage write/read client requests.
     */
    public ShortenerResource(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
        setupEndpoints();
    }
    
    /**
     * Sets how the server reacts to each specific request. Spark method put and get are used
     * to let client communicate with server.
     */

    private void setupEndpoints() {
        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
        	UrlFromClient returnUrl = null;
        	
        	try{
        		
        		returnUrl = shortenerService.createNewUrl(request.body());
        		response.status(NEW_URL_RESPONSE_SUCCESS_CODE);
            
        	} catch (CustomUrlExistsException e){
        	
        		response.status(CUSTOM_URL_EXISTS_RESPONSE_ERROR_CODE);
        		
        	} catch (WrongUrlException e){
        		
        		response.status(WRONG_URL_RESPONSE_ERROR_CODE);
        	} catch (CustomUrlNotValidException e){
        		
        		response.status(CUSTOM_URL_NOT_VALID_RESPONSE_ERROR_CODE);
        	}
        	
        	return returnUrl;
        	
        }, new JsonTransformer());
        
        get("/:shortUrl", "application/json", (request, response) -> {
        	UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
    		ReadableUserAgent agent = parser.parse(request.headers("User-Agent"));
    		
    		String agentName = agent.getName();
    		String agentOS = agent.getOperatingSystem().getName();
    		String agentIP = request.ip();
    		String shortUrl = request.params(":shortUrl");
        	
        	if(request.params(":shortUrl").equalsIgnoreCase("favicon.ico") == false){
        		try{
        		
        			String longUrlToVisit = shortenerService.searchShortUrl(shortUrl, agentName, agentOS, agentIP);
        			response.redirect("http://" + longUrlToVisit);
        	
        		} catch (PageNotFoundException e){
        		
        			response.redirect("#/404");
        			
        		}
        	}
        	
        	return null;
        });
        
        post(API_CONTEXT + "/stats", "application/json", (request, response) -> {
        	UrlFromServer stats = null;
        	
        	try{
        		
        		stats = shortenerService.getStats(request.body());
        		response.status(GET_STATS_RESPONSE_SUCCESS_CODE);
        	
        	}catch (PageNotFoundException e){
        		
        		response.status(SHORT_URL_NOT_FOUND_ERROR_CODE);
        	}
        	return stats;
        }, new JsonTransformer());       
    }
}
