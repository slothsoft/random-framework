package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.BIRTHDAY;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import de.slothsoft.random.Option;

public class BirthdayRandomField extends AbstractSynonymRandomField<Date> {

	protected static final int CURRENT_YEAR = Calendar.getInstance().get(
			Calendar.YEAR);

	public BirthdayRandomField() {
		this(BIRTHDAY);
	}

	public BirthdayRandomField(String displayName) {
		super(displayName);
	}

	@Override
	public Date getRandomAttribute(String attribute, Class<?> attributeClass,
			Set<Option> options) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				RANDOM.nextInt(getEndYear() - getStartYear()) + getStartYear());
		calendar.set(Calendar.MONTH, RANDOM.nextInt(12));
		calendar.set(Calendar.DAY_OF_MONTH, RANDOM.nextInt(calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH)));
		return calendar.getTime();
	}

	protected int getEndYear() {
		return CURRENT_YEAR - 1;
	}

	protected int getStartYear() {
		return CURRENT_YEAR - 100;
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class[] { Date.class };
	}
}
