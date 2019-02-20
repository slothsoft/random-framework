package de.slothsoft.random;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class BigIntegerTest {

	private static final long START = 5;
	private static final long END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<BigIntegerPerson> factory = new DefaultRandomFactory<BigIntegerPerson>(
				BigIntegerPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("bigInteger", Options.NUMBER_RANGE_START, START));
		options.add(new Option("bigInteger", Options.NUMBER_RANGE_END, END));

		for (BigIntegerPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getBigInteger() != null);
			Assert.assertTrue(person.getBigInteger().longValue() >= START);
			Assert.assertTrue(person.getBigInteger().longValue() <= END);
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoBigIntegerPerson> factory = new DefaultRandomFactory<NoBigIntegerPerson>(
				NoBigIntegerPerson.class);
		for (NoBigIntegerPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class BigIntegerPerson {

		private BigInteger bigInteger;

		public BigInteger getBigInteger() {
			return this.bigInteger;
		}

		public void setBigInteger(BigInteger value) {
			this.bigInteger = value;
		}
	}

	public static class NoBigIntegerPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
