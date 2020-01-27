package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a postal
 * code. See <a href="https://en.wikipedia.org/wiki/List_of_postal_codes">list of postal
 * codes</a>.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class PostalCodeRandomField implements RandomField {

	/** Key that can be used by the pattern to display a single digit. */
	public static final String KEY_DIGIT = "{digit}";
	/** Key that can be used by the pattern to display a single letter. */
	public static final String KEY_LETTER = "{letter}";

	/** Pattern used in Germany, e.g. "01109". */
	public static final String PATTERN_GERMANY = KEY_DIGIT + KEY_DIGIT + KEY_DIGIT + KEY_DIGIT + KEY_DIGIT;
	/** Pattern used in the US, e.g. "12345-6789". */
	public static final String PATTERN_US = KEY_DIGIT + KEY_DIGIT + KEY_DIGIT + KEY_DIGIT + KEY_DIGIT + '-' + KEY_DIGIT
			+ KEY_DIGIT + KEY_DIGIT + KEY_DIGIT;
	/** Pattern used in Canada, e.g. "A0B 1C2". */
	public static final String PATTERN_CANADA = KEY_LETTER + KEY_DIGIT + KEY_LETTER + ' ' + KEY_DIGIT + KEY_LETTER
			+ KEY_DIGIT;

	private final PatternRandomField patternField = new PatternRandomField(PATTERN_US);
	private double nullProbability;

	/**
	 * Default constructor.
	 */

	public PostalCodeRandomField() {
		final IntegerRandomField integerRandomField = new IntegerRandomField();
		integerRandomField.startValue(Integer.valueOf(0)).endValue(Integer.valueOf(10));
		this.patternField.addComponent(KEY_DIGIT, integerRandomField);

		final CharacterRandomField characterRandomField = new CharacterRandomField();
		this.patternField.addComponent(KEY_LETTER,
				() -> String.valueOf(characterRandomField.nextValue()).toUpperCase());
	}

	@Override
	public String nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		return this.patternField.nextValue();
	}

	/**
	 * Returns the pattern with keys that get replaced during {@link #nextValue()}. Known
	 * keys are:
	 * <ul>
	 * <li>{@link #KEY_DIGIT}</li>
	 * <li>{@link #KEY_LETTER}</li>
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
	 * <li>{@link #KEY_DIGIT}</li>
	 * <li>{@link #KEY_LETTER}</li>
	 * </ul>
	 *
	 * @param newPattern the pattern
	 * @return this instance
	 */

	public PostalCodeRandomField pattern(String newPattern) {
		setPattern(newPattern);
		return this;
	}

	/**
	 * Sets the pattern with keys that get replaced during {@link #nextValue()}. Known
	 * keys are:
	 * <ul>
	 * <li>{@link #KEY_DIGIT}</li>
	 * <li>{@link #KEY_LETTER}</li>
	 * </ul>
	 *
	 * @param pattern the pattern
	 */

	public void setPattern(String pattern) {
		this.patternField.setPattern(pattern);
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

	public PostalCodeRandomField nullProbability(double newNullProbability) {
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
