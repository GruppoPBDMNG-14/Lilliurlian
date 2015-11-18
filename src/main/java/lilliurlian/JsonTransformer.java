package lilliurlian;

import com.google.gson.Gson;
import spark.Response;
import spark.ResponseTransformer;
import java.util.HashMap;
 
/**
 * Google's Gson library is used to convert Json to Java Objects and viceversa.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        return gson.toJson(model);
    }
}