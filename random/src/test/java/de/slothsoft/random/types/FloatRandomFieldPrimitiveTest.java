package de.slothsoft.random.types;

public class FloatRandomFieldPrimitiveTest extends AbstractNumberRandomFieldTest<Float> {

	public static class Pojo {

		private float value;

		public float getValue() {
			return this.value;
		}

		public void setValue(float value) {
			this.value = value;
		}

	}

	public FloatRandomFieldPrimitiveTest() {
		super(new FloatRandomFieldPrimitiveTest.Pojo(), Float.valueOf(7), Float.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Float> createRandomField() {
		return new FloatRandomField();
	}

}
