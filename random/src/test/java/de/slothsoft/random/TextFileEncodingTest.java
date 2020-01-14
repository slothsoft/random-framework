package de.slothsoft.random;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TextFileEncodingTest {

	@Parameters(name = "{1} ({0})")
	public static Collection<Object[]> data() throws Exception {
		final List<Object[]> result = new ArrayList<>();
		addFiles(result, new File("src/main/resources"));
		return result;
	}

	private static void addFiles(List<Object[]> result, File folder) throws Exception {
		for (final File child : folder.listFiles()) {
			if (child.isDirectory()) {
				addFiles(result, child);
			} else {
				result.add(new Object[]{child, child.getParentFile().getName() + '/' + child.getName()});
			}
		}
	}

	private final File textFile;
	private final String displayName;

	public TextFileEncodingTest(File textFile, String displayName) {
		this.textFile = textFile;
		this.displayName = displayName;
	}

	@Test
	public void testEncoding() throws Exception {
		final List<String> lines = Files.readAllLines(this.textFile.toPath());

		final List<String> brokenLines = new ArrayList<>();
		for (final String line : lines) {
			if (isBroken(line)) {
				brokenLines.add(line);
			}
		}

		if (!brokenLines.isEmpty()) {
			System.out.println(this.displayName);
			for (final String brokenLine : brokenLines) {
				System.out.println("\t" + brokenLine);
			}
		}

		Assert.assertEquals("Found " + brokenLines.size() + " broken lines!", Collections.emptyList(), brokenLines);
	}

	private static boolean isBroken(String input) {
		for (final char c : input.toCharArray()) {
			if (" '-//.`,&’=#:\\".contains(String.valueOf(c))) {
				continue;
			}
			if (Character.isAlphabetic(c)) {
				continue;
			}
			if (Character.isDigit(c)) {
				continue;
			}
			return true;
		}
		return false;
	}

	@Test
	public void testDuplicates() throws Exception {
		final List<String> lines = Files.readAllLines(this.textFile.toPath());

		if (lines.size() > 10_000) {
			// then counting duplicates is way to slow
			Assert.assertEquals("Some lines are present multiple times!", new TreeSet<>(lines).size(), lines.size());
		} else {
			for (final String line : new TreeSet<>(lines)) {
				Assert.assertEquals(line + " is present multiple times!", lines.lastIndexOf(line), lines.indexOf(line));
			}
		}
	}

	@Test
	public void testTrimmed() throws Exception {
		final List<String> lines = Files.readAllLines(this.textFile.toPath());

		for (final String line : new TreeSet<>(lines)) {
			Assert.assertEquals(line + " is not trimmed!", line.trim(), line);
		}
	}
}
