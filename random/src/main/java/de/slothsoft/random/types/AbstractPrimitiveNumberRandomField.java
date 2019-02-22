package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A base class for creating a {@link RandomField} from a {@link Number} that has a
 * primitive counterpart.
 *
 * @author Steffi Schulz
 * @since 2.0.0
 * @param <N> - a type of number
 */

public abstract class AbstractPrimitiveNumberRandomField<N extends Number> extends AbstractNumberRandomField<N> {

	// XXX: check if the primitive here is still necessary

	private final Class<?> primitiveClass;
	private final Class<?> fieldClass;

	private boolean primitive;

	public AbstractPrimitiveNumberRandomField(Class<N> primitiveClass, Class<?> fieldClass) {
		super(fieldClass);
		this.primitiveClass = primitiveClass;
		this.fieldClass = fieldClass;
	}

	@Override
	public Class<?> getFieldClass() {
		return this.primitive ? this.primitiveClass : this.fieldClass;
	}

	public boolean isPrimitive() {
		return this.primitive;
	}

	public AbstractPrimitiveNumberRandomField<N> primitive(boolean newPrimitive) {
		setPrimitive(newPrimitive);
		return this;
	}

	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}

}
