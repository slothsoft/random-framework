package de.slothsoft.random;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import de.slothsoft.random.types.BigDecimalRandomField;
import de.slothsoft.random.types.BigIntegerRandomField;
import de.slothsoft.random.types.BirthdayRandomField;
import de.slothsoft.random.types.BooleanRandomField;
import de.slothsoft.random.types.CalendarRandomField;
import de.slothsoft.random.types.CityRandomField;
import de.slothsoft.random.types.DateRandomField;
import de.slothsoft.random.types.DoubleRandomField;
import de.slothsoft.random.types.FirstNameRandomField;
import de.slothsoft.random.types.FloatRandomField;
import de.slothsoft.random.types.IntegerRandomField;
import de.slothsoft.random.types.LastNameRandomField;
import de.slothsoft.random.types.LocalDateRandomField;
import de.slothsoft.random.types.LocalDateTimeRandomField;
import de.slothsoft.random.types.LocalTimeRandomField;
import de.slothsoft.random.types.LongRandomField;
import de.slothsoft.random.types.ShortRandomField;
import de.slothsoft.random.types.StreetRandomField;
import de.slothsoft.random.types.WordRandomField;

/**
 * Util class for storing managing all the random field this module brings along.
 *
 * @author Stef Schulz
 * @since 2.0.0
 */

public abstract class RandomFieldSupplier {

	private static List<RandomFieldSupplier> suppliers;

	/**
	 * Returns a {@link RandomFieldSupplier} for a property name and class.
	 *
	 * @param propertyName the property's name
	 * @param propertyClass the property's class
	 * @return a {@link RandomFieldSupplier} or null
	 */

	public static RandomFieldSupplier findSupplierByField(String propertyName, Class<?> propertyClass) {
		final String name = propertyName.toLowerCase();
		for (final RandomFieldSupplier supplier : getAllSuppliers()) {
			if (supplier.canSupply(name, propertyClass)) return supplier;
		}
		return null;
	}

	/**
	 * Returns all {@link RandomFieldSupplier}s that this module brings along.
	 *
	 * @return a list of {@link RandomFieldSupplier}; never null; probably never empty
	 */

	public static List<RandomFieldSupplier> getAllSuppliers() {
		if (suppliers == null) {
			suppliers = new ArrayList<>();

			suppliers.add(forSynonymeList("synonyms/street-names.txt", () -> new StreetRandomField()));

			suppliers.add(forSynonymeList("synonyms/last-names.txt", LastNameRandomField::new));
			suppliers.add(forSynonymeList("synonyms/city-names.txt", CityRandomField::new));
			suppliers.add(forSynonymeList("synonyms/first-names.txt", FirstNameRandomField::new));
			suppliers.add(forSynonymeList("synonyms/birthdays.txt", BirthdayRandomField::new));
			suppliers.add(forSynonymeList("synonyms/word.txt", WordRandomField::new));

			suppliers.add(forFieldClass(Date.class, DateRandomField::new));
			suppliers.add(forFieldClass(Calendar.class, CalendarRandomField::new));
			suppliers.add(forFieldClass(LocalDateTime.class, LocalDateTimeRandomField::new));
			suppliers.add(forFieldClass(LocalTime.class, LocalTimeRandomField::new));
			suppliers.add(forFieldClass(LocalDate.class, LocalDateRandomField::new));

			suppliers.add(forFieldClass(Short.class, ShortRandomField::new));
			suppliers.add(forFieldClass(short.class, () -> new ShortRandomField()));
			suppliers.add(forFieldClass(Integer.class, IntegerRandomField::new));
			suppliers.add(forFieldClass(int.class, () -> new IntegerRandomField()));
			suppliers.add(forFieldClass(Long.class, LongRandomField::new));
			suppliers.add(forFieldClass(long.class, () -> new LongRandomField()));
			suppliers.add(forFieldClass(Double.class, DoubleRandomField::new));
			suppliers.add(forFieldClass(double.class, () -> new DoubleRandomField()));
			suppliers.add(forFieldClass(Float.class, FloatRandomField::new));
			suppliers.add(forFieldClass(float.class, () -> new FloatRandomField()));
			suppliers.add(forFieldClass(BigInteger.class, BigIntegerRandomField::new));
			suppliers.add(forFieldClass(BigDecimal.class, BigDecimalRandomField::new));
			suppliers.add(forFieldClass(Boolean.class, BooleanRandomField::new));
			suppliers.add(forFieldClass(boolean.class, () -> new BooleanRandomField()));
		}
		return suppliers;
	}

	// these constructors are not that great, but are a problem for future Stef now

	static RandomFieldSupplier forFieldClass(Class<?> wantedFieldClass, Supplier<RandomField> supplier) {
		return new RandomFieldSupplier(supplier) {

			@Override
			public boolean canSupply(String fieldName, Class<?> fieldClass) {
				return wantedFieldClass == fieldClass;
			}
		};
	}

	static RandomFieldSupplier forSynonymeList(String fileName, Supplier<RandomField> supplier) {
		final List<String> synonyms = Arrays.asList(readFile(RandomFieldSupplier.class.getResourceAsStream(fileName)));
		return new RandomFieldSupplier(supplier) {

			@Override
			public boolean canSupply(String fieldName, Class<?> fieldClass) {
				return synonyms.contains(fieldName.toLowerCase());
			}
		};
	}

	static String[] readFile(InputStream inputStream) {
		return new BufferedReader(new InputStreamReader(inputStream)).lines().parallel().toArray(String[]::new);
	}

	private final Supplier<RandomField> supplier;

	protected RandomFieldSupplier(Supplier<RandomField> supplier) {
		this.supplier = supplier;
	}

	/**
	 * Returns if this random field should be used to render the field name.
	 *
	 * @param fieldName name of the field
	 * @param fieldClass class of the field
	 * @return a boolean - returns true if a {@link RandomField} can be supplied
	 */

	public abstract boolean canSupply(String fieldName, Class<?> fieldClass);

	/**
	 * Creates a <b>new instance</b> of {@link RandomField} to use for a
	 * {@link RandomFactory}.
	 *
	 * @return a new instance
	 */

	public RandomField createRandomField() {
		return this.supplier.get();
	}

}
