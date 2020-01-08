package de.slothsoft.random.types;

import java.util.Calendar;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Calendar} between 10 years ago and 10 years
 * in the future.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class CalendarRandomField extends AbstractChronoRandomField<Calendar> {

	/**
	 * Default constructor.
	 */

	public CalendarRandomField() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);

		now.set(Calendar.YEAR, currentYear - 10);
		setStartValue((Calendar) now.clone());

		now.set(Calendar.YEAR, currentYear + 10);
		setEndValue((Calendar) now.clone());
	}

	@Override
	long toLongValue(Calendar value) {
		return value.getTimeInMillis();
	}

	@Override
	Calendar fromLongValue(long date) {
		final Calendar result = Calendar.getInstance();
		result.setTimeInMillis(date);
		return result;
	}

	@Override
	public CalendarRandomField endValue(Calendar newEndValue) {
		return (CalendarRandomField) super.endValue(newEndValue);
	}

	@Override
	public CalendarRandomField startValue(Calendar newStartValue) {
		return (CalendarRandomField) super.startValue(newStartValue);
	}

}
