package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public class LastNameRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String lastName;

		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String value) {
			this.lastName = value;
		}
	}

	public LastNameRandomFieldTest() {
		super(new Pojo());
		this.property = "lastName";
	}


	@Override
	protected RandomField createRandomField() {
		return new LastNameRandomField();
	}

}
