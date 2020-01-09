package de.slothsoft.random.types;

public class FloatRandomFieldTest extends AbstractNumberRandomFieldTest<Float> {

	public static class Pojo {

		private Float value;

		public Float getValue() {
			return this.value;
		}

		public void setValue(Float value) {
			this.value = value;
		}

	}

	public FloatRandomFieldTest() {
		super(new FloatRandomFieldTest.Pojo(), Float.valueOf(7), Float.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Float> createRandomField() {
		return new FloatRandomField();
	}
}
