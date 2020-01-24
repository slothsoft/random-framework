package de.slothsoft.random.types;

import java.util.Objects;
import java.util.Random;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.StandardWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.WordGeneratorConfig;

/**
 * Generates a word after a defined config stored in {@link WordGeneratorConfig}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class WordRandomField implements RandomField {

	static final Random RND = new Random();

	private WordGeneratorConfig config;

	private char[] configCharacters;
	private double[] configCharactersProbabilities;
	private double nullProbability;

	/**
	 * Default constructor.
	 */

	public WordRandomField() {
		setConfig(StandardWordGeneratorConfig.EVEN_DISTRIBUTION);
	}

	/**
	 * Generates a brand new word.
	 *
	 * @return a word
	 */

	@Override
	public String nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}

		final int wordLength = (int) Math.max(1, (1 + RND.nextGaussian()) * this.config.getStandardWordLength());
		final StringBuilder sb = new StringBuilder();
		char lastLetter = 0;
		while (sb.length() < wordLength) {
			final char nextLetter = nextLetter(lastLetter);
			sb.append(nextLetter);
			lastLetter = nextLetter;
		}
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private char nextLetter(char lastLetter) {
		final double letterProbability = RND.nextDouble()
				* this.configCharactersProbabilities[this.configCharactersProbabilities.length - 1];
		for (int i = 0; i < this.configCharacters.length; i++) {
			if (this.configCharactersProbabilities[i + 1] > letterProbability) {
				return this.configCharacters[i];
			}
		}
		// should not happen
		return '!';
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

	public WordRandomField config(WordGeneratorConfig newConfig) {
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

	public WordRandomField nullProbability(double newNullProbability) {
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
