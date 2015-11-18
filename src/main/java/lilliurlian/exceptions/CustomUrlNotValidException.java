package lilliurlian.exceptions;

/**
 * Exception thrown when the custom URL inserted by the user contains inappropriate content
 * or chars are not like the following: a-z, A-Z, 0-9.
 * 
 * @author GruppoPBDMNG-14
 *
 */

public class CustomUrlNotValidException extends Exception {
	public CustomUrlNotValidException() { super(); }
	public CustomUrlNotValidException(String message) { super(message); }
	public CustomUrlNotValidException(String message, Throwable cause) { super(message, cause); }
	public CustomUrlNotValidException(Throwable cause) { super(cause); }	
}
