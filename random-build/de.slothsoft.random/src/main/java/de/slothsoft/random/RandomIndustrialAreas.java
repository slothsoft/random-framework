package de.slothsoft.random;

/**
 * Utility class for creating <code>RandomIndustrialArea</code>s.
 * 
 * @author Steffi
 */

public abstract class RandomIndustrialAreas {

	/**
	 * Creates a default <code>RandomFactory</code> for the class. This is a
	 * handy method for creating an entire <code>RandomIndustrialArea</code>
	 * based on guess work.
	 * 
	 * @param createdClasses
	 *            - the classes to be guessed
	 * @return the brand new object
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static DefaultRandomIndustrialArea create(Class<?>... createdClasses) {
		DefaultRandomIndustrialArea industrialArea = new DefaultRandomIndustrialArea();
		for (Class<?> createdClass : createdClasses) {
			industrialArea.add(new DefaultRandomFactory(createdClass));
		}
		return industrialArea;
	}

	private RandomIndustrialAreas() {
		// *hiding behind a big method name*
	}
}
