package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractRandomIndustrialArea implements
		RandomIndustrialArea {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.slothsoft.random.RandomIndustrialArea#getRandomFactory(java.lang.Class
	 * )
	 */

	@Override
	public <T> RandomFactory<T> getRandomFactory(Class<T> createdClass) {
		if (!containsRandomFactoryFor(createdClass)) {
			throw new RandomException("Could not find RandomFactory for class "
					+ createdClass);
		}
		return doGetRandomFactory(createdClass);
	}

	/**
	 * Returns the <code>RandomFactory</code> for the class.
	 * 
	 * @param <T>
	 *            - the type the factory is for
	 * @param createdClass
	 *            - the class that should be created
	 * @return a random factory
	 * @throws RandomException
	 *             if none was fond
	 */

	public abstract <T> RandomFactory<T> doGetRandomFactory(
			Class<T> createdClass);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.slothsoft.random.RandomIndustrialArea#createSingle(java.lang.Class)
	 */

	@Override
	public <T> T createSingle(Class<T> createdClass) throws RandomException {
		return createSingle(createdClass, new HashSet<Option>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomIndustrialArea#create(java.lang.Class,
	 * int)
	 */

	@Override
	public <T> List<T> create(Class<T> createdClass, int count)
			throws RandomException {
		return create(createdClass, count, new HashSet<Option>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.slothsoft.random.RandomIndustrialArea#create(java.lang.Class,
	 * int, java.util.Set)
	 */

	@Override
	public <T> List<T> create(Class<T> createdClass, int count,
			Set<Option> options) throws RandomException {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle(createdClass, options));
		}
		return result;
	}

}
