package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.INTEGER;

public class IntegerRandomField extends AbstractPrimitiveNumberRandomField {

	public IntegerRandomField() {
		super(INTEGER);
	}

	@Override
	protected Class<Integer> getNumberClass() {
		return Integer.class;
	}

	@Override
	protected Class<?> getPrimitiveClass() {
		return int.class;
	}

	@Override
	protected Integer getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		int diff = numberRangeEnd.intValue() - numberRangeStart.intValue();
		return RANDOM.nextInt(diff) + numberRangeStart.intValue();
	}

}
