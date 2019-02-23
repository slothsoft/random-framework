package de.slothsoft.random.types;

import java.util.Objects;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing an arbitrary object that is filled with a random
 * list element.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

public class ElementFromListRandomField<E> implements RandomField {

	private final E[] possibleElements;

	/**
	 * Some constructor.
	 *
	 * @param possibleElements - the elements that are possible here; never null
	 */

	public ElementFromListRandomField(E[] possibleElements) {
		this.possibleElements = Objects.requireNonNull(possibleElements);
	}

	@Override
	public E nextValue() {
		return this.possibleElements[RND.nextInt(this.possibleElements.length)];
	}

}
