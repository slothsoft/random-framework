package de.slothsoft.random.example;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.slothsoft.random.DefaultRandomFactory;
import de.slothsoft.random.Gender;
import de.slothsoft.random.Option;
import de.slothsoft.random.Options;
import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomFields;

public class ExtendedPersonTest {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {

		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("blob", RandomFields.FIRST_NAME);
		mapping.put("flup", RandomFields.LAST_NAME);
		mapping.put("date", RandomFields.DATE);

		RandomFactory<Person> factory = new DefaultRandomFactory<Person>(Person.class,
				mapping);

		System.out.println("Random Persons");
		System.out.println("--------------");
		Set<Option> options = new HashSet<Option>();
		options.add(new Option("firstName", Options.GENDER, Gender.MALE));
		for (Person person : factory.create(5)) {
			System.out.println(person);
		}
	}

	public static class Person {

		private String blob;
		private String flup;
		private String arg = "default";
		private Date date;

		public String getBlob() {
			return this.blob;
		}

		public void setBlob(String blob) {
			this.blob = blob;
		}

		public String getFlup() {
			return this.flup;
		}

		public void setFlup(String flup) {
			this.flup = flup;
		}

		public String getArg() {
			return this.arg;
		}

		public void setArg(String arg) {
			this.arg = arg;
		}

		public Date getDate() {
			return this.date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@Override
		public String toString() {
			return this.blob + " " + this.flup + "   "
					+ FORMAT.format(this.date);
		}
	}

}
