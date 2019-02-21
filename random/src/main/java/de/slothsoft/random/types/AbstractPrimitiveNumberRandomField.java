package de.slothsoft.random.types;

public abstract class AbstractPrimitiveNumberRandomField<N extends Number> extends AbstractNumberRandomField<N> {

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
