package de.slothsoft.random.types;

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
	int getEndYear() {
		return CURRENT_YEAR - 1;
	}

	@Override
	int getStartYear() {
		return CURRENT_YEAR - 100;
	}

}
