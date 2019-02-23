package de.slothsoft.random;

import java.util.Random;

/**
 * Interface for all various random fields.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */
public interface RandomField {

	Random RND = new Random(); // else we have too many instances to little apart

	/**
	 * Returns a random value.
	 *
	 * @return a random value
	 */

	Object nextValue();
}
