package util;

/**
 * Custom exception for invalid input 
 * @author Bing Wen (z3463269)
 */
public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
	
    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
