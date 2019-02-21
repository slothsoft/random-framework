package de.slothsoft.random.io;

// TODO: why an interface / abstract class?
public interface DatabaseReader {

	DatabaseReader instance = new TextDatabaseReader();

	public static DatabaseReader getInstance() {
		return instance;
	}

	/**
	 * Reads the synonyms of a property from a file.
	 * 
	 * @param fileName - the file name in question
	 * @return
	 */

	String[] readSynonyms(String fileName);

	/**
	 * Reads a list of elements to be used for a property.
	 * 
	 */

	// TODO: is this really different from above? 
	String[] readAttributes(String fileName);

}
