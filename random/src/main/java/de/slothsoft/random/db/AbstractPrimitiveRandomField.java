package de.slothsoft.random.db;

public abstract class AbstractPrimitiveRandomField<T> extends
		AbstractRandomField<T> {

	public AbstractPrimitiveRandomField(String displayName) {
		super(displayName);
	}

	@Override
	public boolean isMapped(String name, Class<?> parameterType) {
		return isClassMapped(parameterType);
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class<?>[] { getPrimitiveClass(), getNonprimitiveClass() };
	}

	protected abstract Class<?> getPrimitiveClass();

	protected abstract Class<T> getNonprimitiveClass();
}
