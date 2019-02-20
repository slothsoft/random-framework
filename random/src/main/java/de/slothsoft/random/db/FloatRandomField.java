package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.FLOAT;

public class FloatRandomField extends AbstractPrimitiveNumberRandomField {

	public FloatRandomField() {
		super(FLOAT);
	}

	@Override
	protected Class<Float> getNumberClass() {
		return Float.class;
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return float.class;
	}

	@Override
	protected Float getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		float diff = numberRangeEnd.floatValue()
				- numberRangeStart.floatValue();
		return RANDOM.nextFloat() * diff + numberRangeStart.floatValue();
	}
}
