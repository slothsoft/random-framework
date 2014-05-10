package de.slothsoft.random.io;

import java.io.InputStream;
import java.text.MessageFormat;

import de.slothsoft.random.FileUtil;
import de.slothsoft.random.RandomFactoryLogger;

public class TextDatabaseReader extends AbstractDatabaseReader {

	private static final String ENTRIES_FILE = "text/{0}.txt";
	private static String SYNONYM_FILE = "synonyms/{0}s.txt";

	@Override
	public String[] readSynonyms(String fileName) {
		String file = MessageFormat.format(SYNONYM_FILE, fileName);
		return readFile(file);
	}

	@Override
	public String[] readAttributes(String fileName) {
		String file = MessageFormat.format(ENTRIES_FILE, fileName);
		return readFile(file);
	}

	protected String[] readFile(String id) {
		try {
			InputStream stream = getClass().getResourceAsStream(id);
			return FileUtil.readFile(stream).split("\r\n");
		} catch (Exception e) {
			RandomFactoryLogger.logError("Could not read file: " + id);
			return new String[0];
		}
	}

}
