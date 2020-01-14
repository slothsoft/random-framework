package de.slothsoft.random.types.wordgen.generator;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.IntPredicate;

import de.slothsoft.random.types.wordgen.DefaultWordGeneratorConfig;
import de.slothsoft.random.types.wordgen.Letter;
import de.slothsoft.random.types.wordgen.WordGeneratorConfig;

public class WordGeneratorConfigGenerator {

	static class TextInfo {
		int lettersCount;
		Counter words = new Counter();
		Counter sentences = new Counter();
	}

	static class Counter {
		int count;
		long sum;

		double calculateProbability() {
			return (double) this.sum / this.count;
		}
	}

	static class LetterInfo {
		int count;
	}

	TextInfo textInfo;
	Map<Character, LetterInfo> letters;

	IntPredicate isLetterFunction = c -> Character.isLetter((char) c);
	Charset encoding = Charset.forName("utf8");
	int minSentenceLength = 5;
	String startPhrase = null;
	String endPhrase = null;

	public WordGeneratorConfig generateFromStream(InputStream input) {
		return generateFromString(readEntireText(input));
	}

	public WordGeneratorConfig generateFromString(String entireText) {
		this.textInfo = new TextInfo();
		this.letters = new HashMap<>();

		analyzeText(entireText);

		final Letter[] supportedLetters = this.letters.entrySet().stream()
				.map(entry -> new Letter(entry.getKey().charValue())
						.probability((double) entry.getValue().count / this.textInfo.lettersCount))
				.toArray(Letter[]::new);
		final double standardWordLength = this.textInfo.words.calculateProbability();

		return new DefaultWordGeneratorConfig().letters(supportedLetters)
				.standardWordLength(standardWordLength);
	}

	private String readEntireText(InputStream input) {
		String result;
		try (Scanner scanner = new Scanner(input, this.encoding.name())) {
			scanner.useDelimiter("\\A");
			result = scanner.hasNext() ? scanner.next() : "";
		}
		// we only analyze from start to end phrase
		if (this.startPhrase != null) {
			final int index = result.indexOf(this.startPhrase);
			if (index >= 0) {
				result = result.substring(index + this.startPhrase.length());
			}
		}
		if (this.endPhrase != null) {
			final int index = result.indexOf(this.endPhrase);
			if (index >= 0) {
				result = result.substring(0, index);
			}
		}
		// remove unnecessary white spaces
		result = result.replace("[\\s\n\r]+", " ");

		return result;
	}

	private void analyzeText(String text) {
		for (final String sentence : text.split("\\.")) {
			final String sentenceTrim = sentence.trim();
			if (sentenceTrim.length() >= this.minSentenceLength) {
				analyzeSentence(sentenceTrim);
			}
		}
	}

	private void analyzeSentence(String sentence) {
		final int beforeWordsCount = this.textInfo.words.count;
		final String[] words = sentence.split("\\s+");

		for (final String word : words) {
			analyzeWord(word);
		}

		if (beforeWordsCount <= this.textInfo.words.count - 2) {
			// a sentence has at least 2 words
			this.textInfo.sentences.count++;
			this.textInfo.sentences.sum += words.length;
		}
	}

	private void analyzeWord(String word) {
		final int beforeLettersCount = this.textInfo.lettersCount;

		for (final char letter : word.toCharArray()) {
			final char lowerCaseLetter = Character.toLowerCase(letter);
			if (this.isLetterFunction.test(lowerCaseLetter)) {
				this.letters.computeIfAbsent(Character.valueOf(lowerCaseLetter), c -> new LetterInfo()).count++;
				this.textInfo.lettersCount++;
			}
		}

		if (beforeLettersCount < this.textInfo.lettersCount) {
			// if the "word" had no letters we don't count it as word
			this.textInfo.words.count++;
			this.textInfo.words.sum += word.length();
		}
	}

	public IntPredicate getIsLetterFunction() {
		return this.isLetterFunction;
	}

	public WordGeneratorConfigGenerator isLetterFunction(IntPredicate newIsLetterFunction) {
		setIsLetterFunction(newIsLetterFunction);
		return this;
	}

	public void setIsLetterFunction(IntPredicate isLetterFunction) {
		this.isLetterFunction = Objects.requireNonNull(isLetterFunction);
	}

	public Charset getEncoding() {
		return this.encoding;
	}

	public WordGeneratorConfigGenerator encoding(Charset newEncoding) {
		setEncoding(newEncoding);
		return this;
	}

	public void setEncoding(Charset encoding) {
		this.encoding = Objects.requireNonNull(encoding);
	}

	public int getMinSentenceLength() {
		return this.minSentenceLength;
	}

	public WordGeneratorConfigGenerator minSentenceLength(int newMinSentenceLength) {
		setMinSentenceLength(newMinSentenceLength);
		return this;
	}

	public void setMinSentenceLength(int minSentenceLength) {
		this.minSentenceLength = minSentenceLength;
	}

	public String getEndPhrase() {
		return this.endPhrase;
	}

	public WordGeneratorConfigGenerator endPhrase(String newEndPhrase) {
		setEndPhrase(newEndPhrase);
		return this;
	}

	public void setEndPhrase(String endPhrase) {
		this.endPhrase = endPhrase;
	}

	public String getStartPhrase() {
		return this.startPhrase;
	}

	public WordGeneratorConfigGenerator startPhrase(String newStartPhrase) {
		setStartPhrase(newStartPhrase);
		return this;
	}

	public void setStartPhrase(String startPhrase) {
		this.startPhrase = startPhrase;
	}

}
