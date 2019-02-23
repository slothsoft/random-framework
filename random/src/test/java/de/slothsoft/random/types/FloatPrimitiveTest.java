package de.slothsoft.random.types;

public class FloatPrimitiveTest extends AbstractNumberRandomFieldTest<Float> {

	public static class Pojo {

		private float value;

		public float getValue() {
			return this.value;
		}

		public void setValue(float value) {
			this.value = value;
		}

	}

	public FloatPrimitiveTest() {
		super(new FloatPrimitiveTest.Pojo(), Float.valueOf(7), Float.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Float> createRandomField() {
		return new FloatRandomField();
	}

}
