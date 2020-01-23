package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class PostalCodeRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String postalCode;

		public String getPostalCode() {
			return this.postalCode;
		}

		public void setPostalCode(String zipCode) {
			this.postalCode = zipCode;
		}

	}

	private PostalCodeRandomField field;

	public PostalCodeRandomFieldTest() {
		super(new Pojo());
		this.property = "postalCode";
	}

	@Override
	protected RandomField createRandomField() {
		this.field = new PostalCodeRandomField();
		return this.field;
	}

	@Test
	public void testPatternGerman() throws Exception {
		this.field.setPattern(PostalCodeRandomField.PATTERN_GERMANY);

		Assert.assertEquals(PostalCodeRandomField.PATTERN_GERMANY, this.field.getPattern());

		for (int i = 0; i < 100; i++) {
			final String nextValue = this.field.nextValue();
			Assert.assertTrue("Value is broken: " + nextValue,
					Character.isDigit(nextValue.charAt(nextValue.length() - 1)));
		}
	}

	@Test
	public void testPatternUs() throws Exception {
		this.field.pattern(PostalCodeRandomField.PATTERN_US);

		Assert.assertEquals(PostalCodeRandomField.PATTERN_US, this.field.getPattern());

		for (int i = 0; i < 100; i++) {
			final String nextValue = this.field.nextValue();
			Assert.assertTrue("Value is broken: " + nextValue, Character.isDigit(nextValue.charAt(0)));
		}
	}

}
