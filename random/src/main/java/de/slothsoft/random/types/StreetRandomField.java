package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a street
 * (i.e. "High Street 10").
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class StreetRandomField extends ElementFromListRandomField<String> {

	static final String[] streets;

	static {
		streets = FirstNameRandomField.readFile("street-names.txt");
	}

	/**
	 * @since 2.1.0
	 */

	public StreetRandomField() {
		this(streets);
	}

	@Deprecated
	public StreetRandomField(String[] fields) {
		super(fields);
	}

	@Override
	public String nextValue() {
		return super.nextValue() + " " + (RND.nextInt(100) + 1);
	}
}
