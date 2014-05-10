package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class ShortTest {

	private static final short START = 5;
	private static final short END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<ShortPerson> factory = new DefaultRandomFactory<ShortPerson>(
				ShortPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (ShortPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() != null);
			Assert.assertTrue(person.getValue().shortValue() >= START);
			Assert.assertTrue(person.getValue().shortValue() <= END);
		}
	}

	@Test
	public void primitiveTest() {
		RandomFactory<ReallySmallPerson> factory = new DefaultRandomFactory<ReallySmallPerson>(
				ReallySmallPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (ReallySmallPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() >= START);
			Assert.assertTrue(person.getValue() <= END);
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoShortPerson> factory = new DefaultRandomFactory<NoShortPerson>(
				NoShortPerson.class);
		for (NoShortPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class ShortPerson {

		private Short value;

		public Short getValue() {
			return this.value;
		}

		public void setValue(Short value) {
			this.value = value;
		}
	}

	public static class ReallySmallPerson {

		private short value;

		public short getValue() {
			return this.value;
		}

		public void setValue(short value) {
			this.value = value;
		}
	}

	public static class NoShortPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
