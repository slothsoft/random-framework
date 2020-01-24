package de.slothsoft.random.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a first name.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class FirstNameRandomField implements RandomField {

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
		final String fileNameWithPath = "/de/slothsoft/random/text/" + fileName;
		try (InputStream inputStream = FirstNameRandomField.class.getResourceAsStream(fileNameWithPath);
				InputStreamReader streamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(streamReader)) {
			return bufferedReader.lines().parallel().toArray(String[]::new);
		} catch (final IOException e) {
			throw new RuntimeException("Could not open: " + fileName);
		}
	}

	private Gender gender;
	private List<String> supportedNames;
	private double nullProbability;

	/**
	 * Default constructor.
	 */

	public FirstNameRandomField() {
		setGender(Gender.BOTH);
	}

	@Override
	public String nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		return this.supportedNames.get(RND.nextInt(this.supportedNames.size()));
	}

	/**
	 * Returns the gender of the generated first names.
	 *
	 * @return the gender; never null
	 */

	public Gender getGender() {
		return this.gender;
	}

	/**
	 * Sets the gender of the generated first names.
	 *
	 * @param newGender the gender; cannot be null
	 * @return this instance
	 */

	public FirstNameRandomField gender(Gender newGender) {
		setGender(newGender);
		return this;
	}

	/**
	 * Sets the gender of the generated first names.
	 *
	 * @param gender the gender; cannot be null
	 */

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

	/**
	 * Returns the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @return the probability between 0 and 1
	 */

	public double getNullProbability() {
		return this.nullProbability;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param newNullProbability the probability between 0 and 1
	 * @return this instance
	 */

	public FirstNameRandomField nullProbability(double newNullProbability) {
		setNullProbability(newNullProbability);
		return this;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param nullProbability the probability between 0 and 1
	 */

	public void setNullProbability(double nullProbability) {
		if (nullProbability < 0 || nullProbability > 1) {
			throw new IllegalArgumentException("Null probability must be between 0 and 1!");
		}
		this.nullProbability = nullProbability;
	}

}
