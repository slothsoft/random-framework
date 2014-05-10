package de.slothsoft.random;

import java.util.List;
import java.util.Set;

public interface RandomFactory<T> {

	/**
	 * Creates a single instance of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @return a single dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	public T createSingle() throws RandomException;

	/**
	 * Creates a single instance of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param options
	 *            - some options for the values
	 * @return a single dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	public T createSingle(Set<Option> options) throws RandomException;

	/**
	 * Creates some instances of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param count
	 *            - number of instances to be created
	 * @return some dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	public List<T> create(int count);

	/**
	 * Creates some instances of the class this factory is for. Should call
	 * <code>fillAttributes()</code> at some point.
	 * 
	 * @param count
	 *            - number of instances to be created
	 * @param options
	 *            - some options for the values
	 * @return some dummy instance
	 * @throws RandomException
	 *             - if something went wrong
	 */

	public List<T> create(int count, Set<Option> options)
			throws RandomException;

	/**
	 * Returns the class this factory is for
	 */

	public Class<T> getCreatedClass();

}