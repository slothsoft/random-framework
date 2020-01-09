package de.slothsoft.random.types;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link LocalDate} between 10 years ago and 10 years in the future.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class LocalDateRandomField extends AbstractChronoRandomField<LocalDate> {


	@Override
	LocalDate createDefaultStartValue() {
		return LocalDate.now().minusYears(10);
	}

	@Override
	LocalDate createDefaultEndValue() {
		return LocalDate.now().plusYears(10);
	}

	@Override
	long toLongValue(LocalDate value) {
		return value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	@Override
	LocalDate fromLongValue(long date) {
		return LocalDate.ofInstant(Instant.ofEpochMilli(date), TimeZone.getDefault().toZoneId());
	}

	@Override
	public LocalDateRandomField endValue(LocalDate newEndValue) {
		return (LocalDateRandomField) super.endValue(newEndValue);
	}

	@Override
	public LocalDateRandomField startValue(LocalDate newStartValue) {
		return (LocalDateRandomField) super.startValue(newStartValue);
	}

}
