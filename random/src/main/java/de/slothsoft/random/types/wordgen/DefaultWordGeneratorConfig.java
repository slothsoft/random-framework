package de.slothsoft.random.types.wordgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Default implementation for the {@link WordGeneratorConfig} that you can fill using
 * {@link #addLetters(Letter...)} and {@link #removeLetters(Letter...)}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class DefaultWordGeneratorConfig implements WordGeneratorConfig {

	static final String DEFAULT_SUPPORTED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
	static final int DEFAULT_STANDARD_WORD_LENGTH = 5;
	static final Letter DEFAULT_LETTER = new Letter((char) 0);

	List<Letter> letters = new ArrayList<>(
			DEFAULT_SUPPORTED_CHARACTERS.chars().mapToObj(c -> new Letter((char) c)).collect(Collectors.toList()));
	double standardWordLength = DEFAULT_STANDARD_WORD_LENGTH;

	@Override
	public double getProbability(char c) {
		return this.letters.stream().filter(l -> l.character == c).findAny().orElse(DEFAULT_LETTER).probability;
	}

	@Override
	public char[] getSupportedCharacters() {
		final char[] result = new char[this.letters.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = this.letters.get(i).getCharacter();
		}
		return result;
	}

	/**
	 * Adds some letters that will be returned via {@link #getSupportedCharacters()} and
	 * {@link #getProbability(char)}.
	 *
	 * @param addedLetters supported letters
	 */

	public void addLetters(Letter... addedLetters) {
		this.letters.addAll(Arrays.asList(addedLetters));
	}

	/**
	 * Removes some letters that will not be returned via
	 * {@link #getSupportedCharacters()} and {@link #getProbability(char)} any longer.
	 *
	 * @param removedLetters supported letters
	 */

	public void removeLetters(Letter... removedLetters) {
		this.letters.removeAll(Arrays.asList(removedLetters));
	}

	/**
	 * Returns the letters that will be returned via {@link #getSupportedCharacters()} and
	 * {@link #getProbability(char)}.
	 *
	 * @return supported letters
	 */

	public Letter[] getLetters() {
		return this.letters.toArray(new Letter[this.letters.size()]);
	}

	/**
	 * Sets the letters that will be returned via {@link #getSupportedCharacters()} and
	 * {@link #getProbability(char)}.
	 *
	 * @param newSupportedLetters supported letters
	 * @return this instance
	 */

	public DefaultWordGeneratorConfig letters(Letter... newSupportedLetters) {
		setSupportedLetters(newSupportedLetters);
		return this;
	}

	/**
	 * Sets the letters that will be returned via {@link #getSupportedCharacters()} and
	 * {@link #getProbability(char)}.
	 *
	 * @param supportedLetters supported letters
	 */

	public void setSupportedLetters(Letter... supportedLetters) {
		Objects.requireNonNull(supportedLetters);
		if (supportedLetters.length == 0)
			throw new IllegalArgumentException("At least one letter must be supported by config: " + supportedLetters);
		this.letters = new ArrayList<>(Arrays.asList(supportedLetters));
	}

	@Override
	public double getStandardWordLength() {
		return this.standardWordLength;
	}

	/**
	 * Sets the standard word length.
	 *
	 * @param newStandardWordLength the word length
	 * @return this instance
	 */

	public DefaultWordGeneratorConfig standardWordLength(double newStandardWordLength) {
		setStandardWordLength(newStandardWordLength);
		return this;
	}

	/**
	 * Sets the standard word length.
	 *
	 * @param standardWordLength the word length
	 */

	public void setStandardWordLength(double standardWordLength) {
		this.standardWordLength = standardWordLength;
	}

	@Override
	public String toString() {
		return "DefaultWordGeneratorConfig [supportedCharacters="
				+ this.letters.stream().map(l -> String.valueOf(l.getCharacter())).collect(Collectors.joining(""))
				+ ", standardWordLength=" + this.standardWordLength + "]";
	}

	@Override
	public int hashCode() {
		return WordGeneratorConfigUtil.generateHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final DefaultWordGeneratorConfig that = (DefaultWordGeneratorConfig) obj;
		if (Double.doubleToLongBits(this.standardWordLength) != Double.doubleToLongBits(that.standardWordLength))
			return false;
		if (!this.letters.equals(that.letters)) return false;
		return true;
	}

}
