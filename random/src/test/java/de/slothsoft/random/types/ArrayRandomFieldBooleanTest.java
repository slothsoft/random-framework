package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public class ArrayRandomFieldBooleanTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Boolean[] value;

		public Boolean[] getValue() {
			return this.value;
		}

		public void setValue(Boolean[] value) {
			this.value = value;
		}

	}

	public ArrayRandomFieldBooleanTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new ArrayRandomField(Boolean.class, new BooleanRandomField());
	}

}
