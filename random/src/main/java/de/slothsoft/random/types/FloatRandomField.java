package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Float} or its primitive counterpart. The
 * default values are between 1 and 100.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class FloatRandomField extends AbstractNumberRandomField<Float> {

	private static final Float START = Float.valueOf(1);
	private static final Float END = Float.valueOf(100);

	public FloatRandomField() {
		super(Float.class);
	}

	@Override
	protected Float getRandomNumber(Float numberRangeStart, Float numberRangeEnd) {
		final float diff = numberRangeEnd.floatValue() - numberRangeStart.floatValue();
		return Float.valueOf(RND.nextFloat() * diff + numberRangeStart.floatValue());
	}

	@Override
	Float getDefaultRangeStart() {
		return START;
	}

	@Override
	Float getDefaultRangeEnd() {
		return END;
	}

}
