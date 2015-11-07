package lilliurlian.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BadContentChecker {
	private static final int NUMBER_OF_LANG = 11;
	
	public static boolean check(String stringToCheck) {
		boolean result = false;
		
		try{
			for(int i = 0; i < NUMBER_OF_LANG; i++){
				Scanner inputFile = new Scanner(new BufferedReader(new FileReader("src/main/resources/public/languages/it.txt")));

				while (inputFile.hasNextLine() && result == false) {
					String badWord = inputFile.nextLine();
					
					if (stringToCheck.equalsIgnoreCase(badWord) || stringToCheck.contains(badWord)) {
						i = NUMBER_OF_LANG;
						result = true;
					}
				}

				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("non trovato");
			result = false;
		}
		return result; 
	}
}