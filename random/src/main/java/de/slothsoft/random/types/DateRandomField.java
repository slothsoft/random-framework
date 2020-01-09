package de.slothsoft.random.types;

import java.util.Calendar;
import java.util.Date;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Date} between 10 years ago and 10 years in
 * the future.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class DateRandomField extends AbstractChronoRandomField<Date> {

	@Override
	Date createDefaultStartValue() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);
		now.set(Calendar.YEAR, currentYear - 10);
		return now.getTime();
	}

	@Override
	Date createDefaultEndValue() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);
		now.set(Calendar.YEAR, currentYear + 10);
		return now.getTime();
	}

	@Override
	long toLongValue(Date value) {
		return value.getTime();
	}

	@Override
	Date fromLongValue(long date) {
		return new Date(date);
	}

	@Override
	public DateRandomField endValue(Date newEndValue) {
		return (DateRandomField) super.endValue(newEndValue);
	}

	@Override
	public DateRandomField startValue(Date newStartValue) {
		return (DateRandomField) super.startValue(newStartValue);
	}
}
