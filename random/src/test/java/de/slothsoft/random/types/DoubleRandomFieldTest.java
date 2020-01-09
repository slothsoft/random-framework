package de.slothsoft.random.types;

public class DoubleRandomFieldTest extends AbstractNumberRandomFieldTest<Double> {

	public static class Pojo {

		private Double value;

		public Double getValue() {
			return this.value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

	}

	public DoubleRandomFieldTest() {
		super(new DoubleRandomFieldTest.Pojo(), Double.valueOf(7), Double.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Double> createRandomField() {
		return new DoubleRandomField();
	}
}
