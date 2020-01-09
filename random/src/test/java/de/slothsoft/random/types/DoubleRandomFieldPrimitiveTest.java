package de.slothsoft.random.types;

public class DoubleRandomFieldPrimitiveTest extends AbstractNumberRandomFieldTest<Double> {

	public static class Pojo {

		private double value;

		public double getValue() {
			return this.value;
		}

		public void setValue(double value) {
			this.value = value;
		}

	}

	public DoubleRandomFieldPrimitiveTest() {
		super(new DoubleRandomFieldPrimitiveTest.Pojo(), Double.valueOf(7), Double.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Double> createRandomField() {
		return new DoubleRandomField();
	}

}
