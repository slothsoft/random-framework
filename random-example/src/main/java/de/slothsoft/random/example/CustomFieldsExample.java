package de.slothsoft.random.example;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.DateRandomField;
import de.slothsoft.random.types.FirstNameRandomField;
import de.slothsoft.random.types.FirstNameRandomField.Gender;

public class CustomFieldsExample {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {

		final Map<String, RandomField<?>> mapping = new HashMap<>();
		mapping.put("blob", new FirstNameRandomField().gender(Gender.MALE));
//		mapping.put("flup", RandomFields.LAST_NAME);
		mapping.put("date", new DateRandomField());

		final RandomFactory<Person> factory = new RandomFactory<>(Person::new, mapping);

		System.out.println("Random Persons");
		System.out.println("--------------");
		for (final Person person : factory.create(5)) {
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
			return this.blob + " " + this.flup + "   " + FORMAT.format(this.date);
		}
	}

}
