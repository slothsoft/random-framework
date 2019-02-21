package de.slothsoft.random.types;

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
