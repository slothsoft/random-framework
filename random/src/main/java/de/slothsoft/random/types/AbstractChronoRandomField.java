package de.slothsoft.random.types;

import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a date, time, date time or anything time related.
 *
 * @author Stef Schulz
 * @since 2.1.0
 * @param <C> the date and / or time
 */

abstract class AbstractChronoRandomField<C> implements RandomField {

	private C startValue;
	private C endValue;
	private double nullProbability;

	@Override
	public C nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}

		final C start = this.startValue == null ? createDefaultStartValue() : this.startValue;
		final C end = this.endValue == null ? createDefaultEndValue() : this.endValue;

		if (end.equals(start)) {
			return start;
		}

		final long startValueAsLong = toLongValue(start);
		final long endValueAsLong = toLongValue(end);

		final long result = startValueAsLong + (long) (RND.nextDouble() * (endValueAsLong - startValueAsLong));
		return fromLongValue(result);
	}

	abstract long toLongValue(C value);

	abstract C fromLongValue(long value);

	abstract C createDefaultStartValue();

	abstract C createDefaultEndValue();

	boolean isBefore(C value1, C value2) {
		return toLongValue(value1) < toLongValue(value2);
	}

	boolean isEqual(C value1, C value2) {
		return toLongValue(value1) == toLongValue(value2);
	}

	boolean isAfter(C value1, C value2) {
		return toLongValue(value1) > toLongValue(value2);
	}

	/**
	 * Returns the highest possible value for this field.
	 *
	 * @return end value
	 */

	public C getEndValue() {
		return this.endValue;
	}

	/**
	 * Sets the highest possible value for this field.
	 *
	 * @param newEndValue end value
	 * @return this instance
	 */

	public AbstractChronoRandomField<C> endValue(C newEndValue) {
		setEndValue(newEndValue);
		return this;
	}

	/**
	 * Sets the highest possible value for this field.
	 *
	 * @param endValue end value
	 */

	public void setEndValue(C endValue) {
		this.endValue = Objects.requireNonNull(endValue);
	}

	/**
	 * Returns the lowest possible value for this field.
	 *
	 * @return start value
	 */

	public C getStartValue() {
		return this.startValue;
	}

	/**
	 * Sets the lowest possible value for this field.
	 *
	 * @param newStartValue start value
	 * @return this instance
	 */

	public AbstractChronoRandomField<C> startValue(C newStartValue) {
		setStartValue(newStartValue);
		return this;
	}

	/**
	 * Sets the lowest possible value for this field.
	 *
	 * @param startValue start value
	 */

	public void setStartValue(C startValue) {
		this.startValue = Objects.requireNonNull(startValue);
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

	public AbstractChronoRandomField<C> nullProbability(double newNullProbability) {
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
