package de.slothsoft.random.types;

public class ShortTest extends AbstractNumberRandomFieldTest<Short> {

	public static class Pojo {

		private Short value;

		public Short getValue() {
			return this.value;
		}

		public void setValue(Short value) {
			this.value = value;
		}

	}

	public ShortTest() {
		super(new ShortTest.Pojo(), Short.valueOf((short) 7), Short.valueOf((short) 92));
	}

	@Override
	protected AbstractNumberRandomField<Short> createRandomField() {
		return new ShortRandomField();
	}
}
