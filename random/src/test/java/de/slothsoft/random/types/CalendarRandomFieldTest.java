package de.slothsoft.random.types;

import java.util.Calendar;

public class CalendarRandomFieldTest extends AbstractChronoRandomFieldTest<Calendar> {

	public static class Pojo {

		private Calendar value;

		public Calendar getValue() {
			return this.value;
		}

		public void setValue(Calendar value) {
			this.value = value;
		}

	}

	public CalendarRandomFieldTest() {
		super(new Pojo(), toCalendar(2018, 1, 7), toCalendar(2020, 1, 5));
	}

	private static Calendar toCalendar(int y, int m, int d) {
		final Calendar result = Calendar.getInstance();
		result.set(Calendar.YEAR, y);
		result.set(Calendar.MONTH, m);
		result.set(Calendar.DATE, d);
		return result;
	}

	@Override
	protected CalendarRandomField createRandomField() {
		return new CalendarRandomField().startValue(toCalendar(2010, 0, 1)).endValue(toCalendar(2030, 0, 1));
	}

}
