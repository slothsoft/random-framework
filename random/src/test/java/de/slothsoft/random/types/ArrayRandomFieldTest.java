package de.slothsoft.random.types;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class ArrayRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String[] value;

		public String[] getValue() {
			return this.value;
		}

		public void setValue(String[] cities) {
			this.value = cities;
		}

	}

	private ArrayRandomField arrayRandomField;

	public ArrayRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		this.arrayRandomField = new ArrayRandomField(String.class, new CityRandomField());
		return this.arrayRandomField;
	}

	@Test
	public void testSetArrayLength() throws Exception {
		this.arrayRandomField.setArrayLength(2);
		Assert.assertEquals(2, this.arrayRandomField.getArrayLength());

		this.arrayRandomField.arrayLength(3);
		Assert.assertEquals(3, this.arrayRandomField.getArrayLength());
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
