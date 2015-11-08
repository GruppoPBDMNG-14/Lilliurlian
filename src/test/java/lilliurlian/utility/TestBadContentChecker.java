package lilliurlian.utility;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Mirko
 *
 */
public class TestBadContentChecker {
	private String[] samples = {"12cazzoer","nonruffiano7", "estrapuppami12", 
			"pistolante", "ignudo", "finocchio", "al2bordellocsdf1", 
			"sherlock", "vault101", "dovahkin", "dottorechi"};
	private boolean[] results ={true, true, true, true, true, true, true, 
			false, false, false, false};
	
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
	 * Test method for {@link lilliurlian.utility.BadContentChecker#check(java.lang.String)}.
	 */
	@Test
	public final void testCheck() {
		for(int i = 0; i<samples.length; i++)
			assertTrue("Caso di test " + i, BadContentChecker.check(samples[i]) == results[i]);
	}
}