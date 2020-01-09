package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

public class FirstNameRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String firstName;

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String value) {
			this.firstName = value;
		}
	}

	public FirstNameRandomFieldTest() {
		super(new Pojo());
		this.property = "firstName";
	}


	@Override
	protected RandomField createRandomField() {
		return new FirstNameRandomField();
	}

}
