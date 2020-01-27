package de.slothsoft.random.types.wordgen;

import org.junit.Test;

public class StandardWordGeneratorConfigGeneralTest {

	@Test(expected = IllegalArgumentException.class)
	public void testReadConfigFileException() throws Exception {
		StandardWordGeneratorConfig.readConfigFile("unknown");
	}
}
