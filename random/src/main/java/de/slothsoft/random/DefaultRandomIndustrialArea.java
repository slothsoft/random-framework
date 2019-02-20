package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultRandomIndustrialArea extends AbstractRandomIndustrialArea {

	private Map<Class<?>, RandomFactory<?>> randomFactories = new HashMap<Class<?>, RandomFactory<?>>();
	private int recursion = 5;

	/**
	 * Adds a <code>RandomFactory</code> to this industrial area.
	 * 
	 * @param factory
	 */

	public void add(RandomFactory<?> factory) {
		this.randomFactories.put(factory.getCreatedClass(), factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.slothsoft.random.AbstractRandomIndustrialArea#doGetRandomFactory(java
	 * .lang.Class)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public <T> RandomFactory<T> doGetRandomFactory(Class<T> createdClass) {
		return (RandomFactory<T>) this.randomFactories.get(createdClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.slothsoft.random.RandomIndustrialArea#containsRandomFactoryFor(java
	 * .lang.Class)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public <T> boolean containsRandomFactoryFor(Class<T> createdClass) {
		try {
			RandomFactory<T> result = (RandomFactory<T>) this.randomFactories
					.get(createdClass);
			return result != null;
		} catch (Exception e) {
			// might be a class cast exception or something
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.slothsoft.random.RandomIndustrialArea#createSingle(java.lang.Class,
	 * java.util.Set)
	 */

	@Override
	public <T> T createSingle(Class<T> createdClass, Set<Option> options)
			throws RandomException {
		return doCreateSingle(createdClass, options, this.recursion);
	}

	public <T> T doCreateSingle(Class<T> createdClass, Set<Option> options,
			int recursionSteps) throws RandomException {
		RandomFactory<T> factory = getRandomFactory(createdClass);
		T result = factory.createSingle(options);

		if (recursionSteps > 0) {
			Map<String, Class<?>> attributes = MappingUtil
					.getAttributes(createdClass);
			// now check, if one of the factories is better in generating one of
			// the
			// attributes
			for (String attribute : attributes.keySet()) {
				Class<?> attributeClass = attributes.get(attribute);
				if (containsRandomFactoryFor(attributeClass)) {
					try {
						String setterName = MappingUtil
								.getSetterName(attribute);
						Method setter = createdClass.getMethod(setterName,
								attributeClass);
						try {
							setter.invoke(
									result,
									doCreateSingle(attributeClass, options,
											recursionSteps - 1));
						} catch (Exception e) {
							throw new RandomException(
									"Could not set attribute " + attribute
											+ " by RandomIndustrialArea", e);
						}
					} catch (NoSuchMethodException e) {
						// wrong attribute class, just go on
					}
				}
			}
		}
		return result;
	}

	public int getRecursion() {
		return this.recursion;
	}

	public void setRecursion(int recursion) {
		this.recursion = recursion;
	}

}
