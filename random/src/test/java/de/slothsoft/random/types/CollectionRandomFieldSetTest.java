package de.slothsoft.random.types;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import de.slothsoft.random.RandomField;

public class CollectionRandomFieldSetTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Set<Date> value;

		public Set<Date> getValue() {
			return this.value;
		}

		public void setValue(Set<Date> value) {
			this.value = value;
		}

	}

	public CollectionRandomFieldSetTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new CollectionRandomField(TreeSet::new, new DateRandomField());
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
