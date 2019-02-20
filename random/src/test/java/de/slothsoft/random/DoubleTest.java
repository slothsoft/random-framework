package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class DoubleTest {

	private static final double START = 5;
	private static final double END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<DoublePerson> factory = new DefaultRandomFactory<DoublePerson>(
				DoublePerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (DoublePerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() != null);
			Assert.assertTrue(person.getValue().doubleValue() >= START);
			Assert.assertTrue(person.getValue().doubleValue() <= END);
		}
	}

	@Test
	public void primitiveTest() {
		RandomFactory<SmallPerson> factory = new DefaultRandomFactory<SmallPerson>(
				SmallPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (SmallPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() >= START);
			Assert.assertTrue(person.getValue() <= END);
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoDoublePerson> factory = new DefaultRandomFactory<NoDoublePerson>(
				NoDoublePerson.class);
		for (NoDoublePerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class DoublePerson {

		private Double value;

		public Double getValue() {
			return this.value;
		}

		public void setValue(Double value) {
			this.value = value;
		}
	}

	public static class SmallPerson {

		private double value;

		public double getValue() {
			return this.value;
		}

		public void setValue(double value) {
			this.value = value;
		}
	}

	public static class NoDoublePerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
