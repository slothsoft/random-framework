package de.slothsoft.random.types.wordgen;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.types.wordgen.WordGeneratorConfigUtil.ConfigProperties;

public class WordGeneratorConfigUtilTest {

	private static final double DELTA = 0.0001;

	@Test
	public void testEquals() throws Exception {
		assertEquals(new DefaultWordGeneratorConfig(), new DefaultWordGeneratorConfig());
		assertEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')),
				new DefaultWordGeneratorConfig().letters(new Letter('a')));
		assertEquals(new DefaultWordGeneratorConfig().standardWordLength(1),
				new DefaultWordGeneratorConfig().standardWordLength(1));
		assertEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(2),
				new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(2));
	}

	private static void assertEquals(DefaultWordGeneratorConfig expected, DefaultWordGeneratorConfig actual) {
		Assert.assertTrue(WordGeneratorConfigUtil.areEqual(expected, actual));
		Assert.assertEquals(WordGeneratorConfigUtil.generateHashCode(expected),
				WordGeneratorConfigUtil.generateHashCode(actual));
	}

	@Test
	public void testNotEquals() throws Exception {
		assertNotEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')),
				new DefaultWordGeneratorConfig().letters(new Letter('b')));
		assertNotEquals(new DefaultWordGeneratorConfig().standardWordLength(1),
				new DefaultWordGeneratorConfig().standardWordLength(2));
		assertNotEquals(new DefaultWordGeneratorConfig().letters(new Letter('a')).standardWordLength(1),
				new DefaultWordGeneratorConfig().letters(new Letter('b')).standardWordLength(2));
	}

	private static void assertNotEquals(DefaultWordGeneratorConfig expected, DefaultWordGeneratorConfig actual) {
		Assert.assertFalse(WordGeneratorConfigUtil.areEqual(expected, actual));
		Assert.assertNotEquals(WordGeneratorConfigUtil.generateHashCode(expected),
				WordGeneratorConfigUtil.generateHashCode(actual));
	}

	@Test
	public void testEqualsSpecial() throws Exception {
		final DefaultWordGeneratorConfig config = new DefaultWordGeneratorConfig();
		final String error = "Config has wrong equals() method: " + config;

		Assert.assertTrue(error, WordGeneratorConfigUtil.areEqual(config, config));

		Assert.assertFalse(error, WordGeneratorConfigUtil.areEqual(config, null));
		Assert.assertFalse(error, WordGeneratorConfigUtil.areEqual(config, new Object()));
	}

	@Test
	public void testLoadConfigEmpty() throws Exception {
		final WordGeneratorConfig config = WordGeneratorConfigUtil.loadConfig(new ByteArrayInputStream("".getBytes()));

		assertConfigEqual(config, new DefaultWordGeneratorConfig());
	}

	static void assertConfigEqual(final WordGeneratorConfig expected, WordGeneratorConfig actual) {
		Assert.assertArrayEquals(expected.getSupportedCharacters(), actual.getSupportedCharacters());
		Assert.assertEquals(expected.getStandardWordLength(), actual.getStandardWordLength(), DELTA);
	}

	@Test
	public void testStoreConfigEmpty() throws Exception {
		final WordGeneratorConfig config = new DefaultWordGeneratorConfig();

		final Path tempFile = Paths.get("target/" + System.currentTimeMillis() + ".properties");
		try (FileOutputStream output = new FileOutputStream(tempFile.toFile())) {
			WordGeneratorConfigUtil.storeConfig(output, config);
		}
		try (FileInputStream input = new FileInputStream(tempFile.toFile())) {
			final WordGeneratorConfig copy = WordGeneratorConfigUtil.loadConfig(input);
			assertConfigEqual(config, copy);
		}
	}

	@Test
	public void testStoreConfig() throws Exception {
		final DefaultWordGeneratorConfig config = new DefaultWordGeneratorConfig();
		config.setSupportedLetters(new Letter('a'), new Letter('b').probability(2), new Letter('c').probability(0.5));

		final Path tempFile = Paths.get("target/" + System.currentTimeMillis() + ".properties");
		try (FileOutputStream output = new FileOutputStream(tempFile.toFile())) {
			WordGeneratorConfigUtil.storeConfig(output, config);
		}
		try (FileInputStream input = new FileInputStream(tempFile.toFile())) {
			final WordGeneratorConfig copy = WordGeneratorConfigUtil.loadConfig(input);
			assertConfigEqual(config, copy);
		}
	}

	@Test
	public void testConfigProperties() throws Exception {
		final ConfigProperties properties = new ConfigProperties();
		properties.put("A", "1");
		properties.put("C", "3");
		properties.put("B", "2");

		Assert.assertEquals(Arrays.asList("A", "B", "C"), new ArrayList<>(properties.keySet()));
		Assert.assertEquals(Arrays.asList("A", "B", "C"), Collections.list(properties.keys()));
	}
}
