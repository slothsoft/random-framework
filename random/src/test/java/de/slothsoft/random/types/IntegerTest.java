package de.slothsoft.random.types;

public class IntegerTest extends AbstractNumberRandomFieldTest<Integer> {

	public static class Pojo {

		private Integer value;

		public Integer getValue() {
			return this.value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

	public IntegerTest() {
		super(new IntegerTest.Pojo(), Integer.valueOf(7), Integer.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Integer> createRandomField() {
		return new IntegerRandomField();
	}
}
