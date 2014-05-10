package de.slothsoft.random;

public class Util {

	public static <T> boolean contains(T[] array, T value) {
		for (T a : array) {
			if (a == value || (a != null && a.equals(value))) {
				return true;
			}
		}
		return false;
	}
}
