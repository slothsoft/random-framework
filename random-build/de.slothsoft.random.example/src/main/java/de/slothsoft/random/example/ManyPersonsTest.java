package de.slothsoft.random.example;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import de.slothsoft.random.DefaultRandomFactory;
import de.slothsoft.random.Gender;
import de.slothsoft.random.Option;
import de.slothsoft.random.Options;
import de.slothsoft.random.RandomFactory;

public class ManyPersonsTest {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		RandomFactory<Person> factory = new DefaultRandomFactory<Person>(Person.class);
		time = System.currentTimeMillis() - time;

		System.out.println(time + "ms to initialize!\n");

		System.out.println("Persons of Both Genders");
		System.out.println("-----------------------");
		for (Person person : factory.create(5)) {
			System.out.println(person);
		}

		System.out.println("\n\nPersons of Male Genders");
		System.out.println("-----------------------");
		Set<Option> options = new HashSet<Option>();
		options.add(new Option("firstName", Options.GENDER, Gender.MALE));
		for (Person person : factory.create(5, options)) {
			System.out.println(person);
		}

		System.out.println("\n\nPersons of Female Genders");
		System.out.println("-------------------------");
		options = new HashSet<Option>();
		options.add(new Option("firstName", Options.GENDER, Gender.FEMALE));
		for (Person person : factory.create(5, options)) {
			System.out.println(person);
		}
	}

	public static enum PersonGender {
		MALE, FEMALE;
	}

	public static class Person {

		private String firstName;
		private String lastName;
		private String street;
		private Date birthdate;
		private PersonGender gender = null;
		private int date;

		public int getDate() {
			return this.date;
		}

		public void setDate(int date) {
			this.date = date;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public PersonGender getGender() {
			return this.gender;
		}

		public void setGender(PersonGender gender) {
			this.gender = gender;
		}

		public Date getBirthdate() {
			return this.birthdate;
		}

		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}

		@Override
		public String toString() {
			return this.firstName + " " + this.lastName + "   " + this.street
					+ "   *" + FORMAT.format(this.birthdate);
		}
	}

}
