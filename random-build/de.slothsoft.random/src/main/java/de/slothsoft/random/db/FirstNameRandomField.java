package de.slothsoft.random.db;

import static de.slothsoft.random.RandomFields.FIRST_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.slothsoft.random.Gender;
import de.slothsoft.random.RandomFactoryLogger;
import de.slothsoft.random.Option;
import de.slothsoft.random.Options;

public class FirstNameRandomField extends AttributeRandomField {

	private static final Random rnd = new Random();
	private static final String ERROR_NAME = "Johnny";

	private List<GenderField> randomFields = new ArrayList<GenderField>();

	public FirstNameRandomField() {
		super("not-used", FIRST_NAME);

		this.randomFields.add(new GenderField("male-names", FIRST_NAME,
				Gender.MALE));
		this.randomFields.add(new GenderField("female-names", FIRST_NAME,
				Gender.FEMALE));
		this.randomFields.add(new GenderField("unisex-names", FIRST_NAME,
				Gender.BOTH));
	}

	@Override
	protected void readAttributes(String fileName) {
		// not needed (delegated to children)
	}

	public List<GenderField> getRandomFieldsByDisplayName(Gender gender) {
		List<GenderField> result = new ArrayList<GenderField>();

		for (GenderField randomField : this.randomFields) {
			if (gender == randomField.getGender()
					|| randomField.getGender() == Gender.BOTH
					|| gender == Gender.BOTH) {
				result.add(randomField);
			}
		}
		return result;
	}

	@Override
	public String getRandomAttribute(String attribute, Class<?> attributeClass,
			Set<Option> options) {
		Gender gender = getGender(attribute, options);
		List<GenderField> randomFields = getRandomFieldsByDisplayName(gender);
		if (randomFields.size() == 0) {
			RandomFactoryLogger.logError("There are no first names for gender " + gender);
			return ERROR_NAME;
		}
		int size = getAttributesSize(randomFields);
		int nameIndex = rnd.nextInt(size);
		for (GenderField randomField : randomFields) {
			int entriesLength = randomField.getAttributes().length;
			if (nameIndex < randomField.getAttributes().length) {
				return randomField.getAttributes()[nameIndex];
			} else {
				nameIndex -= entriesLength;
			}
		}
		return null;
	}

	public String[] getAttributes(Gender gender) {
		List<GenderField> randomFields = getRandomFieldsByDisplayName(gender);
		List<String> result = new ArrayList<String>();
		for (GenderField randomField : randomFields) {
			result.addAll(Arrays.asList(randomField.getAttributes()));
		}
		return result.toArray(new String[result.size()]);
	}

	private int getAttributesSize(List<GenderField> randomFields) {
		int size = 0;
		for (GenderField randomField : randomFields) {
			size += randomField.getAttributes().length;
		}
		return size;
	}

	private Gender getGender(String attribute, Set<Option> options) {
		return (Gender) getOptionValue(options, attribute, Options.GENDER,
				Gender.BOTH);
	}

	@Override
	public Class<?>[] getAttributeClasses() {
		return new Class[] { String.class };
	}

	/**
	 * 
	 */

	class GenderField extends AttributeRandomField {

		private Gender gender;

		public GenderField(String file, String displayName, Gender gender) {
			super(file, displayName);
			this.gender = gender;
		}

		@Override
		protected void readSynonyms(String fileName) {
			// empty, because not needed
		}

		public Gender getGender() {
			return this.gender;
		}
	}
}
