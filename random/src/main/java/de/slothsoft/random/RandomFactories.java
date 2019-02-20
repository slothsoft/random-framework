package de.slothsoft.random;

import java.util.Map;

/**
 * Utility class for creating <code>RandomFactory</code>s. Created to make the
 * API break of making <code>RandomFactory</code> an interface as small as
 * possible.
 * 
 * @author Steffi
 * 
 */

public abstract class RandomFactories {

	/**
	 * Creates a default <code>RandomFactory</code> for the class.
	 * 
	 * @param <T>
	 *            - type of factory
	 * @param createdClass
	 *            - class of type
	 * @return a <code>RandomFactory</code>
	 */

	public static <T> RandomFactory<T> create(Class<T> createdClass) {
		return new DefaultRandomFactory<T>(createdClass);
	}

	/**
	 * Creates a default <code>RandomFactory</code> for the class and mapping.
	 * 
	 * @param <T>
	 *            - type of factory
	 * @param createdClass
	 *            - class of type
	 * @param mapping
	 *            - the mapping
	 * @return a <code>RandomFactory</code>
	 */

	public static <T> RandomFactory<T> create(Class<T> createdClass,
			Map<String, String> mapping) {
		return new DefaultRandomFactory<T>(createdClass, mapping);
	}

	private RandomFactories() {
		// *playing hide and seek with some interfaces*
	}

}
