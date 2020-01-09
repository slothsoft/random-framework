package de.slothsoft.random.types;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link LocalTime} between 0:00 and 24:00 o'clock.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class LocalTimeRandomField extends AbstractChronoRandomField<LocalTime> {

	@Override
	LocalTime createDefaultStartValue() {
		return LocalTime.MIN;
	}

	@Override
	LocalTime createDefaultEndValue() {
		return LocalTime.MAX;
	}

	@Override
	long toLongValue(LocalTime value) {
		return value.getLong(ChronoField.NANO_OF_DAY);
	}

	@Override
	LocalTime fromLongValue(long date) {
		return LocalTime.ofNanoOfDay(date);
	}

	@Override
	public LocalTimeRandomField endValue(LocalTime newEndValue) {
		return (LocalTimeRandomField) super.endValue(newEndValue);
	}

	@Override
	public LocalTimeRandomField startValue(LocalTime newStartValue) {
		return (LocalTimeRandomField) super.startValue(newStartValue);
	}

}
