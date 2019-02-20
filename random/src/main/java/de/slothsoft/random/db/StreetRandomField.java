package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.STREET;

import java.util.Set;

import de.slothsoft.random.Option;

public class StreetRandomField extends AttributeRandomField {

	public StreetRandomField() {
		super("street-names", STREET);
	}

	@Override
	public String getRandomAttribute(String attribute, Class<?> attributeClass,
			Set<Option> options) {
		return super.getRandomAttribute(attribute, attributeClass, options)
				+ " " + (RANDOM.nextInt(100) + 1);
	}
}
