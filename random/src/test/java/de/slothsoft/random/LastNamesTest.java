package de.slothsoft.random;

import junit.framework.Assert;

import org.junit.Test;

public class LastNamesTest {

	@Test
	public void positiveTest() {
		RandomFactory<LastNamePerson> factory = new DefaultRandomFactory<LastNamePerson>(
				LastNamePerson.class);
		String[] allLastNames = Database.getLastNames();
		Assert.assertTrue(!Util.contains(allLastNames, null));
		for (LastNamePerson person : factory.create(100)) {
			Assert.assertTrue(Util.contains(allLastNames, person.getLastName()));
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoLastNamePerson> factory = new DefaultRandomFactory<NoLastNamePerson>(
				NoLastNamePerson.class);
		for (NoLastNamePerson person : factory.create(100)) {
			Assert.assertTrue(person.getLastName() == null);
		}
	}

	public static class LastNamePerson {

		private String lastName;

		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}

	public static class NoLastNamePerson {

		private NoLastNamePerson lastName;

		public NoLastNamePerson getLastName() {
			return this.lastName;
		}

		public void setLastName(NoLastNamePerson lastName) {
			this.lastName = lastName;
		}

	}

}
