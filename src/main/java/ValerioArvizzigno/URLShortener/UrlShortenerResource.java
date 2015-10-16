package ValerioArvizzigno.URLShortener;

import ValerioArvizzigno.URLShortener.entities.UrlFromClient;
import ValerioArvizzigno.URLShortener.exceptions.CustomUrlExistsException;
import ValerioArvizzigno.URLShortener.exceptions.CustomUrlNotValidException;
import ValerioArvizzigno.URLShortener.exceptions.WrongUrlException;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class UrlShortenerResource {

    private static final String API_CONTEXT = "/api/v1";

    private final UrlShortenerService shortenerService;

    public UrlShortenerResource(UrlShortenerService shortenerService) {
        this.shortenerService = shortenerService;
        setupEndpoints();
    }

    private void setupEndpoints() {
    	
    
    	
        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
        	
        	UrlFromClient returnUrl = null;
        	
        	try{
        		
        		returnUrl = shortenerService.createNewUrl(request.body());
        		response.status(201);
            
        	} catch (CustomUrlExistsException e){
        	
        		response.status(501);
        		
        	} catch (WrongUrlException e){
        		
        		response.status(502);
        	} catch (CustomUrlNotValidException e){
        		
        		response.status(503);
        	}
        	
        	return returnUrl;
        	
        }, new JsonTransformer());
        

    	//dummy
        get(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> shortenerService.find(request.params(":id")), new JsonTransformer());

    }


}
