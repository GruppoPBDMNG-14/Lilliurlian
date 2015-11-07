package lilliurlian.utility;

/*
 * Questa classe di utility permette di valutare se una stringa ottenuta in input è vuota.
 * Per stringa vuota si intende una stringa non inizializzata, con lunghezza nulla 
 * o piena esclusivamente di spazi bianchi.
 * Il metodo restituisce la positività del controllo tramite valore booleano.
 */
public class EmptyStringChecker {
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