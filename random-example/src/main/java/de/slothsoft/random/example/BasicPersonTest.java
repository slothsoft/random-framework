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

public class BasicPersonTest {

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
		private String city;
		private int age;
		private Integer integer;

		public int getAge() {
			return this.age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Integer getInteger() {
			return this.integer;
		}

		public void setInteger(Integer integer) {
			this.integer = integer;
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

		public String getCity() {
			return this.city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return this.firstName + " " + this.lastName + "   " + this.street
					+ "   *" + FORMAT.format(this.birthdate) + "   "
					+ this.city + "\n\tage: " + this.age + "   integer: "
					+ this.integer;
		}
	}

}
