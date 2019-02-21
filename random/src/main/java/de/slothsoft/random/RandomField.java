package de.slothsoft.random;

import java.util.Random;

/**
 * Interface for all various random fields.
 *
 * @author Steffi
 *
 * @param <T> - the class of the return value
 */
public interface RandomField<T> {

	// TODO: t is not necessary probably

	Random RND = new Random(); // else we have too many instances to little apart

	/**
	 * Returns a random value.
	 *
	 * @return a random value
	 */

	T nextValue();

	/**
	 * Returns the class of T
	 *
	 * @return a class
	 */

	Class<?> getFieldClass();

}
