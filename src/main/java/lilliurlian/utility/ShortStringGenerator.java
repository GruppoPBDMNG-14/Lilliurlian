package lilliurlian.utility;

import java.util.Random;

/**
 * Service for shortUrl random generation. Limited charset and length are defined by constants.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class ShortStringGenerator {
	
		  private static final char[] SYMBOLS;
		  private static final int LENGTH = 6;
		  
		  static {
		    StringBuilder tmp = new StringBuilder();
		    for (char ch = '0'; ch <= '9'; ++ch)
		      tmp.append(ch);
		    for (char ch = 'A'; ch <= 'Z'; ++ch)
		      tmp.append(ch);
		    for (char ch = 'a'; ch <= 'z'; ++ch)
			      tmp.append(ch);
		    SYMBOLS = tmp.toString().toCharArray();
		  }   
		  private final Random random = new Random();
		  private final char[] buf;

		  /**
		   * Constructs a new generator setting the needed length.
		   */
		  public ShortStringGenerator() {
			  
		    buf = new char[LENGTH];
		  }

		  /**
		   * Generates a new random string with class defined length and chars.
		   * 
		   * @return the generated string.
		   */
		  public String nextString() {
		    for (int idx = 0; idx < buf.length; ++idx) 
		      buf[idx] = SYMBOLS[random.nextInt(SYMBOLS.length)];
		    return new String(buf);
		  }
		}