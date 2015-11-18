package lilliurlian.exceptions;

/**
 * Exception thrown when a custom URL inserted by user is already into the database.
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class CustomUrlExistsException extends Exception {
	public CustomUrlExistsException() { super(); }
	public CustomUrlExistsException(String message) { super(message); }
	public CustomUrlExistsException(String message, Throwable cause) { super(message, cause); }
	public CustomUrlExistsException(Throwable cause) { super(cause); }
}
