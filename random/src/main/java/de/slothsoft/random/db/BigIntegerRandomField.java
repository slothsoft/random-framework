package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.BIG_INTEGER;

import java.math.BigInteger;

public class BigIntegerRandomField extends AbstractNumberRandomField {

	public BigIntegerRandomField() {
		super(BIG_INTEGER);
	}

	@Override
	protected BigInteger getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		long diff = numberRangeEnd.longValue() - numberRangeStart.longValue();
		long result = (long) RANDOM.nextDouble() * diff
				+ numberRangeStart.longValue();
		return BigInteger.valueOf(result);
	}

	@Override
	protected Class<? extends Number> getNumberClass() {
		return BigInteger.class;
	}

}
