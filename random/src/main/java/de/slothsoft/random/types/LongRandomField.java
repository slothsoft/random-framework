package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Long} or it's primitive counterpart.
 *
 * @author Steffi Schulz
 * @since 1.0.0
 */

public class LongRandomField extends AbstractPrimitiveNumberRandomField<Long> {

	private static final Long START = Long.valueOf(1);
	private static final Long END = Long.valueOf(100);

	public LongRandomField() {
		super(long.class, Long.class);
	}

	@Override
	protected Long getRandomNumber(Long numberRangeStart, Long numberRangeEnd) {
		final int diff = numberRangeEnd.intValue() - numberRangeStart.intValue();
		return Long.valueOf((int) (RND.nextDouble() * diff + numberRangeStart.intValue()));
	}

	@Override
	Long getDefaultRangeStart() {
		return START;
	}

	@Override
	Long getDefaultRangeEnd() {
		return END;
	}
}
