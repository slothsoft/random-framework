package de.slothsoft.random.db;

import java.util.Random;
import java.util.Set;

import de.slothsoft.random.Option;
import de.slothsoft.random.RandomField;

public abstract class AbstractRandomField<T> implements RandomField<T> {

	protected static Random RANDOM = new Random();

	private String displayName;

	public AbstractRandomField(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	/*
	 * Helper methods
	 */

	protected Object getOptionValue(Set<Option> options, String attributeName,
			String optionName, Object defaultValue) {
		Option compareOption = new Option(attributeName, optionName, null);
		for (Option option : options) {
			if (option.equals(compareOption)) {
				return option.getValue();
			}
		}
		return defaultValue;
	}

	protected boolean isClassMapped(Class<?> parameterType) {
		for (Class<?> attributeClass : getAttributeClasses()) {
			if (attributeClass.isAssignableFrom(parameterType)) {
				return true;
			}
		}
		return false;
	}
}
