package de.slothsoft.random.types;

import java.math.BigDecimal;

public class BigDecimalRandomFieldTest extends AbstractNumberRandomFieldTest<BigDecimal> {

	public static class Pojo {

		private BigDecimal value;

		public BigDecimal getValue() {
			return this.value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

	}

	public BigDecimalRandomFieldTest() {
		super(new BigDecimalRandomFieldTest.Pojo(), BigDecimal.valueOf(7), BigDecimal.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<BigDecimal> createRandomField() {
		return new BigDecimalRandomField();
	}
}
