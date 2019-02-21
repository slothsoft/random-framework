package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RandomFactory<T> {

	/**
	 * Creates a single instance of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @return a single dummy instance
	 * @throws RandomException - if something went wrong
	 */

	default T createSingle() throws RandomException {
		return createSingle(new HashSet<Option>());
	}

	/**
	 * Creates a single instance of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param options - some options for the values
	 * @return a single dummy instance
	 * @throws RandomException - if something went wrong
	 */

	T createSingle(Set<Option> options) throws RandomException;

	/**
	 * Creates some instances of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param count - number of instances to be created
	 * @return some dummy instance
	 * @throws RandomException - if something went wrong
	 */

	default List<T> create(int count) {
		return create(count, new HashSet<Option>());
	}

	/**
	 * Creates some instances of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param count   - number of instances to be created
	 * @param options - some options for the values
	 * @return some dummy instance
	 * @throws RandomException - if something went wrong
	 */

	default List<T> create(int count, Set<Option> options) throws RandomException {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle(options));
		}
		return result;
	}

	/**
	 * Returns the class this factory is for
	 */

	Class<T> getCreatedClass();

}