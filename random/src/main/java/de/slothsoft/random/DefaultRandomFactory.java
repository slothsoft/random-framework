package de.slothsoft.random;

import java.util.Map;
import java.util.Set;

/**
 * The main class for creating random dummy data.
 * 
 * @author Steffi
 * @param <T>
 *            - the type to be created
 */
public class DefaultRandomFactory<T> implements RandomFactory<T> {

	private Class<T> createdClass;
	private Map<String, String> attributeMapping;

	/**
	 * A constructor that takes just one class and then tries to guess which
	 * attributes to fill. Might not be the best option for some cases.
	 * 
	 * @param createdClass
	 */

	public DefaultRandomFactory(Class<T> createdClass) {
		this(createdClass, MappingUtil.guessMapping(createdClass));
	}

	/**
	 * A constructor that takes one class and the mapping in the form:
	 * <code>attributeName=de.slothsoft.random.RandomFields.CONSTANT</code>
	 * 
	 * @param createdClass
	 * @param attributeMapping
	 */

	public DefaultRandomFactory(Class<T> createdClass,
			Map<String, String> attributeMapping) {
		this.createdClass = createdClass;
		this.attributeMapping = attributeMapping;
	}


	@Override
	public T createSingle(Set<Option> options) throws RandomException {
		T result = createNewInstance(options);
		fillAttributes(result, options);
		return result;
	}

	/**
	 * Fills the value's attributes.
	 * 
	 * @param value
	 * @param options
	 * @throws RandomException
	 */

	public void fillAttributes(T value, java.util.Set<Option> options)
			throws RandomException {
		MappingUtil.fill(value, this.attributeMapping, options);
	}

	/**
	 * Creates a new instance of the class
	 * 
	 * @param options
	 * @throws RandomException
	 */

	public T createNewInstance(Set<Option> options) throws RandomException {
		try {
			return this.createdClass.getConstructor().newInstance();
		} catch (Exception e) {
			throw new RandomException(
					"Could not create instance of class "
							+ this.createdClass
							+ ". Class must have default constructor and be public visible!",
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomFactor#getCreatedClass()
	 */

	@Override
	public Class<T> getCreatedClass() {
		return this.createdClass;
	}

}
