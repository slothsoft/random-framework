package de.slothsoft.random;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class StreetsTest {

	@Test
	public void positiveTest() {
		RandomFactory<StreetPerson> factory = new DefaultRandomFactory<StreetPerson>(
				StreetPerson.class);
		String[] allStreets = Database.getStreets();
		Assert.assertTrue(!Util.contains(allStreets, null));
		List<StreetPerson> persons = factory.create(100);
		for (StreetPerson person : persons) {
			int spaceIndex = person.getStreet().lastIndexOf(" ");
			String street = person.getStreet().substring(0, spaceIndex);
			Assert.assertTrue(Util.contains(allStreets, street));
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoStreetPerson> factory = new DefaultRandomFactory<NoStreetPerson>(
				NoStreetPerson.class);
		for (NoStreetPerson person : factory.create(100)) {
			Assert.assertTrue(person.getStreet() == null);
		}
	}

	public static class StreetPerson {

		private String street;

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String street) {
			this.street = street;
		}
	}

	public static class NoStreetPerson {

		private NoStreetPerson street;

		public NoStreetPerson getStreet() {
			return this.street;
		}

		public void setStreet(NoStreetPerson street) {
			this.street = street;
		}

	}

}
