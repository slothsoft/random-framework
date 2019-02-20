package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractRandomFactory<T> implements RandomFactory<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomFactor#createSingle()
	 */

	@Override
	public T createSingle() throws RandomException {
		return createSingle(new HashSet<Option>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomFactor#create(int)
	 */

	@Override
	public List<T> create(int count) {
		return create(count, new HashSet<Option>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomFactor#create(int, java.util.Set)
	 */

	@Override
	public List<T> create(int count, Set<Option> options)
			throws RandomException {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle(options));
		}
		return result;
	}
}
