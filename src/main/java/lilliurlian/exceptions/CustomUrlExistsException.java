package lilliurlian.exceptions;

public class CustomUrlExistsException extends Exception {
	public CustomUrlExistsException() { super(); }
	public CustomUrlExistsException(String message) { super(message); }
	public CustomUrlExistsException(String message, Throwable cause) { super(message, cause); }
	public CustomUrlExistsException(Throwable cause) { super(cause); }
}
