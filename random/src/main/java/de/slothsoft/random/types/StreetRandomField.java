package de.slothsoft.random.types;

public class StreetRandomField extends StringFromListRandomField {

	public StreetRandomField(String[] fields) {
		super(fields);
	}

	@Override
	public String nextValue() {
		return super.nextValue() + " " + (RND.nextInt(100) + 1);
	}
}
