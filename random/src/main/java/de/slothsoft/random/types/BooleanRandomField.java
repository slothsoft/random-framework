package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Boolean} or it's primitive counterpart.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BooleanRandomField implements RandomField {

	private double nullProbability;
	private double trueProbability;

	@Override
	public Boolean nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		return Boolean.valueOf(RND.nextDouble() < this.trueProbability);
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

	public BooleanRandomField nullProbability(double newNullProbability) {
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
	 * Returns the probability for this field returning true. If the value is 0 then no
	 * {@link #nextValue()} is true, if it is 1 then every {@link #nextValue()} is true.
	 *
	 * @return the probability between 0 and 1
	 */

	public double getTrueProbability() {
		return this.trueProbability;
	}

	/**
	 * Sets the probability for this field returning true. If the value is 0 then no
	 * {@link #nextValue()} is true, if it is 1 then every {@link #nextValue()} is true.
	 *
	 * @param newTrueProbability the probability between 0 and 1
	 * @return this instance
	 */

	public BooleanRandomField trueProbability(double newTrueProbability) {
		setTrueProbability(newTrueProbability);
		return this;
	}

	/**
	 * Sets the probability for this field returning true. If the value is 0 then no
	 * {@link #nextValue()} is true, if it is 1 then every {@link #nextValue()} is true.
	 *
	 * @param trueProbability the probability between 0 and 1
	 */

	public void setTrueProbability(double trueProbability) {
		if (trueProbability < 0 || trueProbability > 1) {
			throw new IllegalArgumentException("True probability must be between 0 and 1!");
		}
		this.trueProbability = trueProbability;
	}
}
