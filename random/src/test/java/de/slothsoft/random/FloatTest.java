package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class FloatTest {

	private static final float START = 5;
	private static final float END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<FloatPerson> factory = new DefaultRandomFactory<FloatPerson>(
				FloatPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (FloatPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() != null);
			Assert.assertTrue(person.getValue().floatValue() >= START);
			Assert.assertTrue(person.getValue().floatValue() <= END);
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
		RandomFactory<NoFloatPerson> factory = new DefaultRandomFactory<NoFloatPerson>(
				NoFloatPerson.class);
		for (NoFloatPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class FloatPerson {

		private Float value;

		public Float getValue() {
			return this.value;
		}

		public void setValue(Float value) {
			this.value = value;
		}
	}

	public static class SmallPerson {

		private float value;

		public float getValue() {
			return this.value;
		}

		public void setValue(float value) {
			this.value = value;
		}
	}

	public static class NoFloatPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
