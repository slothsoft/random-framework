package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The main class for creating random data inside a defined POJO.
 *
 * @author Stef Schulz
 * @since 1.0.0
 * @param <T> the type to be created
 */

public class RandomFactory<T> implements RandomField {

	/**
	 * Represents a supplier of results.
	 *
	 * @param <U> the type of results supplied by this supplier
	 */

	@FunctionalInterface
	public interface Supplier<U> {

		/**
		 * Gets a result.
		 *
		 * @return a result
		 * @throws RandomException for any reason
		 */

		U get() throws RandomException;
	}

	/**
	 * Creates a {@link RandomFactory} with just one class and then tries to guess which
	 * fields to fill. Might not be the best option for some cases.
	 *
	 * @param pojoClass the class of the POJO to be created; the class must have a default
	 *            constructor and be public
	 * @param <U> the class that should be generated
	 * @return a {@link RandomFactory} with guessed mapping
	 */

	public static <U> RandomFactory<U> forClass(Class<U> pojoClass) {
		return new RandomFactory<>(() -> {
			try {
				return pojoClass.getConstructor().newInstance();
			} catch (final Exception e) {
				throw new RandomException("Could not create instance of class " + pojoClass
						+ ". Class must have default constructor and be public visible!", e);
			}
		}, guessMapping(pojoClass));
	}

	/**
	 * Guesses the mapping from the actual name of the getter and setter.
	 *
	 * @param pojoClass the class
	 * @return a mapping
	 */

	static Map<String, RandomField> guessMapping(Class<?> pojoClass) {
		final Map<String, Class<?>> fields = PropertyUtil.getProperties(pojoClass);
		final Map<String, RandomField> result = new LinkedHashMap<>();

		for (final Entry<String, Class<?>> fieldEntry : fields.entrySet()) {
			final RandomField randomField = RandomFieldSupplier.createRandomFieldByField(fieldEntry.getKey(),
					fieldEntry.getValue());
			if (randomField != null) {
				result.put(fieldEntry.getKey(), randomField);
			}
		}
		return result;
	}

	private final Supplier<T> pojoSupplier;
	private final Map<String, RandomField> fieldMapping;
	private final Class<?> pojoClass;

	/**
	 * A constructor that takes one {@link Supplier}. The mapping of properties to the
	 * factories filling them is guessed.
	 *
	 * @param pojoSupplier the supplier for the POJO
	 */

	public RandomFactory(Supplier<T> pojoSupplier) {
		this(pojoSupplier, guessMapping(pojoSupplier.get().getClass()));
	}

	/**
	 * A constructor that takes one class and the mapping of properties to the factories
	 * filling them.
	 *
	 * @param pojoSupplier the supplier for the POJO
	 * @param fieldMapping the initial mapping for the fields
	 */

	public RandomFactory(Supplier<T> pojoSupplier, Map<String, RandomField> fieldMapping) {
		this.pojoSupplier = pojoSupplier;
		this.fieldMapping = fieldMapping;
		this.pojoClass = pojoSupplier.get().getClass();
	}

	@Override
	public T nextValue() {
		return createSingle();
	}

	/**
	 * Creates a single instance of the class this factory is for.
	 *
	 * @return a single POJO instance
	 * @throws RandomException - if something went wrong
	 */

	public T createSingle() throws RandomException {
		final T result = this.pojoSupplier.get();
		fillFields(result);
		return result;
	}

	void fillFields(T value) throws RandomException {
		final Map<String, Object> context = new HashMap<>();

		for (final Entry<String, RandomField> fieldEntry : this.fieldMapping.entrySet()) {
			final RandomField randomField = fieldEntry.getValue();
			randomField.init(context);
			final Object randomValue = randomField.nextValue();
			PropertyUtil.setProperty(value, fieldEntry.getKey(), randomValue);
			context.put(fieldEntry.getKey(), randomValue);
		}
	}

	/**
	 * Creates some instances of the POJO this factory is for.
	 *
	 * @param count number of instances to be created
	 * @return some POJO instances
	 * @throws RandomException - if something went wrong
	 */

	public List<T> create(int count) {
		final List<T> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle());
		}
		return result;
	}

	/**
	 * Returns the {@link RandomField} used to fill the property.
	 *
	 * @param property the property
	 * @return the random field to fill this property; never null
	 * @throws RandomException if no {@link RandomField} was found
	 */

	public RandomField getRandomField(String property) {
		final RandomField result = findRandomField(property);
		if (result == null) {
			throw new RandomException("Could not find RandomField for property " + property);
		}
		return result;
	}

	/**
	 * Returns the {@link RandomField} used to fill the property.
	 *
	 * @param property the property
	 * @return the random field to fill this property; can be null
	 */

	public RandomField findRandomField(String property) {
		return this.fieldMapping.get(property);
	}

	/**
	 * Adds a {@link RandomField} to fill some property.
	 *
	 * @param property the property
	 * @param randomField the random field to fill this property; can be null
	 */

	public void addRandomField(String property, RandomField randomField) {
		this.fieldMapping.put(property, randomField);
	}

	/**
	 * Removes a {@link RandomField}.
	 *
	 * @param property the property
	 */

	public void removeRandomField(String property) {
		this.fieldMapping.remove(property);
	}

	Class<?> getPojoClass() {
		return this.pojoClass;
	}
}
