package de.slothsoft.random.types;

import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing an arbitrary object that is filled with a random
 * list element.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

public class ElementFromListRandomField<E> implements RandomField {

	private final E[] possibleElements;
	double nullProbability;

	/**
	 * Some constructor.
	 *
	 * @param possibleElements the elements that are possible here; never null
	 */

	public ElementFromListRandomField(E[] possibleElements) {
		this.possibleElements = Objects.requireNonNull(possibleElements);
	}

	@Override
	public E nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		return this.possibleElements[RND.nextInt(this.possibleElements.length)];
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

	public ElementFromListRandomField<E> nullProbability(double newNullProbability) {
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

}
