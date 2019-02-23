package de.slothsoft.random.example;

import java.util.HashMap;
import java.util.Map;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;

/**
 * This example shows how to create your own implementation of {@link RandomField}.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

public class CustomRandomFieldExample {

	public static void main(String[] args) {

		final Map<String, RandomField> mapping = new HashMap<>();
		mapping.put("number", new RandomField() {

			@Override
			public Object nextValue() {
				// chosen by fair dice roll.
				// guaranteed to be random.
				return Integer.valueOf(4);
			}
		});

		final RandomFactory<DiceRoll> factory = new RandomFactory<>(DiceRoll::new, mapping);
		System.out.println(factory.createSingle());
	}

	public static class DiceRoll {

		private int number;

		public int getNumber() {
			return this.number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		@Override
		public String toString() {
			return "DiceRoll: " + this.number;
		}
	}

}
