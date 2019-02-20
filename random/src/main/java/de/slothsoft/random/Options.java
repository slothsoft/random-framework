package de.slothsoft.random;

/**
 * Options that can be set to the various fields.
 * 
 * @author Steffi
 * 
 */
public interface Options {

	/**
	 * Option for setting the gender of the first names. Values have to be of
	 * type <code>de.slothsoft.random.Gender</code>.
	 */
	String GENDER = "gender";

	/**
	 * Option for setting the range of number fields. Values have to be of type
	 * <code>java.lang.Number</code>. Setting a <code>NUMBER_RANGE_START</code>
	 * of 10 will result in values 10 and up, including the 10.
	 */

	String NUMBER_RANGE_START = "numberRangeStart";

	/**
	 * Option for setting the range of number fields. Values have to be of type
	 * <code>java.lang.Number</code>. Setting a <code>NUMBER_RANGE_END</code> of
	 * 10 will result in values up to 9, excluding the 10.
	 */

	String NUMBER_RANGE_END = "numberRangeEnd";
}
