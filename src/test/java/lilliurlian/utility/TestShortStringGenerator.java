package lilliurlian.utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShortStringGenerator {
	private ShortStringGenerator test;
	private String sample = "[-a-zA-Z0-9]*[-a-zA-Z0-9]";

	@Before
	public void setUp() throws Exception {
		test = new ShortStringGenerator();
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public final void testNextString() {
		for (int i = 0; i<100; i++)
			assertTrue("Caso di test " + i, test.nextString().matches(sample));
	}
}
