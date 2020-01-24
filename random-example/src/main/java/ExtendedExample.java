
import java.text.DateFormat;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.types.FirstNameRandomField;

/**
 * An example showing some more fields.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class ExtendedExample {

	protected static DateFormat FORMAT = DateFormat.getDateInstance();

	public static void main(String[] args) {
		final RandomFactory<Person> factory = RandomFactory.forClass(Person.class);
		factory.addRandomField("middleName", new FirstNameRandomField().nullProbability(0.7));

		for (final Person person : factory.create(5)) {
			System.out.println(person);
		}
	}

	public static enum Gender {
		FEMALE, MALE, NONE;
	}

	public static class Person {

		private String firstName;
		private String middleName;
		private Gender gender;
		private String description;

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public Gender getGender() {
			return this.gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public String getMiddleName() {
			return this.middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		@Override
		public String toString() {
			final String name = this.firstName + (this.middleName == null ? "" : (" " + this.middleName)) + " ("
					+ this.gender + "):";
			return name + '\n' + name.replaceAll(".", "=") + '\n' + this.description + '\n';
		}
	}

}
