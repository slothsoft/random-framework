package de.slothsoft.random.types;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.slothsoft.random.RandomField;

public class CollectionRandomFieldListTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private List<Short> value;

		public List<Short> getValue() {
			return this.value;
		}

		public void setValue(List<Short> value) {
			this.value = value;
		}

	}

	public CollectionRandomFieldListTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new CollectionRandomField(ArrayList::new, new ShortRandomField());
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
