package de.slothsoft.random.types;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import de.slothsoft.random.RandomField;

public class CollectionRandomFieldQueueTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Queue<LocalDate> value;

		public Queue<LocalDate> getValue() {
			return this.value;
		}

		public void setValue(Queue<LocalDate> value) {
			this.value = value;
		}

	}

	public CollectionRandomFieldQueueTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new CollectionRandomField(LinkedList::new, new LocalDateRandomField());
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
