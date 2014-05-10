package de.slothsoft.random.db;

import java.util.Set;

import de.slothsoft.random.Option;
import de.slothsoft.random.Options;

public abstract class AbstractNumberRandomField extends
		AbstractRandomField<Number> {

	public AbstractNumberRandomField(String displayName) {
		super(displayName);
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class<?>[] { getNumberClass() };
	}

	protected abstract Class<? extends Number> getNumberClass();

	@Override
	public boolean isMapped(String name, Class<?> parameterType) {
		return isClassMapped(parameterType);
	}

	@Override
	public Number getRandomAttribute(String attributeName,
			Class<?> attributeClass, Set<Option> options) {
		Number start = getNumberRangeStart(attributeName, options);
		Number end = getNumberRangeEnd(attributeName, options);

		if (end.equals(start)) {
			return start;
		}

		if (end.doubleValue() < start.doubleValue()) {
			Number dummy = start;
			start = end;
			end = dummy;
		}

		return getRandomNumber(start, end);
	}

	protected abstract Number getRandomNumber(Number numberRangeStart,
			Number numberRangeEnd);

	private Number getNumberRangeStart(String attribute, Set<Option> options) {
		return (Number) getOptionValue(options, attribute,
				Options.NUMBER_RANGE_START, getDefaultNumberRangeStart());
	}

	private Number getNumberRangeEnd(String attribute, Set<Option> options) {
		return (Number) getOptionValue(options, attribute,
				Options.NUMBER_RANGE_END, getDefaultNumberRangeEnd());
	}

	protected Number getDefaultNumberRangeStart() {
		return Integer.valueOf(0);
	}

	protected Number getDefaultNumberRangeEnd() {
		return Integer.valueOf(100);
	}

}
