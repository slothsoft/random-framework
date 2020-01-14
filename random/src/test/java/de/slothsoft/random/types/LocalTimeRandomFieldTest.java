package de.slothsoft.random.types;

import java.time.LocalTime;

public class LocalTimeRandomFieldTest extends AbstractChronoRandomFieldTest<LocalTime> {

	public static class Pojo {

		private LocalTime value;

		public LocalTime getValue() {
			return this.value;
		}

		public void setValue(LocalTime value) {
			this.value = value;
		}

	}

	public LocalTimeRandomFieldTest() {
		super(new Pojo(), LocalTime.of(1, 5), LocalTime.of(15, 50));
	}

	@Override
	protected LocalTimeRandomField createRandomField() {
		return new LocalTimeRandomField();
	}

}
