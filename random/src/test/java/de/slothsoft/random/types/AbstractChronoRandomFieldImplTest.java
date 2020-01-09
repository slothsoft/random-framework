package de.slothsoft.random.types;

import java.util.Date;

@SuppressWarnings({"boxing", "deprecation"})
public class AbstractChronoRandomFieldImplTest extends AbstractChronoRandomFieldTest<Long> {

	public static class ChronoRandomField extends AbstractChronoRandomField<Long> {

		@Override
		Long createDefaultStartValue() {
			return Long.MIN_VALUE;
		}

		@Override
		Long createDefaultEndValue() {
			return Long.MAX_VALUE;
		}

		@Override
		Long fromLongValue(long value) {
			return Long.valueOf(value);
		}

		@Override
		long toLongValue(Long value) {
			return value.longValue();
		}

	}

	public static class Pojo {

		private Long value;

		public Long getValue() {
			return this.value;
		}

		public void setValue(Long value) {
			this.value = value;
		}

	}

	public AbstractChronoRandomFieldImplTest() {
		super(new Pojo(), new Date(119, 1, 8, 13, 13, 42).getTime(), new Date(121, 1, 8, 13, 13, 42).getTime());
	}

	@Override
	protected ChronoRandomField createRandomField() {
		return (ChronoRandomField) new ChronoRandomField().startValue(new Date(110, 0, 1).getTime())
				.endValue(new Date(130, 0, 1).getTime());
	}

}
