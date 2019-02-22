package de.slothsoft.random.types;

public class DoubleRandomField extends AbstractPrimitiveNumberRandomField<Double> {

	private static final Double START = Double.valueOf(1);
	private static final Double END = Double.valueOf(100);

	public DoubleRandomField() {
		super(double.class, Double.class);
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
