package de.slothsoft.random.types;

import java.time.LocalDate;

public class LocalDateRandomFieldTest extends AbstractChronoRandomFieldTest<LocalDate> {

	public static class Pojo {

		private LocalDate value;

		public LocalDate getValue() {
			return this.value;
		}

		public void setValue(LocalDate value) {
			this.value = value;
		}

	}

	public LocalDateRandomFieldTest() {
		super(new Pojo(), LocalDate.of(2018, 3, 7), LocalDate.of(2020, 1, 5));
	}

	@Override
	protected LocalDateRandomField createRandomField() {
		return new LocalDateRandomField().startValue(LocalDate.of(2010, 2, 1)).endValue(LocalDate.of(2030, 1, 1));
	}

}
