package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * For internal use only. Helps with the mapping...
 * 
 * @author Steffi
 * 
 */
class MappingUtil {

	public static final String GET_PREFIX = "get";
	public static final String SET_PREFIX = "set";

	/**
	 * Guesses the mapping from the actual name of the getter and setter
	 * 
	 * @param clazz
	 *            - the class
	 * @return a mapping
	 */

	public static Map<String, String> guessMapping(Class<?> clazz) {
		Map<String, Class<?>> attributes = getAttributes(clazz);
		Map<String, String> result = new HashMap<String, String>();

		for (String attribute : attributes.keySet()) {
			RandomField<?> field = Database.getRandomFieldByAttributeName(
					attribute, attributes.get(attribute));
			if (field == null) {
				RandomFactoryLogger.logWarning("Could not map attribute " + attribute);
			} else {
				result.put(attribute, field.getDisplayName());
			}
		}

		return result;
	}

	/**
	 * Attributes are fields that have a setter with only one parameter.
	 */

	public static Map<String, Class<?>> getAttributes(Class<?> clazz) {
		Map<String, Class<?>> setters = new HashMap<String, Class<?>>();

		for (Method method : clazz.getMethods()) {
			String name = method.getName();
			if (name.startsWith(SET_PREFIX)) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length == 1) {
					setters.put(name.substring(SET_PREFIX.length()),
							parameterTypes[0]);
				}
			}
		}

		return setters;
	}

	/**
	 * Fills an object value depending on the mapping.
	 * 
	 * @param value
	 *            - the value to be filled
	 * @param mapping
	 *            - the mapping used for filling
	 * @param options
	 *            - some information
	 */

	public static void fill(Object value, Map<String, String> mapping,
			Set<Option> options) {
		for (String attribute : mapping.keySet()) {
			String setterName = getSetterName(attribute);
			try {
				RandomField<?> randomField = Database
						.getRandomFieldByDisplayName(mapping.get(attribute));
				if (randomField != null) {
					Set<Option> subSet = getSubSet(attribute, options);
					for (Class<?> attributeClass : randomField
							.getAttributeClasses()) {
						try {
							Method setter = value.getClass().getMethod(
									setterName, attributeClass);
							setter.invoke(value, randomField
									.getRandomAttribute(attribute,
											attributeClass, subSet));
						} catch (NoSuchMethodException e) {
							// wrong attribute class, just go on
						}
					}
				}
			} catch (Exception e) {
				throw new RandomException("Could not set attribute "
						+ attribute, e);
			}
		}
	}

	public static String getSetterName(String attribute) {
		return SET_PREFIX + attribute.substring(0, 1).toUpperCase()
				+ attribute.substring(1);
	}

	/**
	 * Returns the sub set of options for the wanted attribute
	 * 
	 * @param attribute
	 * @param options
	 * @return
	 */

	private static Set<Option> getSubSet(String attribute, Set<Option> options) {
		if (options.size() == 0) {
			return options;
		}
		Set<Option> result = new HashSet<Option>();
		for (Option option : options) {
			if (attribute.equalsIgnoreCase(option.getAttributeName())) {
				result.add(option);
			}
		}
		return result;
	}
}
