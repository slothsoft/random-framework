package de.slothsoft.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A wrapper for many <code>RandomFactory</code>s and possible some self made
 * random fields or options or whatever might be necessary for even better
 * optimization.<br><br>
 * <i>An industrial area has many factories.</i>
 * 
 * 
 * @author Steffi
 * 
 */
public interface RandomIndustrialArea {

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

	 <T> RandomFactory<T> getRandomFactory(Class<T> createdClass)
			throws RandomException;

	/**
	 * Returns if there is a <code>RandomFactory</code> for the class.
	 * 
	 * @param <T>
	 *            - the type the factory is for
	 * @param createdClass
	 *            - the class that should be created
	 * @return true, if there is a random factory
	 * @throws RandomException
	 *             if none was fond
	 */
	<T> boolean containsRandomFactoryFor(Class<T> createdClass);

	/**
	 * Creates a single instance of the class. For all the attributes of this
	 * class, the factories of this area are asked, if they want to create it.
	 * If not, the normal procedure is used.
	 * 
	 * @param createdClazz
	 *            - the class to be created
	 * @return a single dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */
	
	default <T> T createSingle(Class<T> createdClass) throws RandomException {
		return createSingle(createdClass, new HashSet<Option>());
	}

	/**
	 * Creates a single instance of the class this factory is for. For all the
	 * attributes of this class, the factories of this area are asked, if they
	 * want to create it. If not, the normal procedure is used.
	 * 
	 * @param options
	 *            - some options for the values
	 * @return a single dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	<T> T createSingle(Class<T> createdClass, Set<Option> options)
			throws RandomException;

	/**
	 * Creates some instances of the class this factory is for. For all the
	 * attributes of this class, the factories of this area are asked, if they
	 * want to create it. If not, the normal procedure is used.
	 * 
	 * @param count
	 *            - number of instances to be created
	 * @return some dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	default <T> List<T> create(Class<T> createdClass, int count)
			throws RandomException {
		return create(createdClass, count, new HashSet<Option>());
	}


	/**
	 * Creates some instances of the class this factory is for. For all the
	 * attributes of this class, the factories of this area are asked, if they
	 * want to create it. If not, the normal procedure is used.
	 * 
	 * @param count
	 *            - number of instances to be created
	 * @param options
	 *            - some options for the values
	 * @return some dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	default <T> List<T> create(Class<T> createdClass, int count,
			Set<Option> options) throws RandomException {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < count; i++) {
			result.add(createSingle(createdClass, options));
		}
		return result;
	}
}