package de.slothsoft.random;

import org.junit.Assert;
import org.junit.Test;

public class PropertyUtilTest {

	public static class Pojo {

		String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	@Test
	public void testSetProperty() throws Exception {
		final Pojo pojo = new Pojo();
		PropertyUtil.setProperty(pojo, "value", String.class, "LOL");

		Assert.assertEquals("LOL", pojo.getValue());
	}

	@Test
	public void testSetPropertyNull() throws Exception {
		final Pojo pojo = new Pojo();
		pojo.setValue("-");
		PropertyUtil.setProperty(pojo, "value", String.class, null);

		Assert.assertEquals(null, pojo.getValue());
	}

	@Test(expected = RandomException.class)
	public void testSetPropertyWrongClass() throws Exception {
		final Pojo pojo = new Pojo();
		PropertyUtil.setProperty(pojo, "value", Integer.class, "LOL");
	}

	@Test(expected = RandomException.class)
	public void testSetPropertyWrongPropertyName() throws Exception {
		final Pojo pojo = new Pojo();
		PropertyUtil.setProperty(pojo, "walue", String.class, "LOL");
	}
}
