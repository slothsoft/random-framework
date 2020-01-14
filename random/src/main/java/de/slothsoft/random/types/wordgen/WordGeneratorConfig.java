package de.slothsoft.random.types.wordgen;

import de.slothsoft.random.types.WordRandomField;

/**
 * Config for a {@link WordRandomField}. Default implementation is
 * {@link DefaultWordGeneratorConfig} and some usable standards are
 * {@link StandardWordGeneratorConfig}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public interface WordGeneratorConfig {

	/**
	 * Returns the supported <b>lower case</b> characters.
	 *
	 * @return all lower case characters
	 */

	char[] getSupportedCharacters();

	/**
	 * Returns the standard word length.
	 *
	 * @return the word length
	 */

	double getStandardWordLength();

	/**
	 * Returns the probability for a specific character that got returned via
	 * {@link #getSupportedCharacters()}.
	 *
	 * @param character a supported character
	 * @return it's probability
	 */

	double getProbability(char character);

}
