package de.slothsoft.random.types;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link LocalDateTime} between 10 years ago and 10 years in the future.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class LocalDateTimeRandomField extends AbstractChronoRandomField<LocalDateTime> {

	@Override
	LocalDateTime createDefaultStartValue() {
		return LocalDateTime.now().minusYears(10);
	}

	@Override
	LocalDateTime createDefaultEndValue() {
		return LocalDateTime.now().plusYears(10);
	}

	@Override
	long toLongValue(LocalDateTime value) {
		return value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	@Override
	LocalDateTime fromLongValue(long date) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), TimeZone.getDefault().toZoneId());
	}

	@Override
	public LocalDateTimeRandomField endValue(LocalDateTime newEndValue) {
		return (LocalDateTimeRandomField) super.endValue(newEndValue);
	}

	@Override
	public LocalDateTimeRandomField startValue(LocalDateTime newStartValue) {
		return (LocalDateTimeRandomField) super.startValue(newStartValue);
	}

}
