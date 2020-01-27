package de.slothsoft.random.types;

import java.lang.reflect.Array;
import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing an array.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class ArrayRandomField implements RandomField {

	private final Class<?> elementClass;
	private final RandomField elementRandomField;

	private double nullProbability;
	private int arrayLength = 3;

	/**
	 * Constructor.
	 * 
	 * @param elementClass the element's class
	 * @param elementRandomField a generator for the elements
	 */

	public ArrayRandomField(Class<?> elementClass, RandomField elementRandomField) {
		this.elementClass = Objects.requireNonNull(elementClass);
		this.elementRandomField = Objects.requireNonNull(elementRandomField);
	}

	@Override
	public Object nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		final int actualArrayLength = (int) Math.max(1, (1 + RND.nextGaussian()) * this.arrayLength);
		final Object result = Array.newInstance(this.elementClass, actualArrayLength);
		for (int i = 0; i < actualArrayLength; i++) {
			Array.set(result, i, this.elementRandomField.nextValue());
		}
		return result;
	}

	/**
	 * Returns the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @return the probability between 0 and 1
	 */

	public double getNullProbability() {
		return this.nullProbability;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param newNullProbability the probability between 0 and 1
	 * @return this instance
	 */

	public ArrayRandomField nullProbability(double newNullProbability) {
		setNullProbability(newNullProbability);
		return this;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param nullProbability the probability between 0 and 1
	 */

	public void setNullProbability(double nullProbability) {
		if (nullProbability < 0 || nullProbability > 1) {
			throw new IllegalArgumentException("Null probability must be between 0 and 1!");
		}
		this.nullProbability = nullProbability;
	}

	/**
	 * Returns the standard array length. A Gaussian function is used to scatter the
	 * actual array length around this value.
	 *
	 * @return array length
	 */

	public int getArrayLength() {
		return this.arrayLength;
	}

	/**
	 * Sets the standard array length. A Gaussian function is used to scatter the actual
	 * array length around this value.
	 *
	 * @param newArrayLength array length
	 * @return this instance
	 */

	public ArrayRandomField arrayLength(int newArrayLength) {
		setArrayLength(newArrayLength);
		return this;
	}

	/**
	 * Sets the standard array length. A Gaussian function is used to scatter the actual
	 * array length around this value.
	 *
	 * @param arrayLength array length
	 */

	public void setArrayLength(int arrayLength) {
		this.arrayLength = arrayLength;
	}

}
