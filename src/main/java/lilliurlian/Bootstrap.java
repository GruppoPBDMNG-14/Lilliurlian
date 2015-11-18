

package lilliurlian;

import com.mongodb.*;
import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * This is the main class of the Lilliurlian URL Shortener's server application. Here all the needed parameters are set
 * and the main services started.
 * 
 *  @author GruppoPBDMNG-14
 */

public class Bootstrap {
	/** Server IP Address*/
    private static final String IP_ADDRESS = "localhost";
    /** Server Port */
    private static final int PORT = 8080;
    /** Mongo Database name */
    private static final String  DB_NAME= "URLShortener";

    /**
     * Main method. Server address and port are set.
     * Database client is created and services(Spark, DAO) have been initialized.
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new ShortenerResource(new ShortenerService(mongo()));
    }

    /**
     * Creates a new MongoClient specifying its address.
     * If the chosen DB is already available it gets directly returned, otherwise a new DB with the specified name is created.
     * 
     * @return the application database found or just created.
     * @throws Exception
     */
    private static DB mongo() throws Exception {
            MongoClient mongoClient = new MongoClient(IP_ADDRESS);
            return mongoClient.getDB(DB_NAME);
    }
}