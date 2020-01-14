package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public class CityRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String city;

		public String getCity() {
			return this.city;
		}

		public void setCity(String value) {
			this.city = value;
		}
	}

	public CityRandomFieldTest() {
		super(new Pojo());
		this.property = "city";
	}

	@Override
	protected RandomField createRandomField() {
		return new CityRandomField();
	}

}
