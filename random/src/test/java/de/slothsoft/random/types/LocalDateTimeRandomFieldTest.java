package de.slothsoft.random.types;

import java.time.LocalDateTime;

public class LocalDateTimeRandomFieldTest extends AbstractChronoRandomFieldTest<LocalDateTime> {

	public static class Pojo {

		private LocalDateTime value;

		public LocalDateTime getValue() {
			return this.value;
		}

		public void setValue(LocalDateTime value) {
			this.value = value;
		}

	}

	public LocalDateTimeRandomFieldTest() {
		super(new Pojo(), LocalDateTime.of(2018, 3, 7, 15, 5), LocalDateTime.of(2020, 1, 5, 1, 50));
	}


	@Override
	protected LocalDateTimeRandomField createRandomField() {
		return new LocalDateTimeRandomField().startValue(LocalDateTime.of(2010, 2, 1, 23, 5)).endValue(LocalDateTime.of(2030, 1, 1, 0, 7));
	}

}
