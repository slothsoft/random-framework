package de.slothsoft.random.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import de.slothsoft.random.RandomField;

/**
 * A field where you define a pattern and have a couple of random field to fill the
 * pattern with.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class PatternRandomField implements RandomField {

	private final Map<String, RandomField> components = new HashMap<>();
	private String pattern;
	private double nullProbability;

	/**
	 * Default constructor.
	 *
	 * @param pattern the pattern to fill
	 * @see #addComponent(String, RandomField)
	 */

	public PatternRandomField(String pattern) {
		this.pattern = Objects.requireNonNull(pattern);
	}

	@Override
	public String nextValue() {
		if (RND.nextDouble() < this.nullProbability) {
			return null;
		}
		String result = this.pattern;

		for (final Entry<String, RandomField> component : this.components.entrySet()) {

			final String key = component.getKey();
			int index = result.indexOf(key);
			while (index >= 0) {
				final Object value = component.getValue().nextValue();
				final String valueAsString = value == null ? "" : value.toString();

				result = result.substring(0, index) + valueAsString + result.substring(index + key.length());
				index = result.indexOf(key);
			}
		}
		return result;
	}

	/**
	 * Adds a component that can be accessed by {@link #nextValue()}.
	 *
	 * @param key the key that is replaced "as is"
	 * @param randomField the random field to replace this key with
	 */

	public void addComponent(String key, RandomField randomField) {
		this.components.put(key, randomField);
	}

	/**
	 * Adds components that can be accessed by {@link #nextValue()}.
	 *
	 * @param addedComponents with keys and random fields
	 * @see #addComponent(String, RandomField)
	 */

	public void addComponents(Map<String, RandomField> addedComponents) {
		this.components.putAll(addedComponents);
	}

	/**
	 * Removes a component that can be accessed by {@link #nextValue()}.
	 *
	 * @param key the key that is replaced "as is"
	 */

	public void removeComponent(String key) {
		this.components.remove(key);
	}

	/**
	 * Removes components that can be accessed by {@link #nextValue()}.
	 *
	 * @param keys the keys
	 * @see #removeComponent(String)
	 */

	public void removeComponents(Set<String> keys) {
		keys.forEach(this::removeComponent);
	}

	/**
	 * Returns the pattern with keys that get replaced during {@link #nextValue()}.
	 *
	 * @return the pattern
	 */

	public String getPattern() {
		return this.pattern;
	}

	/**
	 * Sets the pattern with keys that get replaced during {@link #nextValue()}.
	 *
	 * @param newPattern the pattern
	 * @return this instance
	 */

	public PatternRandomField pattern(String newPattern) {
		setPattern(newPattern);
		return this;
	}

	/**
	 * Sets the pattern with keys that get replaced during {@link #nextValue()}.
	 *
	 * @param pattern the pattern
	 */

	public void setPattern(String pattern) {
		this.pattern = Objects.requireNonNull(pattern);
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

	public PatternRandomField nullProbability(double newNullProbability) {
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
}
