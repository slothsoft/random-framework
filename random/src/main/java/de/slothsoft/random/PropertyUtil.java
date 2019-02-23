package de.slothsoft.random;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
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

	static void setProperty(Object pojo, String propertyName, Object value) {
		try {
			final Method setter = getSetter(pojo.getClass(), propertyName);
			setter.invoke(pojo, value);
		} catch (final IllegalAccessException | InvocationTargetException e) {
			throw new RandomException("Could not call setter " + propertyName + " on " + pojo, e);
		}
	}

	static Method getSetter(Class<?> pojoClass, String propertyName) {
		try {
			final BeanInfo beaninfo = Introspector.getBeanInfo(pojoClass);
			final PropertyDescriptor descriptors[] = beaninfo.getPropertyDescriptors();

			for (final PropertyDescriptor descriptor : descriptors) {
				if (descriptor.getName().equals(propertyName)) {
					final Method setter = descriptor.getWriteMethod();
					if (setter == null)
						throw new RandomException(
								"Could not find setter for " + propertyName + " on " + pojoClass + "!");
					return setter;
				}
			}
			throw new RandomException("Could not find property " + propertyName + " on " + pojoClass + "!");

		} catch (final IntrospectionException e) {
			throw new RandomException("Could not introspect " + pojoClass, e);
		}
	}

	/**
	 * Properties are fields that have a setter with only one parameter. Right now
	 * properties start with a capital letter.
	 */

	static Map<String, Class<?>> getProperties(Class<?> clazz) {
		try {
			final Map<String, Class<?>> setters = new HashMap<>();

			final BeanInfo beaninfo = Introspector.getBeanInfo(clazz);
			final PropertyDescriptor descriptors[] = beaninfo.getPropertyDescriptors();

			for (final PropertyDescriptor descriptor : descriptors) {
				if (!descriptor.getName().equals("class")) {
					setters.put(descriptor.getName(), descriptor.getPropertyType());
				}
			}
			return setters;
		} catch (final IntrospectionException e) {
			throw new RandomException("Could not introspect " + clazz, e);
		}
	}
}
