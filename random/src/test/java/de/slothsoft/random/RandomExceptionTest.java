package de.slothsoft.random;

import org.junit.Assert;
import org.junit.Test;

public class RandomExceptionTest {

	@Test
	public void testConstructor() throws Exception {
		try {
			throw new RandomException();
		} catch (final Exception e) {
			Assert.assertEquals(null, e.getMessage());
		}
	}

	@Test
	public void testConstructorWithMessage() throws Exception {
		try {
			throw new RandomException("lol");
		} catch (final Exception e) {
			Assert.assertEquals("lol", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithException() throws Exception {
		try {
			throw new RandomException(new Exception("wuz?"));
		} catch (final Exception e) {
			Assert.assertEquals(Exception.class.getName() + ": wuz?", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithMessageAndException() throws Exception {
		try {
			throw new RandomException("wiu wiu wui", new Exception("error!"));
		} catch (final Exception e) {
			Assert.assertEquals("wiu wiu wui", e.getMessage());
		}
	}
}
