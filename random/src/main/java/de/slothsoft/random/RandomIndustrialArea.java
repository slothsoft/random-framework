package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A wrapper for many <code>RandomFactory</code>s and possible some self made random
 * fields or options or whatever might be necessary for even better optimization.<br>
 * <br>
 * <i>An industrial area has many factories.</i>
 *
 *
 * @author Steffi
 *
 */

public class RandomIndustrialArea {

	/**
	 * Creates a default <code>RandomFactory</code> for the class. This is a handy method
	 * for creating an entire <code>RandomIndustrialArea</code> based on guess work.
	 *
	 * @param createdClasses - the classes to be guessed
	 * @return the brand new object
	 */

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static RandomIndustrialArea create(Class<?>... createdClasses) {
		final RandomIndustrialArea industrialArea = new RandomIndustrialArea();
		for (final Class<?> createdClass : createdClasses) {
			industrialArea.addFactory(RandomFactory.forClass(createdClass));
		}
		return industrialArea;
	}

	private final Map<Class<?>, RandomFactory<?>> randomFactories = new HashMap<>();
	private int recursion = 5; // TODO: rename this field to something that speaks to
								// users

	/**
	 * Adds a <code>RandomFactory</code> to this industrial area.
	 *
	 * @param factory
	 */

	public void addFactory(RandomFactory<?> factory) {
		this.randomFactories.put(factory.getPojoClass(), factory);
	}

	/**
	 * Returns the <code>RandomFactory</code> for the class.
	 *
	 * @param <T> - the type the factory is for
	 * @param createdClass - the class that should be created
	 * @return a random factory
	 * @throws RandomException if none was fond
	 */

	@SuppressWarnings("unchecked")
	public <T> RandomFactory<T> getRandomFactory(Class<T> createdClass) throws RandomException {
		if (!containsRandomFactoryFor(createdClass))
			throw new RandomException("Could not find RandomFactory for class " + createdClass);
		return (RandomFactory<T>) this.randomFactories.get(createdClass);
	}

	/**
	 * Returns if there is a <code>RandomFactory</code> for the class.
	 *
	 * @param <T> - the type the factory is for
	 * @param createdClass - the class that should be created
	 * @return true, if there is a random factory
	 * @throws RandomException if none was fond
	 */

	@SuppressWarnings("unchecked")
	public <T> boolean containsRandomFactoryFor(Class<T> createdClass) {
		try {
			final RandomFactory<T> result = (RandomFactory<T>) this.randomFactories.get(createdClass);
			return result != null;
		} catch (final Exception e) {
			// might be a class cast exception or something
			return false;
		}
	}

	/**
	 * Creates a single instance of the class. For all the fields of this class, the
	 * factories of this area are asked, if they want to create it. If not, the normal
	 * procedure is used.
	 *
	 * @param createdClazz - the class to be created
	 * @return a single dummy instance
	 * @throws RandomException - if something went wrong
	 */

	public <T> T createSingle(Class<T> createdClass) throws RandomException {
		return doCreateSingle(createdClass, this.recursion);
	}

	private <T> T doCreateSingle(Class<T> createdClass, int recursions) throws RandomException {
		final RandomFactory<T> factory = getRandomFactory(createdClass);
		final T result = factory.createSingle();

		if (recursions > 0) {
			final Map<String, Class<?>> fields = PropertyUtil.getFields(createdClass);
			// now check, if one of the factories is better in generating one of
			// the
			// fields
			for (final String field : fields.keySet()) {
				final Class<?> fieldClass = fields.get(field);
				if (containsRandomFactoryFor(fieldClass)) {
					try {
						final String setterName = PropertyUtil.getSetterName(field);
						final Method setter = createdClass.getMethod(setterName, fieldClass);
						try {
							setter.invoke(result, doCreateSingle(fieldClass, recursions - 1));
						} catch (final Exception e) {
							throw new RandomException("Could not set field " + field + " by RandomIndustrialArea", e);
						}
					} catch (final NoSuchMethodException e) {
						// wrong field class, just go on
					}
				}
			}
		}
		return result;
	}
	/**
	 * Creates some instances of the class this factory is for. For all the fields of this
	 * class, the factories of this area are asked, if they want to create it. If not, the
	 * normal procedure is used.
	 *
	 * @param count - number of instances to be created
	 * @return some dummy instance
	 * @throws RandomException - if something went wrong
	 */

	public <T> List<T> create(Class<T> createdClass, int count) throws RandomException {
		final List<T> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle(createdClass));
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
