package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashMap;
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

public class RandomFactory<T> {

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
		final Map<String, RandomField> result = new HashMap<>();

		for (final Entry<String, Class<?>> fieldEntry : fields.entrySet()) {
			final RandomFieldSupplier field = RandomFieldSupplier.findSupplierByField(fieldEntry.getKey(),
					fieldEntry.getValue());
			if (field != null) {
				result.put(fieldEntry.getKey(), field.createRandomField(fieldEntry.getKey(), fieldEntry.getValue()));
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
		for (final Entry<String, RandomField> fieldEntry : this.fieldMapping.entrySet()) {
			final RandomField randomField = fieldEntry.getValue();
			PropertyUtil.setProperty(value, fieldEntry.getKey(), randomField.nextValue());
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

	// TODO: comment and test these methods

	public RandomField getRandomField(String property) {
		return this.fieldMapping.get(property);
	}

	public void addRandomField(String property, RandomField randomField) {
		this.fieldMapping.put(property, randomField);
	}

	Class<?> getPojoClass() {
		return this.pojoClass;
	}
}
