package de.slothsoft.random;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class FirstNamesTest {

	@Test
	public void positiveTest() {
		for (Gender gender : Gender.values()) {
			RandomFactory<FirstNamePerson> factory = new DefaultRandomFactory<FirstNamePerson>(
					FirstNamePerson.class);

			Set<Option> options = new HashSet<Option>();
			options.add(new Option("firstName", Options.GENDER, gender));

			String[] allFirstNames = Database.getFirstNames(gender);
			Assert.assertTrue(!Util.contains(allFirstNames, null));
			for (FirstNamePerson person : factory.create(100, options)) {
				Assert.assertTrue(person.getFirstName() + " must be of gender "
						+ gender,
						Util.contains(allFirstNames, person.getFirstName()));
			}
		}
	}

	@Test
	public void negativeTest() {
		RandomFactory<NoFirstNamePerson> factory = new DefaultRandomFactory<NoFirstNamePerson>(
				NoFirstNamePerson.class);
		for (NoFirstNamePerson person : factory.create(100)) {
			Assert.assertTrue(person.getFirstName() == null);
		}
	}

	public static class FirstNamePerson {

		private String firstName;

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	}

	public static class NoFirstNamePerson {

		private NoFirstNamePerson firstName;

		public NoFirstNamePerson getFirstName() {
			return this.firstName;
		}

		public void setFirstName(NoFirstNamePerson firstName) {
			this.firstName = firstName;
		}

	}

}
