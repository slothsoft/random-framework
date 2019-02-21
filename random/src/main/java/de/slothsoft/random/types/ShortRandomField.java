package de.slothsoft.random.types;

public class ShortRandomField extends AbstractPrimitiveNumberRandomField<Short> {

	private static final Short START = Short.valueOf((short) 1);
	private static final Short END = Short.valueOf((short) 100);

	public ShortRandomField() {
		super(short.class, Short.class);
	}

	@Override
	protected Short getRandomNumber(Short numberRangeStart, Short numberRangeEnd) {
		final int diff = numberRangeEnd.intValue() - numberRangeStart.intValue();
		return Short.valueOf((short) (RND.nextDouble() * diff + numberRangeStart.intValue()));
	}

	@Override
	Short getDefaultNumberRangeStart() {
		return START;
	}

	@Override
	Short getDefaultNumberRangeEnd() {
		return END;
	}
}
