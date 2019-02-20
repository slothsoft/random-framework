package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class LongTest {

	private static final long START = 5;
	private static final long END = 13;

	@Test
	public void positiveTest() {
		RandomFactory<LongPerson> factory = new DefaultRandomFactory<LongPerson>(
				LongPerson.class);

		Set<Option> options = new HashSet<Option>();
		options.add(new Option("value", Options.NUMBER_RANGE_START, START));
		options.add(new Option("value", Options.NUMBER_RANGE_END, END));

		for (LongPerson person : factory.create(100, options)) {
			Assert.assertTrue(person.getValue() != null);
			Assert.assertTrue(person.getValue().longValue() >= START);
			Assert.assertTrue(person.getValue().longValue() <= END);
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
		RandomFactory<NoLongPerson> factory = new DefaultRandomFactory<NoLongPerson>(
				NoLongPerson.class);
		for (NoLongPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class LongPerson {

		private Long value;

		public Long getValue() {
			return this.value;
		}

		public void setValue(Long value) {
			this.value = value;
		}
	}

	public static class SmallPerson {

		private long value;

		public long getValue() {
			return this.value;
		}

		public void setValue(long value) {
			this.value = value;
		}
	}

	public static class NoLongPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
