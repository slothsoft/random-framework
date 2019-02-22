package de.slothsoft.random.types;

import java.math.BigInteger;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link BigInteger}.
 *
 * @author Steffi Schulz
 * @since 1.0.0
 */

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
	BigInteger getDefaultRangeStart() {
		return START;
	}

	@Override
	BigInteger getDefaultRangeEnd() {
		return END;
	}

}
