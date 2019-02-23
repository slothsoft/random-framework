package de.slothsoft.random.example;

import java.text.DateFormat;

import de.slothsoft.random.RandomIndustrialArea;

public class HierarchicalPersonExample {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {
		final RandomIndustrialArea factory = RandomIndustrialArea.forClasses(Person.class, Address.class);

		System.out.println("Persons");
		System.out.println("-------");
		for (final Person person : factory.create(Person.class, 5)) {
			System.out.println(person);
		}
	}

	public static class Person {

		private String lastName;
		private Address address;

		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Address getAddress() {
			return this.address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return this.lastName + "\n\taddress: " + this.address;
		}
	}

	public static class Address {

		private String street;
		private String city;

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return this.city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return this.street + " in " + this.city;
		}

	}

}
