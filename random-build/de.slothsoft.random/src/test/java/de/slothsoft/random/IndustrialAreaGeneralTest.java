package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class IndustrialAreaGeneralTest {

	@Test
	public void positiveTest() {
		RandomIndustrialArea industrialArea = RandomIndustrialAreas.create(
				IAGPerson.class, IAGAddress.class);

		List<IAGPerson> persons = industrialArea.create(IAGPerson.class, 100);
		for (IAGPerson person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNotNull(person.getAddress());
			Assert.assertNotNull(person.getAddress().getStreet());
		}
	}

	@Test
	public void testGetSetter() {
		try {
			Method setter = IAGPerson.class.getMethod("setAddress",
					IAGAddress.class);
			System.out.println(setter);
		} catch (NoSuchMethodException e) {
			// wrong attribute class, just go on
			e.printStackTrace();
		}
	}

	@Test
	public void negativeTest() {
		RandomIndustrialArea industrialArea = RandomIndustrialAreas
				.create(IAGPerson.class);

		List<IAGPerson> persons = industrialArea.create(IAGPerson.class, 100);
		for (IAGPerson person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNull(person.getAddress());
		}
	}

	@Test
	public void negativeExceptionTest() {
		RandomIndustrialArea industrialArea = RandomIndustrialAreas
				.create(IAGPerson.class);

		try {
			industrialArea.create(IAGAddress.class, 100);
			Assert.fail();
		} catch (RandomException e) {
			// should bring an exception: Could not find RandomFactory for class
		}
	}

	public static class IAGPerson {

		private String firstName;
		private IAGAddress address;

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public IAGAddress getAddress() {
			return this.address;
		}

		public void setAddress(IAGAddress address) {
			this.address = address;
		}

	}

	public static class IAGAddress {

		private String street;

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

	}

}