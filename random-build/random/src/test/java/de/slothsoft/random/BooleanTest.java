package de.slothsoft.random;

import junit.framework.Assert;

import org.junit.Test;

public class BooleanTest {

	@Test
	public void positiveTest() {
		RandomFactory<BooleanPerson> factory = new DefaultRandomFactory<BooleanPerson>(
				BooleanPerson.class);

		for (BooleanPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() != null);
		}
	}

	@Test
	public void primitiveTest() {
		RandomFactory<SmallPerson> factory = new DefaultRandomFactory<SmallPerson>(
				SmallPerson.class);

		for (SmallPerson person : factory.create(100)) {
			// what could we check? o_O
			person.getValue();
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoBooleanPerson> factory = new DefaultRandomFactory<NoBooleanPerson>(
				NoBooleanPerson.class);
		for (NoBooleanPerson person : factory.create(100)) {
			Assert.assertTrue(person.getValue() == null);
		}
	}

	public static class BooleanPerson {

		private Boolean value;

		public Boolean getValue() {
			return this.value;
		}

		public void setValue(Boolean value) {
			this.value = value;
		}
	}

	public static class SmallPerson {

		private boolean value;

		public boolean getValue() {
			return this.value;
		}

		public void setValue(boolean value) {
			this.value = value;
		}
	}

	public static class NoBooleanPerson {

		private String value;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
