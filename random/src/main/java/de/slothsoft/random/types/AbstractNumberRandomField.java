package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public abstract class AbstractNumberRandomField<N extends Number> implements RandomField<N> {

	private final Class<?> fieldClass;

	private N startValue;
	private N endValue;

	public AbstractNumberRandomField(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}

	@Override
	public Class<?> getFieldClass() {
		return this.fieldClass;
	}

	@Override
	public N nextValue() {
		N start = this.startValue == null ? getDefaultNumberRangeStart() : this.startValue;
		N end = this.endValue == null ? getDefaultNumberRangeEnd() : this.endValue;

		if (end.equals(start)) return start;

		if (end.doubleValue() < start.doubleValue()) {
			final N dummy = start;
			start = end;
			end = dummy;
		}
		return getRandomNumber(start, end);
	}

	protected abstract N getRandomNumber(N numberRangeStart, N numberRangeEnd);

	abstract N getDefaultNumberRangeStart();

	abstract N getDefaultNumberRangeEnd();

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
