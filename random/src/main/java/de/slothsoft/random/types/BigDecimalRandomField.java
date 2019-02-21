package de.slothsoft.random.types;

import java.math.BigDecimal;

public class BigDecimalRandomField extends AbstractNumberRandomField<BigDecimal> {

	private static final BigDecimal START = BigDecimal.ZERO;
	private static final BigDecimal END = BigDecimal.valueOf(100L);

	public BigDecimalRandomField() {
		super(BigDecimal.class);
	}

	@Override
	protected BigDecimal getRandomNumber(BigDecimal numberRangeStart, BigDecimal numberRangeEnd) {
		final long diff = numberRangeEnd.longValue() - numberRangeStart.longValue();
		final long result = (long) RND.nextDouble() * diff + numberRangeStart.longValue();
		return BigDecimal.valueOf(result);
	}

	@Override
	BigDecimal getDefaultNumberRangeStart() {
		return START;
	}

	@Override
	BigDecimal getDefaultNumberRangeEnd() {
		return END;
	}

}
