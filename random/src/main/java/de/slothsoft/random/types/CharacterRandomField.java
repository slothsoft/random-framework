package de.slothsoft.random.types;

import java.util.Objects;
import java.util.Random;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.StandardWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.WordGeneratorConfig;

/**
 * Generates letters after a defined config stored in {@link WordGeneratorConfig}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class CharacterRandomField implements RandomField {

	static final Random RND = new Random();

	private WordGeneratorConfig config;

	private char[] configCharacters;
	private double[] configCharactersProbabilities;

	/**
	 * Default constructor.
	 */

	public CharacterRandomField() {
		setConfig(StandardWordGeneratorConfig.EVEN_DISTRIBUTION);
	}

	/**
	 * Generates a brand new word.
	 *
	 * @return a word
	 */

	@Override
	public Character nextValue() {
		final double letterProbability = RND.nextDouble()
				* this.configCharactersProbabilities[this.configCharactersProbabilities.length - 1];
		for (int i = 0; i < this.configCharacters.length; i++) {
			if (this.configCharactersProbabilities[i + 1] > letterProbability) {
				return Character.valueOf(this.configCharacters[i]);
			}
		}
		// should not happen
		return Character.valueOf('!');
	}

	private void updateFieldsFromConfig() {
		this.configCharacters = Objects.requireNonNull(this.config.getSupportedCharacters());
		if (this.configCharacters.length == 0) {
			throw new IllegalArgumentException("At least one character must be supported by config: " + this.config);
		}
		this.configCharactersProbabilities = new double[this.configCharacters.length + 1];
		for (int i = 0; i < this.configCharacters.length; i++) {
			this.configCharactersProbabilities[i + 1] = this.configCharactersProbabilities[i]
					+ this.config.getProbability(this.configCharacters[i]);
		}
	}

	/**
	 * Returns the used config.
	 *
	 * @return a config
	 */

	public WordGeneratorConfig getConfig() {
		return this.config;
	}

	/**
	 * Sets the used config.
	 *
	 * @param newConfig a config
	 * @return this instance
	 */

	public CharacterRandomField config(WordGeneratorConfig newConfig) {
		setConfig(newConfig);
		return this;
	}

	/**
	 * Sets the used config.
	 *
	 * @param config a config
	 */

	public void setConfig(WordGeneratorConfig config) {
		this.config = Objects.requireNonNull(config);
		updateFieldsFromConfig();
	}

}
