package de.slothsoft.random.types.wordgen.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import de.slothsoft.random.types.wordgen.WordGeneratorConfig;
import de.slothsoft.random.types.wordgen.WordGeneratorConfigUtil;

/**
 * The text I choose right now, "Alice's Abenteuer im Wunderland" is not only old
 * spelling, it's the one before that. But the percent values are actually quite close to
 * other sources (e.g. https://www.sttmedia.com/characterfrequency-german), so I think
 * that's fine.
 */

public class GermanGenerator {

	private static final String BOOK_URL = "https://www.gutenberg.org/files/19778/19778-8.txt";

	public static void main(String[] args) throws Exception {
		final WordGeneratorConfigGenerator generator = new WordGeneratorConfigGenerator();
		generator.setIsLetterFunction(c -> "abcdefghijklmnopqrstuvwxyzäöüß".contains(String.valueOf((char) c)));
		generator.setEncoding(Charset.forName("ISO-8859-1"));
		generator.setStartPhrase("Team at http://www.pgdp.net");
		generator.setEndPhrase("End of Project Gutenberg");

		final File exportFile = new File("target/german.properties");
		try (InputStream input = new URL(BOOK_URL).openStream()) {
			final WordGeneratorConfig config = generator.generateFromStream(input);

			try (FileOutputStream output = new FileOutputStream(exportFile)) {
				WordGeneratorConfigUtil.storeConfig(output, config);
			}
		}
		System.out.println("Finished generation: " + exportFile);
	}
}
