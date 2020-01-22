package de.slothsoft.random.types;

import de.slothsoft.random.RandomField;

/**
 * A {@link RandomField} representing an object that is filled with a random
 * {@link Enum}'s list element.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class EnumRandomField<E extends Enum<E>> extends ElementFromListRandomField<E> {

	/**
	 * Default constructor.
	 *
	 * @param enumClass the {@link Enum} class; cannot be null
	 */

	public EnumRandomField(Class<E> enumClass) {
		super(enumClass.getEnumConstants());
	}
}
