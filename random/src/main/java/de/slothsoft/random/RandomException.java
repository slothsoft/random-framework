package de.slothsoft.random;

/**
 * An exception occuring while creating random data.
 * 
 * @author Steffi
 */
public class RandomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RandomException() {
		super();
	}

	public RandomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RandomException(String message, Throwable cause) {
		super(message, cause);
	}

	public RandomException(String message) {
		super(message);
	}

	public RandomException(Throwable cause) {
		super(cause);
	}

}
