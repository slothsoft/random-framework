package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public class ArrayRandomFieldShortPrimitiveTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private short[] value;

		public short[] getValue() {
			return this.value;
		}

		public void setValue(short[] value) {
			this.value = value;
		}

	}

	public ArrayRandomFieldShortPrimitiveTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new ArrayRandomField(short.class, new ShortRandomField());
	}

}
