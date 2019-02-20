package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class IntegerTest {

	private static final int START = 5;
	private static final int END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<IntegerPerson> factory = new DefaultRandomFactory<IntegerPerson>(
				IntegerPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("integer", Options.NUMBER_RANGE_START, START));
		options.add(new Option("integer", Options.NUMBER_RANGE_END, END));

		for (IntegerPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getInteger() != null);
			Assert.assertTrue(person.getInteger().intValue() >= START);
			Assert.assertTrue(person.getInteger().intValue() <= END);
		}
	}

	@Test
	public void primitiveTest() {
		RandomFactory<IntPerson> factory = new DefaultRandomFactory<IntPerson>(
				IntPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("integer", Options.NUMBER_RANGE_START, START));
		options.add(new Option("integer", Options.NUMBER_RANGE_END, END));

		for (IntPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getInteger() >= START);
			Assert.assertTrue(person.getInteger() <= END);
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoIntegerPerson> factory = new DefaultRandomFactory<NoIntegerPerson>(
				NoIntegerPerson.class);
		for (NoIntegerPerson person : factory.create(100)) {
			Assert.assertTrue(person.getInteger() == null);
		}
	}

	public static class IntegerPerson {

		private Integer integer;

		public Integer getInteger() {
			return this.integer;
		}

		public void setInteger(Integer integer) {
			this.integer = integer;
		}
	}

	public static class IntPerson {

		private int integer;

		public int getInteger() {
			return this.integer;
		}

		public void setInteger(int integer) {
			this.integer = integer;
		}
	}

	public static class NoIntegerPerson {

		private String integer;

		public String getInteger() {
			return this.integer;
		}

		public void setInteger(String integer) {
			this.integer = integer;
		}

	}

}
