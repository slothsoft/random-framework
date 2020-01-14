package de.slothsoft.random.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.FirstNameRandomField.Gender;

public class FirstNameRandomFieldTest extends AbstractRandomFieldTest {

	public static class Pojo {

		private String firstName;

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String value) {
			this.firstName = value;
		}
	}

	private FirstNameRandomField firstNameField;

	public FirstNameRandomFieldTest() {
		super(new Pojo());
		this.property = "firstName";
	}

	@Override
	protected RandomField createRandomField() {
		this.firstNameField = new FirstNameRandomField();
		return this.firstNameField;
	}

	@Test
	public void testGender() throws Exception {
		this.firstNameField.setGender(Gender.FEMALE);
		Assert.assertEquals(Gender.FEMALE, this.firstNameField.getGender());

		this.firstNameField.gender(Gender.MALE);
		Assert.assertEquals(Gender.MALE, this.firstNameField.getGender());
	}

	@Test
	public void testGenderFemale() throws Exception {
		this.firstNameField.setGender(Gender.FEMALE);

		final List<String> names = new ArrayList<>(Arrays.asList(FirstNameRandomField.femaleNames));
		names.addAll(Arrays.asList(FirstNameRandomField.unisexNames));

		for (int i = 0; i < 100; i++) {
			final String name = this.firstNameField.nextValue();
			Assert.assertTrue(name + " is not a female name!", names.contains(name));
		}
	}

	@Test
	public void testGenderMale() throws Exception {
		this.firstNameField.setGender(Gender.MALE);

		final List<String> names = new ArrayList<>(Arrays.asList(FirstNameRandomField.maleNames));
		names.addAll(Arrays.asList(FirstNameRandomField.unisexNames));

		for (int i = 0; i < 100; i++) {
			final String name = this.firstNameField.nextValue();
			Assert.assertTrue(name + " is not a male name!", names.contains(name));
		}
	}

	@Test
	public void testGenderNeither() throws Exception {
		this.firstNameField.setGender(Gender.NEITHER);

		final List<String> names = Arrays.asList(FirstNameRandomField.unisexNames);
		for (int i = 0; i < 100; i++) {
			final String name = this.firstNameField.nextValue();
			Assert.assertTrue(name + " is not a unisex name!", names.contains(name));
		}
	}

	@Test
	public void testGenderBoth() throws Exception {
		this.firstNameField.setGender(Gender.BOTH);

		final List<String> names = new ArrayList<>(Arrays.asList(FirstNameRandomField.maleNames));
		names.addAll(Arrays.asList(FirstNameRandomField.femaleNames));
		names.addAll(Arrays.asList(FirstNameRandomField.unisexNames));

		for (int i = 0; i < 100; i++) {
			final String name = this.firstNameField.nextValue();
			Assert.assertTrue(name + " is not a name!", names.contains(name));
		}
	}

}
