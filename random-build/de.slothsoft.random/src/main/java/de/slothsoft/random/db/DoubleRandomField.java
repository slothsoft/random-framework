package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.DOUBLE;

public class DoubleRandomField extends AbstractPrimitiveNumberRandomField {

	public DoubleRandomField() {
		super(DOUBLE);
	}

	@Override
	protected Class<Double> getNumberClass() {
		return Double.class;
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return double.class;
	}

	@Override
	protected Double getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		double diff = numberRangeEnd.doubleValue()
				- numberRangeStart.doubleValue();
		return RANDOM.nextDouble() * diff + numberRangeStart.doubleValue();
	}
}
