package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a city.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class CityRandomField extends ElementFromListRandomField<String> {

	static final String[] cities;

	static {
		cities = FirstNameRandomField.readFile("city-names.txt");
	}

	/**
	 * Default constructor.
	 */

	public CityRandomField() {
		super(cities);
	}

}
