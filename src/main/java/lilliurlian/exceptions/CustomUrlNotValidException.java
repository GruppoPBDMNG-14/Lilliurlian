package lilliurlian.exceptions;

public class CustomUrlNotValidException extends Exception {
	public CustomUrlNotValidException() { super(); }
	public CustomUrlNotValidException(String message) { super(message); }
	public CustomUrlNotValidException(String message, Throwable cause) { super(message, cause); }
	public CustomUrlNotValidException(Throwable cause) { super(cause); }	
}
