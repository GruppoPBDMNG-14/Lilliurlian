package lilliurlian;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import lilliurlian.entities.UrlFromClient;
import lilliurlian.exceptions.CustomUrlExistsException;
import lilliurlian.exceptions.CustomUrlNotValidException;
import lilliurlian.exceptions.PageNotFoundException;
import lilliurlian.exceptions.WrongUrlException;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class ShortenerResource {

    private static final String API_CONTEXT = "/api/v1";
    private static final int CUSTOM_URL_NOT_VALID_RESPONSE_ERROR_CODE = 503;
    private static final int WRONG_URL_RESPONSE_ERROR_CODE = 502;
    private static final int CUSTOM_URL_EXISTS_RESPONSE_ERROR_CODE = 501;
    private static final int NEW_URL_RESPONSE_SUCCESS_CODE = 201;

    private final ShortenerService shortenerService;

    public ShortenerResource(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
        setupEndpoints();
    }

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
        	
        	System.out.println(request.params("shortUrl"));
        	if(request.params(":shortUrl").equalsIgnoreCase("favicon.ico") == false){
        		try{
        		
        			String longUrlToVisit = shortenerService.searchShortUrl(request.params(":shortUrl"));
        			response.redirect("http://" + longUrlToVisit);
        	
        		} catch (PageNotFoundException e){
        		
        			response.redirect("#/404");
        		}
        	}
        	
        	return null;
        });

                

    }


}
