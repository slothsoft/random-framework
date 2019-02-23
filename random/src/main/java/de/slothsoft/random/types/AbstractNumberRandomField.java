package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A base class for creating a {@link RandomField} from a {@link Number}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 * @param <N> - a type of number
 */

public abstract class AbstractNumberRandomField<N extends Number> implements RandomField {

	private N startValue;
	private N endValue;

	@Override
	public N nextValue() {
		N start = this.startValue == null ? getDefaultRangeStart() : this.startValue;
		N end = this.endValue == null ? getDefaultRangeEnd() : this.endValue;

		if (end.equals(start)) return start;

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

	public N getStartValue() {
		return this.startValue;
	}

	public AbstractNumberRandomField<N> startValue(N newStartValue) {
		setStartValue(newStartValue);
		return this;
	}

	public void setStartValue(N startValue) {
		this.startValue = startValue;
	}

	public N getEndValue() {
		return this.endValue;
	}

	public AbstractNumberRandomField<N> endValue(N newEndValue) {
		setEndValue(newEndValue);
		return this;
	}

	public void setEndValue(N endValue) {
		this.endValue = endValue;
	}

}
