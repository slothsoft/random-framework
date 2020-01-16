package de.slothsoft.random.types;

import java.util.Objects;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.wordgen.WordGeneratorConfig;

/**
 * Generates words in sentences and paragraphs after a defined config stored in {@link WordGeneratorConfig}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class WordsRandomField implements RandomField {

	private final WordRandomField word = new WordRandomField();

	private int paragraphCount = 2;

	/**
	 * Generates a brand new word.
	 *
	 * @return a word
	 */

	@Override
	public String nextValue() {
		final int paragraphLength = (int) Math.max(1, (1 + RND.nextGaussian()) * this.paragraphCount);
		final StringBuilder sb = new StringBuilder();
		for (int p = 0; p < paragraphLength; p++) {
			if (sb.length() > 0) {
				sb.append('\n').append('\n');
			}

			final int sentencesPerParagraph = 3;
			for (int s = 0; s < sentencesPerParagraph; s++) {
				if (p > 0 || s > 0) {
					sb.append(' ');
				}

				final int wordsPerSentence = 5;
				for (int w = 0; w < wordsPerSentence; w++) {
					String nextWord = this.word.nextValue();
					if (w == 0) {
						nextWord = nextWord.substring(0, 1).toUpperCase() + nextWord.substring(1);
					}
					sb.append(nextWord);
					if (w < wordsPerSentence - 1) {
						sb.append(' ');
					} else {
						sb.append(".");
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Returns the used config.
	 *
	 * @return a config
	 */

	public WordGeneratorConfig getConfig() {
		return this.word.getConfig();
	}

	/**
	 * Sets the used config.
	 *
	 * @param newConfig a config
	 * @return this instance
	 */

	public WordsRandomField config(WordGeneratorConfig newConfig) {
		setConfig(newConfig);
		return this;
	}

	/**
	 * Sets the used config.
	 *
	 * @param config a config
	 */

	public void setConfig(WordGeneratorConfig config) {
		this.word.setConfig(Objects.requireNonNull(config));
	}

	/**
	 * Returns the number of paragraphs.
	 * 
	 * @return paragraph count
	 */

	public int getParagraphCount() {
		return this.paragraphCount;
	}

	/**
	 * Sets the number of paragraphs.
	 * 
	 * @param newParagraphCount paragraph count
	 * @return this instance
	 */

	public WordsRandomField paragraphCount(int newParagraphCount) {
		setParagraphCount(newParagraphCount);
		return this;
	}

	/**
	 * Sets the number of paragraphs.
	 * 
	 * @param paragraphCount paragraph count
	 */

	public void setParagraphCount(int paragraphCount) {
		this.paragraphCount = paragraphCount;
	}

}
