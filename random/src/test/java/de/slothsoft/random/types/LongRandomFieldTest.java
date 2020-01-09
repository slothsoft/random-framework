package de.slothsoft.random.types;

public class LongRandomFieldTest extends AbstractNumberRandomFieldTest<Long> {

	public static class Pojo {

		private Long value;

		public Long getValue() {
			return this.value;
		}

		public void setValue(Long value) {
			this.value = value;
		}

	}

	public LongRandomFieldTest() {
		super(new LongRandomFieldTest.Pojo(), Long.valueOf(7), Long.valueOf(92));
	}

	@Override
	protected AbstractNumberRandomField<Long> createRandomField() {
		return new LongRandomField();
	}
}
