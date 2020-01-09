package de.slothsoft.random.types;

import java.math.BigInteger;

public class BigIntegerRandomFieldTest extends AbstractNumberRandomFieldTest<BigInteger> {

	public static class Pojo {

		private BigInteger value;

		public BigInteger getValue() {
			return this.value;
		}

		public void setValue(BigInteger value) {
			this.value = value;
		}

	}

	public BigIntegerRandomFieldTest() {
		super(new BigIntegerRandomFieldTest.Pojo(), BigInteger.valueOf(7), BigInteger.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<BigInteger> createRandomField() {
		return new BigIntegerRandomField();
	}
}
