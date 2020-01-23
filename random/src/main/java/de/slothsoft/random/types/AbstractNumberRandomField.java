package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A base class for creating a {@link RandomField} from a {@link Number}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 * @param <N> a type of number
 */

public abstract class AbstractNumberRandomField<N extends Number> implements RandomField {

	private N startValue;
	private N endValue;

	@Override
	public N nextValue() {
		N start = this.startValue == null ? getDefaultRangeStart() : this.startValue;
		N end = this.endValue == null ? getDefaultRangeEnd() : this.endValue;

		if (end.equals(start)) {
			return start;
		}

		if (end.doubleValue() < start.doubleValue()) {
			final N dummy = start;
			start = end;
			end = dummy;
		}
		return getRandomNumber(start, end);
	}

	protected abstract N getRandomNumber(N numberRangeStart, N numberRangeEnd);

	abstract N getDefaultRangeStart();

	abstract N getDefaultRangeEnd();

	/**
	 * Returns the start value, i.e. {@link #nextValue} will always be greater than this value.
	 *
	 * @return the start value
	 */

	public N getStartValue() {
		return this.startValue;
	}

	/**
	 * Sets the start value, i.e. {@link #nextValue} will always be greater than this value.
	 *
	 * @param newStartValue the start value
	 * @return this instance
	 */

	public AbstractNumberRandomField<N> startValue(N newStartValue) {
		setStartValue(newStartValue);
		return this;
	}

	/**
	 * Sets the start value, i.e. {@link #nextValue} will always be greater than this value.
	 *
	 * @param startValue the start value
	 */

	public void setStartValue(N startValue) {
		this.startValue = startValue;
	}

	/**
	 * Returns the end value, i.e. {@link #nextValue} will always be less than this value.
	 *
	 * @return the end value
	 */

	public N getEndValue() {
		return this.endValue;
	}

	/**
	 * Sets the end value, i.e. {@link #nextValue} will always be less than this value.
	 *
	 * @param newEndValue the end value
	 * @return this instance
	 */

	public AbstractNumberRandomField<N> endValue(N newEndValue) {
		setEndValue(newEndValue);
		return this;
	}

	/**
	 * Sets the end value, i.e. {@link #nextValue} will always be less than this value.
	 *
	 * @param endValue the end value
	 */

	public void setEndValue(N endValue) {
		this.endValue = endValue;
	}

}
