package de.slothsoft.random.types;

import java.util.Calendar;
import java.util.Date;

import de.slothsoft.random.RandomField;

public class DateRandomField implements RandomField<Date> {

	static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	@Override
	public Date nextValue() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, RND.nextInt(getEndYear() - getStartYear()) + getStartYear());
		calendar.set(Calendar.MONTH, RND.nextInt(12));
		calendar.set(Calendar.DAY_OF_MONTH, RND.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
		return calendar.getTime();
	}

	int getEndYear() {
		return CURRENT_YEAR + 10;
	}

	int getStartYear() {
		return CURRENT_YEAR - 10;
	}

	@Override
	public Class<?> getFieldClass() {
		return Date.class;
	}
}
