package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.DATE;

public class DateRandomField extends BirthdayRandomField {

	public DateRandomField() {
		this(DATE);
	}

	public DateRandomField(String displayName) {
		super(displayName);
	}

	@Override
	protected int getEndYear() {
		return CURRENT_YEAR + 10;
	}

	@Override
	protected int getStartYear() {
		return CURRENT_YEAR - 10;
	}

}
