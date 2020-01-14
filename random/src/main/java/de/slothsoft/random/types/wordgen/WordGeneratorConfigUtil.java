package de.slothsoft.random.types.wordgen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * A util class for handy methods surrounding the {@link WordGeneratorConfig}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public final class WordGeneratorConfigUtil {

	static class ConfigProperties extends Properties {

		private static final long serialVersionUID = 210296994033180536L;

		@Override
		public Set<Object> keySet() {
			return Collections.unmodifiableSet(new TreeSet<>(super.keySet()));
		}

		@Override
		public Set<Map.Entry<Object, Object>> entrySet() {
			final Set<Map.Entry<Object, Object>> set1 = super.entrySet();
			final Set<Map.Entry<Object, Object>> set2 = new LinkedHashSet<>(set1.size());

			final Iterator<Map.Entry<Object, Object>> iterator = set1.stream()
					.sorted((o1, o2) -> o1.getKey().toString().compareTo(o2.getKey().toString())).iterator();

			while (iterator.hasNext()) {
				set2.add(iterator.next());
			}
			return set2;
		}

		@Override
		public synchronized Enumeration<Object> keys() {
			return Collections.enumeration(new TreeSet<>(super.keySet()));
		}
	}

	private static final String PROP_SUPPORTED_CHARACTERS = "supportedCharacters";
	private static final String PROP_STANDARD_WORD_LENGTH = "standardWordLength";
	private static final String PREFIX_LETTER_ = "letter.";
	private static final String SUFFIX_PROBABILITY = ".probability";

	/**
	 * Loads the config from an input stream.
	 *
	 * @param stream a stream
	 * @return read config
	 * @throws IOException for all kinds of exceptions
	 */

	public static WordGeneratorConfig loadConfig(InputStream stream) throws IOException {
		final Properties props = new Properties();
		props.load(stream);

		final char[] supportedCharacters = ((String) props.getOrDefault(PROP_SUPPORTED_CHARACTERS,
				DefaultWordGeneratorConfig.DEFAULT_SUPPORTED_CHARACTERS)).toCharArray();
		final Letter[] supportedLetters = CharBuffer.wrap(supportedCharacters).chars()
				.mapToObj(c -> createLetter(props, (char) c)).toArray(Letter[]::new);

		final DefaultWordGeneratorConfig result = new DefaultWordGeneratorConfig();
		result.setSupportedLetters(supportedLetters);
		result.setStandardWordLength(Double.parseDouble((String) props.getOrDefault(PROP_STANDARD_WORD_LENGTH,
				String.valueOf(DefaultWordGeneratorConfig.DEFAULT_STANDARD_WORD_LENGTH))));
		return result;
	}

	private static Letter createLetter(Properties props, char c) {
		final double probability = Double.parseDouble((String) props
				.getOrDefault(PREFIX_LETTER_ + c + SUFFIX_PROBABILITY, String.valueOf(Letter.DEFAULT_PROBABILITY)));
		return new Letter(c).probability(probability);
	}

	/**
	 * Stores the config to an output stream.
	 *
	 * @param stream a stream
	 * @param config to be stored
	 * @throws IOException for all kinds of exceptions
	 */

	public static void storeConfig(OutputStream stream, WordGeneratorConfig config) throws IOException {
		final char[] supportedCharacters = config.getSupportedCharacters();

		final ConfigProperties props = new ConfigProperties();
		props.setProperty(PROP_SUPPORTED_CHARACTERS, new String(supportedCharacters));
		for (final char c : supportedCharacters) {
			props.setProperty(PREFIX_LETTER_ + c + SUFFIX_PROBABILITY, String.valueOf(config.getProbability(c)));
		}
		props.setProperty(PROP_STANDARD_WORD_LENGTH, String.valueOf(config.getStandardWordLength()));

		props.store(stream, null);
	}

	/**
	 * Helper method for implementing {@link #hashCode()}.
	 *
	 * @param thisObject a config
	 * @return a hashcode
	 */

	static int generateHashCode(WordGeneratorConfig thisObject) {
		final long temp = Double.doubleToLongBits(thisObject.getStandardWordLength());
		return 31 * (int) (temp ^ (temp >>> 32)) + Arrays.hashCode(thisObject.getSupportedCharacters());
	}

	/**
	 * Helper method for implementing {@link #equals(Object)}.
	 *
	 * @param thisObject a config
	 * @param obj another config
	 * @return true if equal
	 */

	static boolean areEqual(WordGeneratorConfig thisObject, Object obj) {
		if (thisObject == obj) return true;
		if (obj == null) return false;
		if (thisObject.getClass() != obj.getClass()) return false;
		final WordGeneratorConfig that = (DefaultWordGeneratorConfig) obj;
		if (Double.doubleToLongBits(thisObject.getStandardWordLength()) != Double
				.doubleToLongBits(that.getStandardWordLength()))
			return false;
		if (!Arrays.equals(thisObject.getSupportedCharacters(), that.getSupportedCharacters())) return false;
		return true;
	}

	private WordGeneratorConfigUtil() {
		// hide me
	}

}
