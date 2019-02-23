package de.slothsoft.random.types;

public class DoublePrimitiveTest extends AbstractNumberRandomFieldTest<Double> {

	public static class Pojo {

		private double value;

		public double getValue() {
			return this.value;
		}

		public void setValue(double value) {
			this.value = value;
		}

	}

	public DoublePrimitiveTest() {
		super(new DoublePrimitiveTest.Pojo(), Double.valueOf(7), Double.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Double> createRandomField() {
		return new DoubleRandomField();
	}

}
