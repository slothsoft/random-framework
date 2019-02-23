package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Boolean} or it's primitive counterpart.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BooleanRandomField implements RandomField {

	@Override
	public Class<?> getFieldClass() {
		return Boolean.class;
	}

	@Override
	public Boolean nextValue() {
		return Boolean.valueOf(RND.nextBoolean());
	}

}
