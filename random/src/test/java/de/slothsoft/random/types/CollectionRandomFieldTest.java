package de.slothsoft.random.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;

public class CollectionRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private Collection<Boolean> value;

		public Collection<Boolean> getValue() {
			return this.value;
		}

		public void setValue(Collection<Boolean> value) {
			this.value = value;
		}

	}

	public CollectionRandomFieldTest() {
		super(new Pojo());
	}

	@Override
	protected RandomField createRandomField() {
		return new CollectionRandomField(ArrayList::new, new BooleanRandomField());
	}

	@Test
	public void testCreateCollectionConstructorCollection() throws Exception {
		final Supplier<Collection<?>> supplier = CollectionRandomField.createCollectionConstructor(Collection.class);
		Assert.assertNotNull(supplier);
		Assert.assertNotNull(supplier.get());
	}

	@Test
	public void testCreateCollectionConstructorSet() throws Exception {
		final Supplier<Collection<?>> supplier = CollectionRandomField.createCollectionConstructor(Set.class);
		Assert.assertNotNull(supplier);
		Assert.assertNotNull(supplier.get());
	}

	@Test
	public void testCreateCollectionConstructorList() throws Exception {
		final Supplier<Collection<?>> supplier = CollectionRandomField.createCollectionConstructor(List.class);
		Assert.assertNotNull(supplier);
		Assert.assertNotNull(supplier.get());
	}

	@Test
	public void testCreateCollectionConstructorQueue() throws Exception {
		final Supplier<Collection<?>> supplier = CollectionRandomField.createCollectionConstructor(Queue.class);
		Assert.assertNotNull(supplier);
		Assert.assertNotNull(supplier.get());
	}

	@Test
	public void testCreateCollectionConstructorOther() throws Exception {
		final Supplier<Collection<?>> supplier = CollectionRandomField.createCollectionConstructor(Deque.class);
		Assert.assertNull(supplier);
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
