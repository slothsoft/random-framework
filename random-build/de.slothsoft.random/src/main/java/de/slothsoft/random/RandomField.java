package de.slothsoft.random;

import java.util.Set;

/**
 * Interface for all various random fields.
 * 
 * @author Steffi
 * 
 * @param <T>
 *            - the class of the return value
 */
public interface RandomField<T> {

	/**
	 * Returns a random attribute for an attribute name and options.
	 * 
	 * @param attributeName
	 *            - attribute name
	 * @param options
	 *            - options for this random field
	 * @return a random attribute
	 */

	T getRandomAttribute(String attributeName, Class<?> attributeClass,
			Set<Option> options);

	/**
	 * Returns the possible classes of T
	 * 
	 * @return a class
	 */

	Class<?>[] getAttributeClasses();

	/**
	 * Returns a display name not really used for displaying. More or less an
	 * id.
	 * 
	 * @return a string
	 */
	String getDisplayName();

	/**
	 * Returns if this random field should be used to render the attribute name.
	 * 
	 * @param attributeName
	 * @return a boolean
	 */

	boolean isMapped(String name, Class<?> parameterType);

}
