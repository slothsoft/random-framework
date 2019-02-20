package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.BOOLEAN;

import java.util.Set;

import de.slothsoft.random.Option;

public class BooleanRandomField extends AbstractPrimitiveRandomField<Boolean> {

	public BooleanRandomField() {
		super(BOOLEAN);
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return boolean.class;
	}

	@Override
	protected Class<Boolean> getNonprimitiveClass() {
		return Boolean.class;
	}

	@Override
	public Boolean getRandomAttribute(String attributeName,
			Class<?> attributeClass, Set<Option> options) {
		return RANDOM.nextBoolean();
	}

}
