package lilliurlian.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This utility checks if a string contains inappropriate content.
 * Text files are used as resources of forbidden words to compare the string with.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class BadContentChecker {
	
	/**
	 * Array of languages id
	 */
	private static final String[] LANGUAGES =  {"de","en","es","fi","fr","hi","hu","it","nl","pt","sv"};
	
	/**
	 * Searches for bad content in a given string.
	 * 
	 * @param stringToCheck The string to analyze.
	 * @return true if bad content is found, otherwise returns false.
	 */
	public static boolean check(String stringToCheck) {
		boolean result = false;
		
		try{
			for(int i = 0; i < LANGUAGES.length; i++){
				
				Scanner inputFile = new Scanner(new BufferedReader(new FileReader("src/main/resources/public/languages/" + LANGUAGES[i] + ".txt")));

				while (inputFile.hasNextLine() && result == false) {
					String badWord = inputFile.nextLine();
					
					if (stringToCheck.equalsIgnoreCase(badWord) || stringToCheck.contains(badWord)) {
						i = LANGUAGES.length;
						result = true;
					}
				}

				inputFile.close();
			}
		} catch (FileNotFoundException e) {

			result = false;
		}
		return result; 
	}
}