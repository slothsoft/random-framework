package de.slothsoft.random.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing an {@link Collection}.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class CollectionRandomField implements RandomField {

	/**
	 * Returns a {@link Supplier} for creating instances of a collection class. Might
	 * return null.
	 *
	 * @param collectionClass a collection class
	 * @return a {@link Supplier} or null
	 */

	static Supplier<Collection<?>> createCollectionConstructor(Class<?> collectionClass) {
		if (collectionClass == List.class) {
			return ArrayList::new;
		}
		if (collectionClass == Set.class) {
			return TreeSet::new;
		}
		if (collectionClass == Queue.class) {
			return LinkedList::new;
		}
		if (collectionClass == Collection.class) {
			return ArrayList::new;
		}
		return null;
	}

	private final Supplier<Collection<?>> collectionConstructor;
	private final RandomField elementRandomField;

	private double nullProbability;
	private int collectionSize = 3;

	/**
	 * Constructor.
	 *
	 * @param collectionConstructor constructor to create the collection
	 * @param elementRandomField a generator for the elements
	 */

	public CollectionRandomField(Supplier<Collection<?>> collectionConstructor, RandomField elementRandomField) {
		this.collectionConstructor = Objects.requireNonNull(collectionConstructor);
		this.elementRandomField = Objects.requireNonNull(elementRandomField);
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		final Collection result = this.collectionConstructor.get();
		final int actualCollectionSize = (int) Math.max(1, (1 + RND.nextGaussian()) * this.collectionSize);
		for (int i = 0; i < actualCollectionSize; i++) {
			result.add(this.elementRandomField.nextValue());
		}
		return result;
	}

	/**
	 * Returns the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @return the probability between 0 and 1
	 */

	public double getNullProbability() {
		return this.nullProbability;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param newNullProbability the probability between 0 and 1
	 * @return this instance
	 */

	public CollectionRandomField nullProbability(double newNullProbability) {
		setNullProbability(newNullProbability);
		return this;
	}

	/**
	 * Sets the probability for this field returning null. If the value is 0 then no
	 * {@link #nextValue()} is null, if it is 1 then every {@link #nextValue()} is null.
	 *
	 * @param nullProbability the probability between 0 and 1
	 */

	public void setNullProbability(double nullProbability) {
		if (nullProbability < 0 || nullProbability > 1) {
			throw new IllegalArgumentException("Null probability must be between 0 and 1!");
		}
		this.nullProbability = nullProbability;
	}

	/**
	 * Returns the standard collection size. A Gaussian function is used to scatter the
	 * actual collection size around this value.
	 *
	 * @return collection size
	 */

	public int getCollectionSize() {
		return this.collectionSize;
	}

	/**
	 * Sets the standard collection size. A Gaussian function is used to scatter the
	 * actual collection size around this value.
	 *
	 * @param newCollectionSize collection size
	 * @return this instance
	 */

	public CollectionRandomField collectionSize(int newCollectionSize) {
		setCollectionSize(newCollectionSize);
		return this;
	}

	/**
	 * Sets the standard collection size. A Gaussian function is used to scatter the
	 * actual collection size around this value.
	 *
	 * @param collectionSize collection size
	 */

	public void setCollectionSize(int collectionSize) {
		this.collectionSize = collectionSize;
	}

}
