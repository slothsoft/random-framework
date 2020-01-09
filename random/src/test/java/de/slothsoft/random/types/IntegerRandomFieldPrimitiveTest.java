package de.slothsoft.random.types;

public class IntegerRandomFieldPrimitiveTest extends AbstractNumberRandomFieldTest<Integer> {

	public static class Pojo {

		private int value;

		public int getValue() {
			return this.value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}

	public IntegerRandomFieldPrimitiveTest() {
		super(new IntegerRandomFieldPrimitiveTest.Pojo(), Integer.valueOf(7), Integer.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Integer> createRandomField() {
		return new IntegerRandomField();
	}

}
