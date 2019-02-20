package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.LONG;

public class LongRandomField extends AbstractPrimitiveNumberRandomField {

	public LongRandomField() {
		super(LONG);
	}

	@Override
	protected Class<Long> getNumberClass() {
		return Long.class;
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return long.class;
	}

	@Override
	protected Long getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		long diff = numberRangeEnd.longValue() - numberRangeStart.longValue();
		return (long) RANDOM.nextDouble() * diff + numberRangeStart.longValue();
	}
}
