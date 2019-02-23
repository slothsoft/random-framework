package de.slothsoft.random;

import java.util.Map;

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
		PropertyUtil.setProperty(pojo, "value", "LOL");

		Assert.assertEquals("LOL", pojo.getValue());
	}

	@Test
	public void testSetPropertyNull() throws Exception {
		final Pojo pojo = new Pojo();
		pojo.setValue("-");
		PropertyUtil.setProperty(pojo, "value", null);

		Assert.assertEquals(null, pojo.getValue());
	}

	@Test(expected = RandomException.class)
	public void testSetPropertyWrongPropertyName() throws Exception {
		final Pojo pojo = new Pojo();
		PropertyUtil.setProperty(pojo, "walue", "LOL");
	}

	@Test
	public void testGetProperties() throws Exception {
		final Map<String, Class<?>> properties = PropertyUtil.getProperties(Pojo.class);

		Assert.assertNotNull(properties);
		Assert.assertEquals("Wrong property count: " + properties, 1, properties.size());
		Assert.assertTrue("Key should exist: " + properties, properties.containsKey("value"));
		Assert.assertEquals(String.class, properties.get("value"));
	}
}
