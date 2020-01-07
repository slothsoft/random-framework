package de.slothsoft.random.it;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.random.RandomFieldSupplier;

public class RandomFieldSupplierTest {

	@Test
	public void testGetAllSuppliers() throws Exception {
		final List<RandomFieldSupplier> suppliers = RandomFieldSupplier.getAllSuppliers();

		Assert.assertNotNull(suppliers);
		Assert.assertTrue("Not enough suppliers:" + suppliers, suppliers.size() >= 14);
	}
}
