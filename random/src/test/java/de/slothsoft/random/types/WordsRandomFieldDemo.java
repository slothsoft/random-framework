package de.slothsoft.random.types;

import de.slothsoft.random.types.wordgen.StandardWordGeneratorConfig;

public class WordsRandomFieldDemo {

	public static void main(String[] args) {
		final WordsRandomField randomField = new WordsRandomField();

		for (final StandardWordGeneratorConfig config : StandardWordGeneratorConfig.values()) {
			System.out.println(config);
			randomField.setConfig(config);

			System.out.println(randomField.nextValue());
			System.out.println("\n-- o -- o -- o -- o -- o -- o -- o --\n\n");
		}
	}
}
