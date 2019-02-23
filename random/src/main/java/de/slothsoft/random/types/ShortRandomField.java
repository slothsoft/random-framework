package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Short} or its primitive counterpart. The
 * default values are between 1 and 100.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class ShortRandomField extends AbstractNumberRandomField<Short> {

	private static final Short START = Short.valueOf((short) 1);
	private static final Short END = Short.valueOf((short) 100);

	public ShortRandomField() {
		super(Short.class);
	}

	@Override
	protected Short getRandomNumber(Short numberRangeStart, Short numberRangeEnd) {
		final int diff = numberRangeEnd.intValue() - numberRangeStart.intValue();
		return Short.valueOf((short) (RND.nextDouble() * diff + numberRangeStart.intValue()));
	}

	@Override
	Short getDefaultRangeStart() {
		return START;
	}

	@Override
	Short getDefaultRangeEnd() {
		return END;
	}
}
