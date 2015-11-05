package lilliurlian.utility;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;


public class IPGeoloc {
	
	private File db;
	private DatabaseReader dbreader;
	
	public IPGeoloc() throws IOException{
		
		db = new File("src/main/resources/public/IPdb/GeoLite2-Country.mmdb");
		
		dbreader = new DatabaseReader.Builder(db).build();
	}
	

	public String getCountryIso(String IP) throws IOException, GeoIp2Exception {
		CountryResponse response = dbreader.country(InetAddress.getByName(IP));
		Country country = response.getCountry();
		return country.getIsoCode();
	}
	
	public String getCountryName(String IP) throws IOException, GeoIp2Exception {
		CountryResponse response = dbreader.country(InetAddress.getByName(IP));
		Country country = response.getCountry();
		return country.getName();
	}
	
}