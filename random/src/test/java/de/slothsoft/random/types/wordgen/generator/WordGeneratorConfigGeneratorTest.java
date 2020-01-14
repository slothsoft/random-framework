package de.slothsoft.random.types.wordgen.generator;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.types.wordgen.WordGeneratorConfig;

public class WordGeneratorConfigGeneratorTest {

	private static final double DELTA = 0.0001;

	private final WordGeneratorConfigGenerator generator = new WordGeneratorConfigGenerator();

	@Test
	public void testStandardWordLength() {
		final WordGeneratorConfig config = this.generator.generateFromString("Hello World");

		Assert.assertEquals(5, config.getStandardWordLength(), DELTA);
	}

}
