package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a last name.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class LastNameRandomField extends ElementFromListRandomField<String> {

	static final String[] lastNames;

	static {
		lastNames = FirstNameRandomField.readFile("last-names.txt");
	}

	/**
	 * Default constructor.
	 */

	public LastNameRandomField() {
		super(lastNames);
	}

}
