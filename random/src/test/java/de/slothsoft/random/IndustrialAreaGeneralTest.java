package de.slothsoft.random;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class IndustrialAreaGeneralTest {

	@Test
	public void positiveTest() {
		final RandomIndustrialArea industrialArea = RandomIndustrialArea.create(IAGPerson.class, IAGAddress.class);

		final List<IAGPerson> persons = industrialArea.create(IAGPerson.class, 100);
		for (final IAGPerson person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNotNull(person.getAddress());
			Assert.assertNotNull(person.getAddress().getStreet());
		}
	}

	@Test
	public void testGetSetter() {
		try {
			final Method setter = IAGPerson.class.getMethod("setAddress", IAGAddress.class);
			System.out.println(setter);
		} catch (final NoSuchMethodException e) {
			// wrong field class, just go on
			e.printStackTrace();
		}
	}

	@Test
	public void negativeTest() {
		final RandomIndustrialArea industrialArea = RandomIndustrialArea.create(IAGPerson.class);

		final List<IAGPerson> persons = industrialArea.create(IAGPerson.class, 100);
		for (final IAGPerson person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNull(person.getAddress());
		}
	}

	@Test
	public void negativeExceptionTest() {
		final RandomIndustrialArea industrialArea = RandomIndustrialArea.create(IAGPerson.class);

		try {
			industrialArea.create(IAGAddress.class, 100);
			Assert.fail();
		} catch (final RandomException e) {
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