package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing a {@link String} that should look like a postal
 * code.
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

	private final PatternRandomField patternField = new PatternRandomField(PATTERN_US);

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
}
