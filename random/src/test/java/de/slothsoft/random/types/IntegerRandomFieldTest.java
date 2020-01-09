package de.slothsoft.random.types;

public class IntegerRandomFieldTest extends AbstractNumberRandomFieldTest<Integer> {

	public static class Pojo {

		private Integer value;

		public Integer getValue() {
			return this.value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

	public IntegerRandomFieldTest() {
		super(new IntegerRandomFieldTest.Pojo(), Integer.valueOf(7), Integer.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Integer> createRandomField() {
		return new IntegerRandomField();
	}
}
