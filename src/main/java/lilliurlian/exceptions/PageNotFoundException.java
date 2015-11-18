package lilliurlian.exceptions;

/**
 * Exception thrown when the short URL requested for redirect is not found
 * 
 * @author GruppoPBDMNG-14
 *
 */
public class PageNotFoundException extends Exception {
		public PageNotFoundException() { super(); }
		public PageNotFoundException(String message) { super(message); }
		public PageNotFoundException(String message, Throwable cause) { super(message, cause); }
		public PageNotFoundException(Throwable cause) { super(cause); }
}
