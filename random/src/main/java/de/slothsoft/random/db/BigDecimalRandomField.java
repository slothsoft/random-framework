package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.BIG_DECIMAL;

import java.math.BigDecimal;

public class BigDecimalRandomField extends AbstractNumberRandomField {

	public BigDecimalRandomField() {
		super(BIG_DECIMAL);
	}

	@Override
	protected BigDecimal getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd) {
		long diff = numberRangeEnd.longValue() - numberRangeStart.longValue();
		long result = (long) RANDOM.nextDouble() * diff
				+ numberRangeStart.longValue();
		return BigDecimal.valueOf(result);
	}

	@Override
	protected Class<? extends Number> getNumberClass() {
		return BigDecimal.class;
	}

}
