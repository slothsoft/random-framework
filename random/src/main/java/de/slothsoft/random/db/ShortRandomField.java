package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.SHORT;

public class ShortRandomField extends AbstractPrimitiveNumberRandomField {

	public ShortRandomField() {
		super(SHORT);
	}

	@Override
	protected Class<Short> getNumberClass() {
		return Short.class;
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return short.class;
	}

	@Override
	protected Short getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		int diff = numberRangeEnd.shortValue() - numberRangeStart.shortValue();
		return (short) (RANDOM.nextDouble() * diff + numberRangeStart
				.shortValue());
	}

}
