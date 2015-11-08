package lilliurlian.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharFinder {
	public static boolean isFound(String stringToCheck){
		Pattern p = Pattern.compile(".*\\W+.*");
		Matcher m = p.matcher(stringToCheck);
		return m.matches();
	}
}
