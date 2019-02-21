package de.slothsoft.random.db;

public abstract class AbstractPrimitiveNumberRandomField extends
		AbstractNumberRandomField {

	public AbstractPrimitiveNumberRandomField(String displayName) {
		super(displayName);
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class<?>[] { getPrimitiveClass(), getNumberClass() };
	}

	protected abstract Class<?> getPrimitiveClass();
}
