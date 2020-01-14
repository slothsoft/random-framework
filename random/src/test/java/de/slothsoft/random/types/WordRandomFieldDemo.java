package de.slothsoft.random.types;

import de.slothsoft.random.types.wordgen.StandardWordGeneratorConfig;

public class WordRandomFieldDemo {

	public static void main(String[] args) {
		final WordRandomField randomField = new WordRandomField();

		for (final StandardWordGeneratorConfig config : StandardWordGeneratorConfig.values()) {
			System.out.println(config);
			randomField.setConfig(config);

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.print(randomField.nextValue());
					System.out.print(' ');
				}
				System.out.print('\n');
			}
			System.out.println();
		}
	}
}
