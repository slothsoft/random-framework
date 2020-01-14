package de.slothsoft.random.types;

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

	public StreetRandomFieldTest() {
		super(new Pojo());
		this.property = "street";
	}

	@Override
	protected RandomField createRandomField() {
		return new StreetRandomField();
	}

}
