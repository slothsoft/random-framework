package de.slothsoft.random.types;

import java.util.Calendar;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a birthday, i.e. a date between last year and 100
 * years ago.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class BirthdayRandomField extends DateRandomField {

	public BirthdayRandomField() {
		final Calendar now = Calendar.getInstance();
		final int currentYear = now.get(Calendar.YEAR);

		now.set(Calendar.YEAR, currentYear - 100);
		setStartValue(now.getTime());

		now.set(Calendar.YEAR, currentYear - 1);
		setEndValue(now.getTime());
	}

}
