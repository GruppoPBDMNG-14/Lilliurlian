package lilliurlian.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class that researches special chars in a string.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class SpecialCharFinder {
	
	/**
	 * Checks if a string contains chars different from: a-z, A-Z, 0-9.
	 * 
	 * @param stringToCheck The string to analyze.
	 * @return true if a special char is found, otherwise returns false.
	 */
	public static boolean isFound(String stringToCheck){
		Pattern p = Pattern.compile(".*\\W+.*");
		Matcher m = p.matcher(stringToCheck);
		return m.matches();
	}
}
