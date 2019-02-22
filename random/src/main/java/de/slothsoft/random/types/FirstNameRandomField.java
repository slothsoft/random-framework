package de.slothsoft.random.types;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a first name.
 *
 * @author Steffi Schulz
 * @since 1.0.0
 */

public class FirstNameRandomField implements RandomField<String> {

	/**
	 * Enum for setting the Gender of the first name.
	 *
	 */

	public enum Gender {
		MALE(true, false), FEMALE(false, true), NEITHER(false, false), BOTH(true, true);

		boolean allowsMale;
		boolean allowsFemale;

		private Gender(boolean allowsMale, boolean allowsFemale) {
			this.allowsMale = allowsMale;
			this.allowsFemale = allowsFemale;
		}

	}

	static final String[] maleNames;
	static final String[] femaleNames;
	static final String[] unisexNames;

	static {
		maleNames = readFile("male-names.txt");
		femaleNames = readFile("female-names.txt");
		unisexNames = readFile("unisex-names.txt");
	}

	static String[] readFile(String fileName) {
		return new BufferedReader(
				new InputStreamReader(FirstNameRandomField.class.getResourceAsStream("../text/" + fileName))).lines()
						.parallel().toArray(String[]::new);
	}

	private Gender gender;
	private List<String> supportedNames;

	public FirstNameRandomField() {
		setGender(Gender.BOTH);
	}

	@Override
	public String nextValue() {
		return this.supportedNames.get(RND.nextInt(this.supportedNames.size()));
	}

	@Override
	public Class<?> getFieldClass() {
		return String.class;
	}

	public Gender getGender() {
		return this.gender;
	}

	public FirstNameRandomField gender(Gender newGender) {
		setGender(newGender);
		return this;
	}

	public void setGender(Gender gender) {
		this.gender = Objects.requireNonNull(gender);
		updateSupportedNames();
	}

	private void updateSupportedNames() {
		this.supportedNames = new ArrayList<>();
		if (this.gender.allowsMale) {
			this.supportedNames.addAll(Arrays.asList(maleNames));
		}
		if (this.gender.allowsFemale) {
			this.supportedNames.addAll(Arrays.asList(femaleNames));
		}
		this.supportedNames.addAll(Arrays.asList(unisexNames));
	}

}
