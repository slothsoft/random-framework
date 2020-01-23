package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class StreetRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String street;

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String value) {
			this.street = value;
		}
	}

	private StreetRandomField field;

	public StreetRandomFieldTest() {
		super(new Pojo());
		this.property = "street";
	}

	@Override
	protected RandomField createRandomField() {
		this.field = new StreetRandomField();
		return this.field;
	}

	@Test
	public void testPatternStreetFirst() throws Exception {
		this.field.setPattern(StreetRandomField.PATTERN_STREET_FIRST);

		Assert.assertEquals(StreetRandomField.PATTERN_STREET_FIRST, this.field.getPattern());

		for (int i = 0; i < 100; i++) {
			final String nextValue = this.field.nextValue();
			Assert.assertTrue("Value is broken: " + nextValue,
					Character.isDigit(nextValue.charAt(nextValue.length() - 1)));
		}
	}

	@Test
	public void testPatternHouseNumberFirst() throws Exception {
		this.field.pattern(StreetRandomField.PATTERN_HOUSE_NUMBER_FIRST);

		Assert.assertEquals(StreetRandomField.PATTERN_HOUSE_NUMBER_FIRST, this.field.getPattern());

		for (int i = 0; i < 100; i++) {
			final String nextValue = this.field.nextValue();
			Assert.assertTrue("Value is broken: " + nextValue, Character.isDigit(nextValue.charAt(0)));
		}
	}

}
