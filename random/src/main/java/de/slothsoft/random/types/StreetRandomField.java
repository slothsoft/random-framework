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
	private final IntegerRandomField houseNumberField = new IntegerRandomField();

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
		this.houseNumberField.startValue(Integer.valueOf(1)).endValue(Integer.valueOf(100));
		this.patternField.addComponent(KEY_HOUSE_NUMBER, () -> String.valueOf(this.houseNumberField.nextValue()));
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

	/**
	 * Returns the start value for house numbers, i.e. the house number {@link #nextValue} will always be greater than this value.
	 *
	 * @return the start value
	 */

	public int getHouseNumberStartValue() {
		return this.houseNumberField.getStartValue().intValue();
	}

	/**
	 * Sets the start value for house numbers, i.e. the house number {@link #nextValue} will always be greater than this value.
	 *
	 * @param newStartValue the start value
	 * @return this instance
	 */

	public StreetRandomField houseNumberStartValue(int newStartValue) {
		setHouseNumberStartValue(newStartValue);
		return this;
	}

	/**
	 * Sets the start value for house numbers, i.e. the house number {@link #nextValue} will always be greater than this value.
	 *
	 * @param startValue the start value
	 */

	public void setHouseNumberStartValue(int startValue) {
		this.houseNumberField.setStartValue(Integer.valueOf(startValue));
	}

	/**
	 * Returns the end value, i.e. {@link #nextValue} will always be less than this value.
	 *
	 * @return the end value
	 */

	public int getHouseNumberEndValue() {
		return this.houseNumberField.getEndValue().intValue();
	}

	/**
	 * Sets the end value for house numbers, i.e. the house number of {@link #nextValue} will always be less than this value.
	 *
	 * @param newEndValue the end value
	 * @return this instance
	 */

	public StreetRandomField houseNumberEndValue(int newEndValue) {
		setHouseNumberEndValue(newEndValue);
		return this;
	}

	/**
	 * Sets the end value for house numbers, i.e. the house number of {@link #nextValue} will always be less than this value.
	 *
	 * @param endValue the end value
	 */

	public void setHouseNumberEndValue(int endValue) {
		this.houseNumberField.setEndValue(Integer.valueOf(endValue));
	}
}
