package de.slothsoft.random.types;

import java.util.Objects;

import de.slothsoft.random.RandomField;

public class StringFromListRandomField implements RandomField<String> {

	private final String[] fields;

	public StringFromListRandomField(String[] fields) {
		this.fields = Objects.requireNonNull(fields);
	}

	@Override
	public String nextValue() {
		return this.fields[RND.nextInt(this.fields.length)];
	}

	@Override
	public Class<?> getFieldClass() {
		return String.class;
	}
}