package de.slothsoft.random.types;

public class LongPrimitiveTest extends AbstractNumberRandomFieldTest<Long> {

	public static class Pojo {

		private long value;

		public long getValue() {
			return this.value;
		}

		public void setValue(long value) {
			this.value = value;
		}

	}

	public LongPrimitiveTest() {
		super(new LongPrimitiveTest.Pojo(), Long.valueOf(7), Long.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Long> createRandomField() {
		return new LongRandomField();
	}
}
