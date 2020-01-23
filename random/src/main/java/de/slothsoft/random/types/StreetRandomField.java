package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a street
 * (i.e. "High Street 10").
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class StreetRandomField extends ElementFromListRandomField<String> {

	/** Key that can be used by the pattern to display the street name. */
	public static final String KEY_STREET = "{street}";
	/** Key that can be used by the pattern to display the house number. */
	public static final String KEY_HOUSE_NUMBER = "{number}";

	/** Pattern used in Europe, e.g. "Hauptstraße 3". */
	public static final String PATTERN_STREET_FIRST = KEY_STREET + ' ' + KEY_HOUSE_NUMBER;
	/** Pattern used in English speaking countries, e.g. "201 High Street". */
	public static final String PATTERN_HOUSE_NUMBER_FIRST = KEY_HOUSE_NUMBER + ' ' + KEY_STREET;

	static final String[] streets;

	static {
		streets = FirstNameRandomField.readFile("street-names.txt");
	}

	private final PatternRandomField patternField = new PatternRandomField(PATTERN_HOUSE_NUMBER_FIRST);

	/**
	 * Default constructor.
	 *
	 * @since 2.1.0
	 */

	public StreetRandomField() {
		this(streets);
	}

	/**
	 * Old constructor.
	 *
	 * @param fields the street names
	 * @deprecated use {@link StreetRandomField#StreetRandomField()} or
	 *             {@link ElementFromListRandomField#ElementFromListRandomField(Object[])}
	 *             directly
	 */

	@Deprecated
	public StreetRandomField(String[] fields) {
		super(fields);
		this.patternField.addComponent(KEY_HOUSE_NUMBER, () -> String.valueOf(RND.nextInt(100) + 1));
		this.patternField.addComponent(KEY_STREET, () -> super.nextValue());
	}

	@Override
	public String nextValue() {
		return this.patternField.nextValue();
	}

	/**
	 * Returns the pattern with keys that get replaced during {@link #nextValue()}. Known
	 * keys are:
	 * <ul>
	 * <li>{@link #KEY_HOUSE_NUMBER}</li>
	 * <li>{@link #KEY_STREET}</li>
	 * </ul>
	 *
	 * @return the pattern
	 */

	public String getPattern() {
		return this.patternField.getPattern();
	}

	/**
	 * Sets the pattern with keys that get replaced during {@link #nextValue()}. Known
	 * keys are:
	 * <ul>
	 * <li>{@link #KEY_HOUSE_NUMBER}</li>
	 * <li>{@link #KEY_STREET}</li>
	 * </ul>
	 *
	 * @param newPattern the pattern
	 * @return this instance
	 */

	public StreetRandomField pattern(String newPattern) {
		setPattern(newPattern);
		return this;
	}

	/**
	 * Sets the pattern with keys that get replaced during {@link #nextValue()}. Known
	 * keys are:
	 * <ul>
	 * <li>{@link #KEY_HOUSE_NUMBER}</li>
	 * <li>{@link #KEY_STREET}</li>
	 * </ul>
	 *
	 * @param pattern the pattern
	 */

	public void setPattern(String pattern) {
		this.patternField.setPattern(pattern);
	}
}
