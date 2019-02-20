package de.slothsoft.random;

import static de.slothsoft.random.RandomFields.CITY;
import static de.slothsoft.random.RandomFields.LAST_NAME;

import java.util.ArrayList;
import java.util.List;

import de.slothsoft.random.db.AttributeRandomField;
import de.slothsoft.random.db.BigDecimalRandomField;
import de.slothsoft.random.db.BigIntegerRandomField;
import de.slothsoft.random.db.BirthdayRandomField;
import de.slothsoft.random.db.BooleanRandomField;
import de.slothsoft.random.db.DateRandomField;
import de.slothsoft.random.db.DoubleRandomField;
import de.slothsoft.random.db.FirstNameRandomField;
import de.slothsoft.random.db.FloatRandomField;
import de.slothsoft.random.db.IntegerRandomField;
import de.slothsoft.random.db.LongRandomField;
import de.slothsoft.random.db.ShortRandomField;
import de.slothsoft.random.db.StreetRandomField;

/**
 * Database of RandomFields
 * 
 * @author Steffi
 * 
 */

class Database {

	private static AttributeRandomField lastNameField;
	private static AttributeRandomField cityField;
	private static FirstNameRandomField firstNameField;
	private static StreetRandomField streetField;

	private static List<RandomField<?>> randomFields;

	/**
	 * Loads all random fields lazily.
	 * 
	 * @return
	 */

	public synchronized static List<RandomField<?>> getRandomFields() {
		if (randomFields == null) {
			lastNameField = new AttributeRandomField("last-names", LAST_NAME);
			cityField = new AttributeRandomField("city-names", CITY);
			firstNameField = new FirstNameRandomField();
			streetField = new StreetRandomField();

			randomFields = new ArrayList<RandomField<?>>();
			randomFields.add(lastNameField);
			randomFields.add(cityField);
			randomFields.add(firstNameField);
			randomFields.add(streetField);
			randomFields.add(new DateRandomField());
			randomFields.add(new BirthdayRandomField());

			randomFields.add(new ShortRandomField());
			randomFields.add(new IntegerRandomField());
			randomFields.add(new LongRandomField());
			randomFields.add(new DoubleRandomField());
			randomFields.add(new FloatRandomField());
			randomFields.add(new BigIntegerRandomField());
			randomFields.add(new BigDecimalRandomField());

			randomFields.add(new BooleanRandomField());
		}
		return randomFields;
	}

	/**
	 * Returns a random field by its synonyms
	 * 
	 * @param fieldName
	 * @param class1
	 * @return
	 */

	public static RandomField<?> getRandomFieldByAttributeName(
			String fieldName, Class<?> parameterType) {
		String name = fieldName.toLowerCase();
		for (RandomField<?> randomField : getRandomFields()) {
			if (randomField.isMapped(name, parameterType)) {
				return randomField;
			}
		}
		return null;
	}

	/**
	 * Returns a random field by its display name
	 * 
	 * @param fieldName
	 * @return
	 */

	public static RandomField<?> getRandomFieldByDisplayName(String displayName) {
		for (RandomField<?> randomField : getRandomFields()) {
			if (displayName.equals(randomField.getDisplayName())) {
				return randomField;
			}
		}
		return null;
	}

	public static String[] getStreets() {
		getRandomFields();
		return streetField.getAttributes();
	}

	public static String[] getCities() {
		getRandomFields();
		return cityField.getAttributes();
	}

	public static String[] getLastNames() {
		getRandomFields();
		return lastNameField.getAttributes();
	}

	public static String[] getFirstNames(Gender gender) {
		getRandomFields();
		return firstNameField.getAttributes(gender);
	}
}
