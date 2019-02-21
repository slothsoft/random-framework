package de.slothsoft.random.types;

import java.math.BigInteger;

public class BigIntegerTest extends AbstractNumberRandomFieldTest<BigInteger> {

	public static class Pojo {

		private BigInteger value;

		public BigInteger getValue() {
			return this.value;
		}

		public void setValue(BigInteger value) {
			this.value = value;
		}

	}

	public BigIntegerTest() {
		super(new BigIntegerTest.Pojo(), BigInteger.valueOf(7), BigInteger.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<BigInteger> createRandomField() {
		return new BigIntegerRandomField();
	}
}
