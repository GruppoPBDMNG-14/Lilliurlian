package lilliurlian.utility;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * Utility service that accesses to the GeoLite2 country database to geolocate the IP of a request.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class IPGeoloc {
	
	/** The file of the local GeoLite2 database. */
	private File db;
	
	/**GeoLite2 tools for reading db files. */
	private DatabaseReader dbreader;
	
	/**
	 * Constructs an IPGeoloc service.
	 * 
	 * @throws IOException
	 */
	public IPGeoloc() throws IOException{
		db = new File("src/main/resources/public/IPdb/GeoLite2-Country.mmdb");
		dbreader = new DatabaseReader.Builder(db).build();
	}
	
	/**
	 * Finds the ISO code of the country associated to the given IP
	 * 
	 * @param IP The IP we want to geolocate.
	 * @return the ISO code found.
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public String getCountryIso(String IP) throws IOException, GeoIp2Exception {
		CountryResponse response = dbreader.country(InetAddress.getByName(IP));
		Country country = response.getCountry();
		return country.getIsoCode();
	}
	
	/**
	 * Finds the country name associated to the given IP.
	 * 
	 * @param IP The IP we want to geolocate.
	 * @return the country name found.
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public String getCountryName(String IP) throws IOException, GeoIp2Exception {
		CountryResponse response = dbreader.country(InetAddress.getByName(IP));
		Country country = response.getCountry();
		return country.getName();
	}
}