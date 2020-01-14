package de.slothsoft.random.types.wordgen;

/**
 * A single letter with information.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class Letter {

	static final int DEFAULT_PROBABILITY = 1;

	final char character;
	double probability = DEFAULT_PROBABILITY;

	/**
	 * Default constructor.
	 *
	 * @param character character
	 */

	public Letter(char character) {
		this.character = character;
	}

	/**
	 * Returns the character.
	 *
	 * @return character
	 */

	public char getCharacter() {
		return this.character;
	}

	/**
	 * Returns the probability of the character.
	 *
	 * @return probability
	 */

	public double getProbability() {
		return this.probability;
	}

	/**
	 * Sets the probability of the character.
	 *
	 * @param newProbability probability
	 * @return this instance
	 */

	public Letter probability(double newProbability) {
		setProbability(newProbability);
		return this;
	}

	/**
	 * Sets the probability of the character.
	 *
	 * @param probability probability
	 */

	public void setProbability(double probability) {
		this.probability = probability;
	}

	@Override
	public int hashCode() {
		return (int) (31 * this.character + 7 * Double.doubleToLongBits(this.probability));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final Letter that = (Letter) obj;
		if (this.character != that.character) return false;
		if (Double.doubleToLongBits(this.probability) != Double.doubleToLongBits(that.probability)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Letter [character=" + this.character + ", probability=" + this.probability + "]";
	}

}
