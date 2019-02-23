package de.slothsoft.random.types;

import java.math.BigDecimal;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link BigDecimal}. The default values are between
 * 1 and 100.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BigDecimalRandomField extends AbstractNumberRandomField<BigDecimal> {

	private static final BigDecimal START = BigDecimal.valueOf(1);
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
	BigDecimal getDefaultRangeStart() {
		return START;
	}

	@Override
	BigDecimal getDefaultRangeEnd() {
		return END;
	}

}
