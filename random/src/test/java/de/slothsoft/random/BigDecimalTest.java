package de.slothsoft.random;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class BigDecimalTest {

	private static final long START = 5;
	private static final long END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<BigDecimalPerson> factory = new DefaultRandomFactory<BigDecimalPerson>(
				BigDecimalPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("bigDecimal", Options.NUMBER_RANGE_START, START));
		options.add(new Option("bigDecimal", Options.NUMBER_RANGE_END, END));

		for (BigDecimalPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getBigDecimal() != null);
			Assert.assertTrue(person.getBigDecimal().longValue() >= START);
			Assert.assertTrue(person.getBigDecimal().longValue() <= END);
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoBigDecimalPerson> factory = new DefaultRandomFactory<NoBigDecimalPerson>(
				NoBigDecimalPerson.class);
		for (NoBigDecimalPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class BigDecimalPerson {

		private BigDecimal bigDecimal;

		public BigDecimal getBigDecimal() {
			return this.bigDecimal;
		}

		public void setBigDecimal(BigDecimal value) {
			this.bigDecimal = value;
		}
	}

	public static class NoBigDecimalPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
