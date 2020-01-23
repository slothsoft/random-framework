
import java.text.DateFormat;
import java.util.Date;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.types.FirstNameRandomField;
import de.slothsoft.random.types.FirstNameRandomField.Gender;

/**
 * This example shows how to set this framework up for a single, very basic POJO without
 * any bells and whistles.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

public class BasicExample {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {
		final RandomFactory<Person> factory = RandomFactory.forClass(Person.class);

		System.out.println("Persons of Both Genders");
		System.out.println("-----------------------");
		for (final Person person : factory.create(5)) {
			System.out.println(person);
		}

		System.out.println("\n\nPersons of Male Genders");
		System.out.println("-----------------------");
		factory.addRandomField("firstName", new FirstNameRandomField().gender(Gender.MALE));
		for (final Person person : factory.create(5)) {
			System.out.println(person);
		}

		System.out.println("\n\nPersons of Female Genders");
		System.out.println("-------------------------");
		factory.addRandomField("firstName", new FirstNameRandomField().gender(Gender.FEMALE));
		for (final Person person : factory.create(5)) {
			System.out.println(person);
		}
	}

	public static class Person {

		private String firstName;
		private String lastName;
		private String street;
		private Date birthdate;
		private String postalCode;
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

		public Date getBirthdate() {
			return this.birthdate;
		}

		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}

		public String getPostalCode() {
			return this.postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCity() {
			return this.city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return this.firstName + " " + this.lastName + "   " + this.street + "   *" + FORMAT.format(this.birthdate)
					+ "   " + this.postalCode + " " + this.city + "\n\tage: " + this.age + "   integer: "
					+ this.integer;
		}
	}

}
