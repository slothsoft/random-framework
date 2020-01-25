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
	private double nullProbability;
	private double capitalProbability;

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
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		final double letterProbability = RND.nextDouble()
				* this.configCharactersProbabilities[this.configCharactersProbabilities.length - 1];
		for (int i = 0; i < this.configCharacters.length; i++) {
			if (this.configCharactersProbabilities[i + 1] > letterProbability) {
				char result = this.configCharacters[i];
				if (RND.nextDouble() < this.capitalProbability) {
					result = Character.toUpperCase(result);
				} else {
					result = Character.toLowerCase(result);
				}
				return Character.valueOf(result);
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

	public CharacterRandomField nullProbability(double newNullProbability) {
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

	/**
	 * Returns the probability for this field returning capital. If the value is 0 then no
	 * {@link #nextValue()} is a capital letter, if it is 1 then every
	 * {@link #nextValue()} is a capital letter.
	 *
	 * @return the probability between 0 and 1
	 */

	public double getCapitalProbability() {
		return this.capitalProbability;
	}

	/**
	 * Sets the probability for this field returning capital. If the value is 0 then no
	 * {@link #nextValue()} is a capital letter, if it is 1 then every
	 * {@link #nextValue()} is a capital letter.
	 *
	 * @param newCapitalProbability the probability between 0 and 1
	 * @return this instance
	 */

	public CharacterRandomField capitalProbability(double newCapitalProbability) {
		setCapitalProbability(newCapitalProbability);
		return this;
	}

	/**
	 * Sets the probability for this field returning capital. If the value is 0 then no
	 * {@link #nextValue()} is a capital letter, if it is 1 then every
	 * {@link #nextValue()} is a capital letter.
	 *
	 * @param capitalProbability the probability between 0 and 1
	 */

	public void setCapitalProbability(double capitalProbability) {
		if (capitalProbability < 0 || capitalProbability > 1) {
			throw new IllegalArgumentException("Capital probability must be between 0 and 1!");
		}
		this.capitalProbability = capitalProbability;
	}
}
