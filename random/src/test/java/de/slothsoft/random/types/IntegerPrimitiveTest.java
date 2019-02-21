package de.slothsoft.random.types;

public class IntegerPrimitiveTest extends AbstractNumberRandomFieldTest<Integer> {

	public static class Pojo {

		private int value;

		public int getValue() {
			return this.value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}

	public IntegerPrimitiveTest() {
		super(new IntegerPrimitiveTest.Pojo(), Integer.valueOf(7), Integer.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Integer> createRandomField() {
		return new IntegerRandomField().primitive(true);
	}

}
