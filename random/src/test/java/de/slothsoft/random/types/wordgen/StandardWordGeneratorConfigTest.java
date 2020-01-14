package de.slothsoft.random.types.wordgen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * This class test the default behavior, e.g. fill fields with special names or types.
 */

@RunWith(Parameterized.class)
public class StandardWordGeneratorConfigTest {

	private static final double DELTA = 0.0000001;

	@Parameters(name = "{0}")
	public static Collection<Object[]> data() throws Exception {
		final List<Object[]> result = new ArrayList<>();
		for (final StandardWordGeneratorConfig config : StandardWordGeneratorConfig.values()) {
			result.add(new Object[]{config});
		}
		return result;
	}

	private final StandardWordGeneratorConfig config;

	public StandardWordGeneratorConfigTest(StandardWordGeneratorConfig config) {
		this.config = config;
	}

	@Test
	public void testSupportedCharacters() throws Exception {
		final char[] supportedCharacters = this.config.getSupportedCharacters();

		Assert.assertNotNull(supportedCharacters);
		Assert.assertTrue(supportedCharacters.length >= 26);
	}

	@Test
	public void testStandardWordLength() throws Exception {
		final double standardWordLength = this.config.getStandardWordLength();

		Assert.assertTrue(standardWordLength > 1);
	}

	@Test
	public void testProbability() throws Exception {
		final String error = "Standard config has a problem with char ";

		for (final char c : this.config.getSupportedCharacters()) {
			final double probability = this.config.getProbability(c);

			Assert.assertNotEquals(error + c, 0, probability, DELTA);
		}
	}

}
