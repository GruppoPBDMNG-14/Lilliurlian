package lilliurlian.utility;

/**
 * This utility class allows to evaluate if an input string is empty.
 * For "empty string" we mean: a not initialized string, a null length string or a string
 * only composed by blank spaces.
 * 
 * @author GruppoPBDMNG-14
 */
public class EmptyStringChecker {
	
	/**
	 * Checks if a string is empty.
	 * 
	 * @param str The string to check.
	 * @return true if the given string is empty, otherwise false.
	 */
	public static boolean isBlank(String str) {
	    boolean validateResult = true;
	    
	    if (str == null ||  str.length() == 0) {
	        validateResult = true;
	        
	    } else {
	    	for (int i = 0; i <  str.length(); i++) {
	    		if ((Character.isWhitespace(str.charAt(i)) == false)) {
	    			validateResult = false;
	    		}
	    	}
	    }	
	    return validateResult;
	}
}