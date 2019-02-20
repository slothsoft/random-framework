package de.slothsoft.random;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Handler with common methods concering file in- and output
 * 
 * @author Steffi
 * 
 */

public class FileUtil {

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Reads a (text) <code>File</code> into a string array. Each line is a new
	 * string in this array.
	 * 
	 * @param file
	 *            - the file to be read
	 * @return a string array
	 * @throws IOException
	 */

	public static String[] readFile(File file) throws IOException {
		return readFile(new FileReader(file));
	}

	/**
	 * Reads a <code>Reader</code> into a string array. Each line is a new
	 * String in this array.
	 * 
	 * @param reader
	 *            - the reader to be read
	 * @return a string array
	 * @throws IOException
	 */

	public static String[] readFile(Reader reader) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(reader);
		ArrayList<String> values = new ArrayList<String>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			values.add(line);
		}

		return values.toArray(new String[0]);
	}

	public static String readFile(InputStream stream) throws IOException {
		StringBuilder sb = new StringBuilder();

		int line;
		while ((line = stream.read()) != -1) {
			sb.append((char) line);
		}

		return sb.toString();
	}

	/**
	 * Writes an array of strings into a file.
	 * 
	 * @param file
	 *            - the file to be written to
	 * @param strings
	 *            - the lines
	 * @throws IOException
	 */

	public static void writeFile(File file, String[] strings)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		for (String string : strings) {
			sb.append(string);
			sb.append(LINE_SEPARATOR);
		}
		writeFile(file, sb);
	}

	/**
	 * Writes a <code>StringBuilder</code> into a file.
	 * 
	 * @param file
	 *            - the file to be written to
	 * @param sb
	 *            - the line
	 * @throws IOException
	 */

	public static void writeFile(File file, StringBuilder sb)
			throws IOException {
		writeFile(file, sb.toString());
	}

	/**
	 * Writes a <code>String</code> into a file.
	 * 
	 * @param file
	 *            - the file to be written to
	 * @param strings
	 *            - the line
	 * @throws IOException
	 */
	public static void writeFile(File file, String string) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(string);
		writer.close();
	}

}
