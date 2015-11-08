package lilliurlian.utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSpecialCharFinder {
	private String[] samples = {"%", "ù", "a", "we)", "asdxvz23", "cwefwe565!", "asf234", "12345", "qwe'ty", "azxe", "/", "à"};
	private boolean[] results = {true, true, false, true, false, true, false, false, true, false, true, true};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIsFound() {
		for(int i = 0; i<samples .length; i++)
			assertTrue("Caso di test " + i, SpecialCharFinder.isFound(samples[i]) == results[i]);
	}
}