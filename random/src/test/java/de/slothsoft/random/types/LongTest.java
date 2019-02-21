package de.slothsoft.random.types;

public class LongTest extends AbstractNumberRandomFieldTest<Long> {

	public static class Pojo {

		private Long value;

		public Long getValue() {
			return this.value;
		}

		public void setValue(Long value) {
			this.value = value;
		}

	}

	public LongTest() {
		super(new LongTest.Pojo(), Long.valueOf(7), Long.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Long> createRandomField() {
		return new LongRandomField();
	}
}
