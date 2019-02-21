package de.slothsoft.random.types;

import java.math.BigInteger;

public class BigIntegerRandomField extends AbstractNumberRandomField<BigInteger> {

	private static final BigInteger START = BigInteger.ZERO;
	private static final BigInteger END = BigInteger.valueOf(100);

	public BigIntegerRandomField() {
		super(BigInteger.class);
	}

	@Override
	protected BigInteger getRandomNumber(BigInteger numberRangeStart, BigInteger numberRangeEnd) {
		final long diff = numberRangeEnd.longValue() - numberRangeStart.longValue();
		final long result = (long) RND.nextDouble() * diff + numberRangeStart.longValue();
		return BigInteger.valueOf(result);
	}

	@Override
	BigInteger getDefaultNumberRangeStart() {
		return START;
	}

	@Override
	BigInteger getDefaultNumberRangeEnd() {
		return END;
	}

}
