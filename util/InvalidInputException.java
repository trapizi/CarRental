package util;

public class InvalidInputException extends Exception {
	/**
	 * 
	 */
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
