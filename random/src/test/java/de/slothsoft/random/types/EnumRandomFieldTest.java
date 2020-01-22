package de.slothsoft.random.types;

import java.time.DayOfWeek;

import de.slothsoft.random.RandomField;

public class EnumRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private DayOfWeek value;

		public DayOfWeek getValue() {
			return this.value;
		}

		public void setValue(DayOfWeek value) {
			this.value = value;
		}

	}

	public EnumRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new EnumRandomField<>(DayOfWeek.class);
	}
}
