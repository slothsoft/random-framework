package de.slothsoft.random.types;

public class FloatRandomField extends AbstractPrimitiveNumberRandomField<Float> {

	private static final Float START = Float.valueOf(1);
	private static final Float END = Float.valueOf(100);

	public FloatRandomField() {
		super(float.class, Float.class);
	}

	@Override
	protected Float getRandomNumber(Float numberRangeStart, Float numberRangeEnd) {
		final float diff = numberRangeEnd.floatValue() - numberRangeStart.floatValue();
		return Float.valueOf(RND.nextFloat() * diff + numberRangeStart.floatValue());
	}

	@Override
	Float getDefaultNumberRangeStart() {
		return START;
	}

	@Override
	Float getDefaultNumberRangeEnd() {
		return END;
	}

}
