package de.slothsoft.random.types;

import org.junit.Test;

import de.slothsoft.random.RandomField;

public class ArrayRandomFieldCityTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String[] cities;

		public String[] getCities() {
			return this.cities;
		}

		public void setCities(String[] cities) {
			this.cities = cities;
		}

	}

	public ArrayRandomFieldCityTest() {
		super(new Pojo());
		this.property = "cities";
	}

	@Override
	protected RandomField createRandomField() {
		return new ArrayRandomField(String.class, new CityRandomField());
	}

	@Override
	@Test
	public void testForClassGuess() throws Exception {
		// is not guessed on it's own
	}

	@Override
	@Test
	public void testConstructorGuess() throws Exception {
		// is not guessed on it's own
	}

}
