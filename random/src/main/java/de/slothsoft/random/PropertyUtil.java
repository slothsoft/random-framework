package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple util class for everything property related. Might need to swap this class with
 * Apache utils or something like that in the future.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

final class PropertyUtil {

	private static final String SET_PREFIX = "set";

	static void setProperty(Object pojo, String propertyName, Class<?> propertyClass, Object value) {
		final String setterName = getSetterName(propertyName);
		try {
			final Method setter = pojo.getClass().getMethod(setterName, propertyClass);
			setter.invoke(pojo, value);
		} catch (final Exception e) {
			throw new RandomException("Could not set property " + propertyName, e);
		}
	}

	static String getSetterName(String property) {
		return SET_PREFIX + property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	/**
	 * Properties are fields that have a setter with only one parameter. Right now
	 * properties start with a capital letter.
	 */

	static Map<String, Class<?>> getProperties(Class<?> clazz) {
		final Map<String, Class<?>> setters = new HashMap<>();

		for (final Method method : clazz.getMethods()) {
			final String name = method.getName();
			if (name.startsWith(SET_PREFIX)) {
				final Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length == 1) {
					setters.put(name.substring(SET_PREFIX.length()), parameterTypes[0]);
				}
			}
		}
		return setters;
	}
}
