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

	static final int YEAR = 0;
	static final int MONTH = 1;
	static final int DAY = 2;
	static final int HOUR = 3;
	static final int MINUTE = 4;
	static final int SECOND = 5;
	static final int ARRAY_LENGTH = 6;
	static final int[] UNITS_MULTIPLIER = {12, 30, 24, 60, 60};

	private C startValue;
	private C endValue;

	public AbstractChronoRandomField() {
		this.startValue = fromLongValue(Long.MIN_VALUE);
		this.endValue = fromLongValue(Long.MAX_VALUE);
	}

	@Override
	public C nextValue() {
		final C start = this.startValue;
		final C end = this.endValue;

		if (end.equals(start)) return start;

		final long startValueAsLong = toLongValue(start);
		final long endValueAsLong = toLongValue(end);

		final long result = startValueAsLong + (long) (RND.nextDouble() * (endValueAsLong - startValueAsLong));
		return fromLongValue(result);
	}

	abstract long toLongValue(C value);

	abstract C fromLongValue(long value);

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
	 * @return end value; never null
	 */

	public C getEndValue() {
		return this.endValue;
	}

	/**
	 * Sets the highest possible value for this field.
	 *
	 * @param newEndValue end value; cannot be null
	 * @return this instance; cannot be null
	 */

	public AbstractChronoRandomField<C> endValue(C newEndValue) {
		setEndValue(newEndValue);
		return this;
	}

	/**
	 * Sets the highest possible value for this field.
	 *
	 * @param endValue end value; cannot be null
	 */

	public void setEndValue(C endValue) {
		this.endValue = Objects.requireNonNull(endValue);
	}

	/**
	 * Returns the lowest possible value for this field.
	 *
	 * @return start value; never null
	 */

	public C getStartValue() {
		return this.startValue;
	}

	/**
	 * Sets the lowest possible value for this field.
	 *
	 * @param newStartValue start value
	 * @return this instance; cannot be null
	 */

	public AbstractChronoRandomField<C> startValue(C newStartValue) {
		setStartValue(newStartValue);
		return this;
	}

	/**
	 * Sets the lowest possible value for this field.
	 *
	 * @param startValue start value; cannot be null
	 */

	public void setStartValue(C startValue) {
		this.startValue = Objects.requireNonNull(startValue);
	}

}
