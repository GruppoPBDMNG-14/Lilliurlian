package lilliurlian.utility;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.maxmind.geoip2.exception.GeoIp2Exception;

public class IPGeolocTest {
	IPGeoloc test;
	private String[] samples = {"2.119.255.250", "3.255.255.255", "2.155.255.255", "5.39.127.255", "2.63.255.255"};
	private String[] resultsISO = {"IT", "US", "ES", "FR", "RU"};
	private String[] resultsName = {"Italy", "United States", "Spain", "France", "Russia"};

	@Before
	public void setUp() throws Exception {
		test = new IPGeoloc();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public final void testGetCountryIso() throws IOException, GeoIp2Exception {
		for(int i = 0; i<samples.length; i++)
			assertEquals(test.getCountryIso(samples[i]), resultsISO[i]);
	}

	@Test
	public final void testGetCountryName() throws IOException, GeoIp2Exception {
		for(int i = 0; i<samples.length; i++)
			assertEquals(test.getCountryName(samples[i]), resultsName[i]);
	}
}
