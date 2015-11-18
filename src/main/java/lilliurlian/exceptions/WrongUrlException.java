package lilliurlian.exceptions;

/**
 * Exception thrown when a website URL isn't correct and doesn't match URL standards (i.e. no .domain, invalid chars).
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class WrongUrlException extends Exception {
	public WrongUrlException() { super(); }
	public WrongUrlException(String message) { super(message); }
	public WrongUrlException(String message, Throwable cause) { super(message, cause); }
	public WrongUrlException(Throwable cause) { super(cause); }
}
