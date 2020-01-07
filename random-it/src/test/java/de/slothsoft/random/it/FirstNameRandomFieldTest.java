package de.slothsoft.random.it;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.types.FirstNameRandomField;

public class FirstNameRandomFieldTest {

	@Test
	public void testConstructor() throws Exception {
		final FirstNameRandomField field = new FirstNameRandomField();

		Assert.assertNotNull(field);
	}
}
