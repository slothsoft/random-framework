package de.slothsoft.random.types.wordgen.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import de.slothsoft.random.types.wordgen.WordGeneratorConfig;
import de.slothsoft.random.types.wordgen.WordGeneratorConfigUtil;

public class EnglishGenerator {

	private static final String BOOK_URL = "http://www.gutenberg.org/files/22396/22396-8.txt";

	public static void main(String[] args) throws Exception {
		final WordGeneratorConfigGenerator generator = new WordGeneratorConfigGenerator();
		generator.setIsLetterFunction(c -> "abcdefghijklmnopqrstuvwxyz".contains(String.valueOf((char) c)));
		generator.setEncoding(Charset.forName("ISO-8859-1"));
		generator.setStartPhrase("22396-h.zip)");
		generator.setEndPhrase("***END OF THE PROJECT GUTENBERG");

		final File exportFile = new File("target/english.properties");
		try (InputStream input = new URL(BOOK_URL).openStream()) {
			final WordGeneratorConfig config = generator.generateFromStream(input);

			try (FileOutputStream output = new FileOutputStream(exportFile)) {
				WordGeneratorConfigUtil.storeConfig(output, config);
			}
		}
		System.out.println("Finished generation: " + exportFile);
	}
}
