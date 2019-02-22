package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Boolean} or it's primitive counterpart.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BooleanRandomField implements RandomField {

	private boolean primitive;

	@Override
	public Class<?> getFieldClass() {
		return this.primitive ? boolean.class : Boolean.class;
	}

	@Override
	public Boolean nextValue() {
		return Boolean.valueOf(RND.nextBoolean());
	}

	public boolean isPrimitive() {
		return this.primitive;
	}

	public BooleanRandomField primitive(boolean newPrimitive) {
		setPrimitive(newPrimitive);
		return this;
	}

	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}

}
