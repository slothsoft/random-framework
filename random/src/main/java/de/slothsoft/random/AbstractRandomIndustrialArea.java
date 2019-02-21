package de.slothsoft.random;

public abstract class AbstractRandomIndustrialArea implements
		RandomIndustrialArea {


	@Override
	public <T> RandomFactory<T> getRandomFactory(Class<T> createdClass) {
		if (!containsRandomFactoryFor(createdClass)) {
			throw new RandomException("Could not find RandomFactory for class "
					+ createdClass);
		}
		return doGetRandomFactory(createdClass);
	}

	/**
	 * Returns the <code>RandomFactory</code> for the supported class.
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

}
