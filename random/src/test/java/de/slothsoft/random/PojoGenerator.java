package de.slothsoft.random;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public final class PojoGenerator {

	public static Class<?> generate(String className, String propertyName, Class<?> propertyClass)
			throws NotFoundException, CannotCompileException {
		return generate(className, Collections.singletonMap(propertyName, propertyClass));
	}

	public static Class<?> generate(String className, Map<String, Class<?>> properties)
			throws NotFoundException, CannotCompileException {

		final ClassPool pool = ClassPool.getDefault();
		final CtClass newClass = pool.makeClass(className);

		for (final Entry<String, Class<?>> entry : properties.entrySet()) {
			addProperty(newClass, entry.getKey(), entry.getValue());
		}

		return newClass.toClass();
	}

	private static void addProperty(final CtClass newClass, String propertyName, Class<?> propertyClass)
			throws CannotCompileException, NotFoundException {
		newClass.addField(new CtField(resolveCtClass(propertyClass), propertyName, newClass));
		newClass.addMethod(generateGetter(newClass, propertyName, propertyClass));
		newClass.addMethod(generateSetter(newClass, propertyName, propertyClass));
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
			throws CannotCompileException {
		final String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		final StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
			throws CannotCompileException {
		final String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		final StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ")
				.append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
				.append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
		final ClassPool pool = ClassPool.getDefault();
		return pool.get(clazz.getName());
	}

	private PojoGenerator() {
		// hide me
	}
}