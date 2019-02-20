package de.slothsoft.random;

import junit.framework.Assert;

import org.junit.Test;

public class CitiesTest {

	@Test
	public void positiveTest() {
		RandomFactory<CityPerson> factory = new DefaultRandomFactory<CityPerson>(
				CityPerson.class);
		String[] allCities = Database.getCities();
		Assert.assertTrue(!Util.contains(allCities, null));
		for (CityPerson person : factory.create(100)) {
			Assert.assertTrue(Util.contains(allCities, person.getCity()));
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoCityPerson> factory = new DefaultRandomFactory<NoCityPerson>(
				NoCityPerson.class);
		for (NoCityPerson person : factory.create(100)) {
			Assert.assertTrue(person.getCity() == null);
		}
	}

	public static class CityPerson {

		private String city;

		public String getCity() {
			return this.city;
		}

		public void setCity(String city) {
			this.city = city;
		}
	}

	public static class NoCityPerson {

		private NoCityPerson city;

		public NoCityPerson getCity() {
			return this.city;
		}

		public void setCity(NoCityPerson city) {
			this.city = city;
		}

	}

}
