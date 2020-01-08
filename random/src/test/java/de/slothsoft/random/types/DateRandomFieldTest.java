package de.slothsoft.random.types;

import java.util.Date;

public class DateRandomFieldTest extends AbstractChronoRandomFieldTest<Date> {

	public static class Pojo {

		private Date value;

		public Date getValue() {
			return this.value;
		}

		public void setValue(Date value) {
			this.value = value;
		}

	}

	@SuppressWarnings("deprecation")
	public DateRandomFieldTest() {
		super(new Pojo(), new Date(118, 1, 7, 15, 10, 42), new Date(120, 1, 5, 7, 59, 30));
	}

	@Override
	@SuppressWarnings("deprecation")
	protected AbstractChronoRandomField<Date> createRandomField() {
		return new DateRandomField().startValue(new Date(110, 0, 1)).endValue(new Date(130, 0, 1));
	}

}
