package de.slothsoft.random;

/**
 * Interface for storing the display names, that are in fact ids, of the random
 * fields that can be accessed.
 * 
 * @author Steffi
 * 
 */
public interface RandomFields {

	/**
	 * First name of a person. Can be configured by
	 * <code>de.slothsoft.random.Gender</code>.
	 */

	String FIRST_NAME = "first-name";

	/**
	 * Last name of a person.
	 */

	String LAST_NAME = "last-name";

	/**
	 * Name of a street with house number.
	 */

	String STREET = "street-name";

	/**
	 * Name of a city
	 */

	String CITY = "city-name";

	/**
	 * Birthday of something or someone. Is on default between now and 100 years
	 * ago.
	 */

	String BIRTHDAY = "birthday";

	/**
	 * A date of some sort. Is on default between ten years ago and ten years in
	 * the future.
	 */

	String DATE = "date";

	/**
	 * A number of some sort.
	 */

	String INTEGER = "integer";

	/**
	 * A number of some sort.
	 */

	String SHORT = "short";

	/**
	 * A number of some sort.
	 */

	String LONG = "long";

	/**
	 * A number of some sort.
	 */

	String FLOAT = "float";

	/**
	 * A number of some sort.
	 */

	String DOUBLE = "double";

	/**
	 * A number of some sort.
	 */

	String BIG_INTEGER = "big-integer";

	/**
	 * A number of some sort.
	 */

	String BIG_DECIMAL = "big-decimal";

	/**
	 * A boolean of some sort.
	 */

	String BOOLEAN = "boolean";
}
