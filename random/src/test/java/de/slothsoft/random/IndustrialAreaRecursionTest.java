package de.slothsoft.random;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;

import de.slothsoft.random.DefaultRandomIndustrialArea;
import de.slothsoft.random.RandomIndustrialAreas;

public class IndustrialAreaRecursionTest {

	@Test
	public void positiveTest() {
		DefaultRandomIndustrialArea industrialArea = RandomIndustrialAreas
				.create(IARPerson.class);

		List<IARPerson> persons = industrialArea.create(IARPerson.class, 100);
		for (IARPerson person : persons) {
			Assert.assertNotNull(person);
			Assert.assertNotNull(person.getParent());
			Assert.assertNotNull(person.getParent().getParent());
			Assert.assertNotNull(person.getParent().getParent().getParent());
			Assert.assertNotNull(person.getParent().getParent().getParent()
					.getParent());
			Assert.assertNotNull(person.getParent().getParent().getParent()
					.getParent().getParent());
			Assert.assertNull(person.getParent().getParent().getParent()
					.getParent().getParent().getParent());
		}
	}

	public static class IARPerson {

		private IARPerson parent;

		public IARPerson getParent() {
			return this.parent;
		}

		public void setParent(IARPerson parent) {
			this.parent = parent;
		}

	}

}