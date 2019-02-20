package de.slothsoft.random.io;

public abstract class AbstractDatabaseReader {

	private static AbstractDatabaseReader instance;

	public static AbstractDatabaseReader getInstance() {
		if (instance == null) {
			instance = new TextDatabaseReader();
		}
		return instance;
	}

	public abstract String[] readSynonyms(String fileName);

	public abstract String[] readAttributes(String fileName);

}
