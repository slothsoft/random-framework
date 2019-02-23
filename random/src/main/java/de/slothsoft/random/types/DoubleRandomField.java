package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link Double} or its primitive counterpart. The
 * default values are between 1 and 100.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class DoubleRandomField extends AbstractNumberRandomField<Double> {

	private static final Double START = Double.valueOf(1);
	private static final Double END = Double.valueOf(100);

	public DoubleRandomField() {
		super(Double.class);
	}

	@Override
	protected Double getRandomNumber(Double numberRangeStart, Double numberRangeEnd) {
		final double diff = numberRangeEnd.doubleValue() - numberRangeStart.doubleValue();
		return Double.valueOf(RND.nextDouble() * diff + numberRangeStart.doubleValue());
	}

	@Override
	Double getDefaultRangeStart() {
		return START;
	}

	@Override
	Double getDefaultRangeEnd() {
		return END;
	}

}
