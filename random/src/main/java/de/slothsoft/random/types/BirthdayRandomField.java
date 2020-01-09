package de.slothsoft.random.types;

import java.util.Calendar;
import java.util.Date;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a birthday, i.e. a date between last year and 100
 * years ago.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BirthdayRandomField extends DateRandomField {

	@Override
	Date createDefaultStartValue() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);
		now.set(Calendar.YEAR, currentYear - 100);
		return now.getTime();
	}

	@Override
	Date createDefaultEndValue() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);
		now.set(Calendar.YEAR, currentYear - 1);
		return now.getTime();
	}

	@Override
	public BirthdayRandomField endValue(Date newEndValue) {
		return (BirthdayRandomField) super.endValue(newEndValue);
	}

	@Override
	public BirthdayRandomField startValue(Date newStartValue) {
		return (BirthdayRandomField) super.startValue(newStartValue);
	}

}
