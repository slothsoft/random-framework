package de.slothsoft.random;

/**
 * An exception occurring while creating random data.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class RandomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RandomException() {
		super();
	}

	public RandomException(String message) {
		super(message);
	}

	public RandomException(Throwable cause) {
		super(cause);
	}

	public RandomException(String message, Throwable cause) {
		super(message, cause);
	}

}
