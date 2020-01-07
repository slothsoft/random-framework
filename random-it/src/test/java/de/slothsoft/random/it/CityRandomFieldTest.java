package de.slothsoft.random.it;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.types.CityRandomField;

public class CityRandomFieldTest {

	@Test
	public void testConstructor() throws Exception {
		final CityRandomField field = new CityRandomField();

		Assert.assertNotNull(field);
	}
}
