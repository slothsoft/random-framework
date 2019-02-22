package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Integer} or it's primitive counterpart.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class IntegerRandomField extends AbstractPrimitiveNumberRandomField<Integer> {

	private static final Integer START = Integer.valueOf(1);
	private static final Integer END = Integer.valueOf(100);

	public IntegerRandomField() {
		super(int.class, Integer.class);
	}

	@Override
	protected Integer getRandomNumber(Integer numberRangeStart, Integer numberRangeEnd) {
		final int diff = numberRangeEnd.intValue() - numberRangeStart.intValue();
		return Integer.valueOf((int) (RND.nextDouble() * diff + numberRangeStart.intValue()));
	}

	@Override
	Integer getDefaultRangeStart() {
		return START;
	}

	@Override
	Integer getDefaultRangeEnd() {
		return END;
	}

}
