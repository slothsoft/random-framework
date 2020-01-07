package de.slothsoft.random.it;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.types.LastNameRandomField;

public class LastNameRandomFieldTest {

	@Test
	public void testConstructor() throws Exception {
		final LastNameRandomField field = new LastNameRandomField();

		Assert.assertNotNull(field);
	}
}
