package lilliurlian.exceptions;

public class PageNotFoundException extends Exception {

		public PageNotFoundException() { super(); }
		public PageNotFoundException(String message) { super(message); }
		public PageNotFoundException(String message, Throwable cause) { super(message, cause); }
		public PageNotFoundException(Throwable cause) { super(cause); }
	
}
