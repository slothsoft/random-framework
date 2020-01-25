package de.slothsoft.random;

import java.util.Map;
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
	 * This method is called before {@link #nextValue()} to give the {@link RandomField}s
	 * for a POJO the chance to communicate. All already generated properties can be read
	 * using <code>context.get(propertyName)</code>.
	 *
	 * @param context the context
	 */

	default void init(Map<String, Object> context) {
		// nothing to do on default
	}

	/**
	 * Returns a random value.
	 *
	 * @return a random value
	 */

	Object nextValue();
}
