package lilliurlian.utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mirko
 *
 */
public class TestEmptyStringChecker {
	private String[] samples = {"", " ", "  ", "   ", " a", "a ", " a ", "  a 1 ", "1 2 3&"};
	private boolean[] results = {true, true, true, true, false, false, false, false, false};

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link lilliurlian.utility.EmptyStringChecker#isBlank(java.lang.String)}.
	 */
	@Test
	public final void testIsBlank() {
		for(int i = 0; i<samples.length; i++)
			assertTrue("Caso di test " + i, EmptyStringChecker.isBlank(samples[i]) == results[i]);
	}

}
