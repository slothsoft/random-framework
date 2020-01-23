package de.slothsoft.random;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomIndustrialAreaTest {

	public static class Person {

		private String firstName;
		private Address address;

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public Address getAddress() {
			return this.address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

	}

	public static class Address {

		private String street;

		public String getStreet() {
			return this.street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

	}

	private RandomIndustrialArea industrialArea;

	@Before
	public void setUp() {
		this.industrialArea = new RandomIndustrialArea();
	}

	@Test
	public void testForClassesParentChild() {
		this.industrialArea = RandomIndustrialArea.forClasses(Person.class, Address.class);

		final List<Person> persons = this.industrialArea.create(Person.class, 100);
		for (final Person person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNotNull(person.getAddress());
			Assert.assertNotNull(person.getAddress().getStreet());
		}
	}

	@Test
	public void testForClassesParent() {
		this.industrialArea = RandomIndustrialArea.forClasses(Person.class);

		final List<Person> persons = this.industrialArea.create(Person.class, 100);
		for (final Person person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNull(person.getAddress());
		}
	}

	@Test
	public void testForClassesChildOnly() {
		this.industrialArea = RandomIndustrialArea.forClasses(Person.class);

		try {
			this.industrialArea.create(Address.class, 100);
			Assert.fail("There should have been an exception!");
		} catch (final RandomException e) {
			// should bring an exception: Could not find RandomFactory for class
			Assert.assertNotNull(e);
		}
	}

	public static class PersonWithParent {

		private PersonWithParent parent;

		public PersonWithParent getParent() {
			return this.parent;
		}

		public void setParent(PersonWithParent parent) {
			this.parent = parent;
		}
	}

//	getCreationDepth()
//	creationDepth(int)
//	setCreationDepth(int)

	@Test
	public void testCreationDepthDefault() {
		this.industrialArea.addFactory(RandomFactory.forClass(PersonWithParent.class));

		// default creation depth is 5
		final List<PersonWithParent> persons = this.industrialArea.create(PersonWithParent.class, 100);
		for (final PersonWithParent person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getParent()); // 1
			Assert.assertNotNull(person.getParent().getParent()); // 2
			Assert.assertNotNull(person.getParent().getParent().getParent()); // 3
			Assert.assertNotNull(person.getParent().getParent().getParent().getParent()); // 4
			Assert.assertNotNull(person.getParent().getParent().getParent().getParent().getParent()); // 5
			Assert.assertNull(person.getParent().getParent().getParent().getParent().getParent().getParent()); // 6
																												// =>
																												// null!
		}
	}

	@Test
	public void testSetCreationDepth() {
		this.industrialArea.addFactory(RandomFactory.forClass(PersonWithParent.class));
		this.industrialArea.setCreationDepth(3);

		Assert.assertEquals(3, this.industrialArea.getCreationDepth());

		final List<PersonWithParent> persons = this.industrialArea.create(PersonWithParent.class, 100);
		for (final PersonWithParent person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getParent()); // 1
			Assert.assertNotNull(person.getParent().getParent()); // 2
			Assert.assertNotNull(person.getParent().getParent().getParent()); // 3
			Assert.assertNull(person.getParent().getParent().getParent().getParent()); // 4
																						// =>
																						// null!
		}
	}

	@Test
	public void testCreationDepth() {
		this.industrialArea.addFactory(RandomFactory.forClass(PersonWithParent.class));
		this.industrialArea.creationDepth(2);

		Assert.assertEquals(2, this.industrialArea.getCreationDepth());

		final List<PersonWithParent> persons = this.industrialArea.create(PersonWithParent.class, 100);
		for (final PersonWithParent person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getParent()); // 1
			Assert.assertNotNull(person.getParent().getParent()); // 2
			Assert.assertNull(person.getParent().getParent().getParent()); // 3 => null!
		}
	}

	@Test
	public void testFindRandomFieldNull() throws Exception {
		Assert.assertNull(this.industrialArea.findRandomFactory(Person.class));
	}

	@Test(expected = RandomException.class)
	public void testGetRandomFieldNull() throws Exception {
		Assert.assertNull(this.industrialArea.getRandomFactory(Person.class));
	}

	@Test
	public void testAddFactory() throws Exception {
		final RandomFactory<Person> factory = RandomFactory.forClass(Person.class);
		this.industrialArea.addFactory(factory);

		Assert.assertSame(factory, this.industrialArea.findRandomFactory(Person.class));
		Assert.assertSame(factory, this.industrialArea.getRandomFactory(Person.class));
	}

	@Test
	public void testAddFactoryOverride() throws Exception {
		final RandomFactory<Person> otherFactory = RandomFactory.forClass(Person.class);
		this.industrialArea.addFactory(otherFactory);

		final RandomFactory<Person> factory = RandomFactory.forClass(Person.class);
		this.industrialArea.addFactory(factory);

		Assert.assertSame(factory, this.industrialArea.findRandomFactory(Person.class));
		Assert.assertSame(factory, this.industrialArea.getRandomFactory(Person.class));
	}

	@Test
	public void testAddFactoryUsedByCreateSignle() throws Exception {
		this.industrialArea.addFactory(RandomFactory.forClass(Person.class));

		final Person person = this.industrialArea.createSingle(Person.class);
		Assert.assertNotNull(person);
		Assert.assertNotNull(person.getFirstName());
		Assert.assertNull(person.getAddress());
	}

	@Test
	public void testAddFactoryUsedByCreate() throws Exception {
		this.industrialArea.addFactory(RandomFactory.forClass(Person.class));

		for (final Person person : this.industrialArea.create(Person.class, 100)) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getFirstName());
			Assert.assertNull(person.getAddress());
		}
	}

	@Test(expected = NullPointerException.class)
	public void testAddFactoryNull() throws Exception {
		this.industrialArea.addFactory(null);
	}

	@Test
	public void testRemoveRandomField() throws Exception {
		final RandomFactory<Person> factory = RandomFactory.forClass(Person.class);
		this.industrialArea.addFactory(factory);
		this.industrialArea.removeFactory(factory);

		Assert.assertNull(this.industrialArea.findRandomFactory(Person.class));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveFactoryNull() throws Exception {
		this.industrialArea.removeFactory((RandomFactory<?>) null);
	}

	@Test
	public void testRemoveRandomFieldClass() throws Exception {
		this.industrialArea.addFactory(RandomFactory.forClass(Person.class));
		this.industrialArea.removeFactory(Person.class);

		Assert.assertNull(this.industrialArea.findRandomFactory(Person.class));
	}

	@Test
	public void testContainsRandomFactoryFor() throws Exception {
		Assert.assertFalse(this.industrialArea.containsRandomFactoryFor(Person.class));

		this.industrialArea.addFactory(RandomFactory.forClass(Person.class));

		Assert.assertTrue(this.industrialArea.containsRandomFactoryFor(Person.class));
	}
}
