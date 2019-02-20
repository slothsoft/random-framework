package de.slothsoft.random;

/**
 * A option used for configuringthe creation process.
 * 
 * @author Steffi
 * 
 */
public class Option {

	private String attributeName;
	private String optionName;
	private Object value;

	/**
	 * The default constructor.
	 */

	public Option(String attributeName, String optionName, Object value) {
		this.attributeName = attributeName;
		this.optionName = optionName;
		this.value = value;
	}

	/**
	 * The name of the attribute in the class to be filled
	 */

	public String getAttributeName() {
		return this.attributeName;
	}

	/**
	 * The name of the attribute in the class to be filled.
	 */

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * The option to be set. Stored inside the Options interface.
	 */

	public String getOptionName() {
		return this.optionName;
	}

	/**
	 * The option to be set. Stored inside the Options interface.
	 */

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	/**
	 * The value of the option. Could be anything.
	 */

	public Object getValue() {
		return this.value;
	}

	/**
	 * The value of the option. Could be anything.
	 */

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		int hashCode = this.optionName == null ? 0 : this.optionName.hashCode();
		hashCode *= 13;
		hashCode += this.attributeName == null ? 0 : this.attributeName
				.hashCode();
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Option) {
			Option that = (Option) obj;
			if (equalsIgnoreCase(that.attributeName, this.attributeName)
					&& equals(that.optionName, this.optionName)) {
				return true;
			}
		}
		return false;
	}

	private boolean equals(String s1, String s2) {
		return s1 == s2 || (s1 != null && s1.equals(s2));
	}

	private boolean equalsIgnoreCase(String s1, String s2) {
		return s1 == s2 || (s1 != null && s1.equalsIgnoreCase(s2));
	}

}
