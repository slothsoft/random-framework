
import java.util.HashMap;
import java.util.Map;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.EnumRandomField;
import de.slothsoft.random.types.FirstNameRandomField;

/**
 * This example shows how to use the context to set up fields that are linked (like the
 * first name field with the gender).
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class ContextExample {

	private static final String CONTEXT_GENDER = "context.gender";

	public static void main(String[] args) {
		final Map<String, RandomField> fieldMapping = new HashMap<>();

		// we set up all three fields to get or create the person's gender, then use the
		// data to generate the values

		// Note: The fields' order is usually unclear. You can use a LinkedHashMap to
		// change that, but it might make your code volatile. It's probably better to
		// structure the code in a way that the order does not matter.

		fieldMapping.put("firstName", new FirstNameRandomField() {

			@Override
			public void init(Map<String, Object> context) {
				setGender(FirstNameRandomField.Gender.valueOf(getOrCreateGender(context).name()));
			}
		});
		fieldMapping.put("gender", new RandomField() {

			private Gender gender;

			@Override
			public void init(Map<String, Object> context) {
				this.gender = getOrCreateGender(context);
			}

			@Override
			public Object nextValue() {
				return this.gender;
			}
		});
		fieldMapping.put("female", new RandomField() {

			private Gender gender;

			@Override
			public void init(Map<String, Object> context) {
				this.gender = getOrCreateGender(context);
			}

			@Override
			public Object nextValue() {
				return Boolean.valueOf(this.gender == Gender.FEMALE);
			}
		});

		// now generate some persons!

		final RandomFactory<Person> factory = new RandomFactory<>(Person::new, fieldMapping);
		for (final Person person : factory.create(10)) {
			System.out.println(person);
		}
	}

	protected static Gender getOrCreateGender(Map<String, Object> context) {
		return (Gender) context.computeIfAbsent(CONTEXT_GENDER, key -> new EnumRandomField<>(Gender.class).nextValue());
	}

	public static enum Gender {
		FEMALE, MALE;
	}

	public static class Person {

		private String firstName;
		private Gender gender;
		private boolean female;

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

		public boolean isFemale() {
			return this.female;
		}

		public void setFemale(boolean female) {
			this.female = female;
		}

		@Override
		public String toString() {
			return this.firstName + " " + this.gender + " (female=" + this.female + ")";
		}
	}

}
