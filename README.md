# Random Framework

- **Author:** [Stef Schulz](mailto:s.schulz@slothsoft.de)
- **Repository:** <https://github.com/slothsoft/framework-random>
- **Open Issues:** <https://github.com/slothsoft/framework-random/issues>
- **Wiki:** none


A framework for creating dummy data for [POJO](https://de.wikipedia.org/wiki/Plain_Old_Java_Object)s. It can fill a lot of fields with random data, and is even able to recognize some fields that need special values to look pretty.

<details><summary><b>Features:</b></summary>
<p>
- `BigDecimal`
- `BigInteger`
- `Date` (and "birthdays", which have another range)
- `Double` and `double`
- `Float` and `float`
- `Integer` and `int`
- `Long` and `long`
- `Short` and `short`
- some special `Strings`
    * cities
    * first names
    * last names
    * streets (with house number)
</p>
</details>
     
## Getting Started

### Prerequisites

You need at least **Java 1.8** or above to use this library. You can use Maven as a build tool, but Gradle or using plain JARs should work as well.

### Installing

I probably should try to get this library into Maven Central. Then there'd be Maven GAVs like this:

```xml
<dependency>
	<groupId>de.slothsoft.random</groupId>
	<artifactId>random</artifactId>
	<version>2.0.0</version>
</dependency>
```

For other build tools and the JAR take a look at [Maven Central](https://mvnrepository.com/artifact/junit/junit).


### Using the Framework

The new and improved API is really easy to use. To create a simple POJO without much ado, do this

```java
class MyPojo { // add getter and setter!
	
		private String firstName;
		private Date birthdate;
}

RandomFactory<MyPojo> factory = RandomFactory.forClass(MyPojo.class);

// create a single POJO
System.out.println(factory.createSingle());


// create many POJOs
for (final Person person : factory.create(5)) {
	System.out.println(person);
}
```

If you want to have more control over the generated values, you cann add your own `RandomFields` like this:


```java
class MyPojo { // add getter and setter!
	
		private String firstName;
		private Date burzeltag;
}

RandomFactory<MyPojo> factory = RandomFactory.forClass(MyPojo.class);
factory.addRandomField("firstName", new FirstNameRandomField().gender(Gender.MALE));
factory.addRandomField("burzeltag", new BirthdayRandomField());


System.out.println(factory.createSingle());
```

Even more examples are located [here](random-example/src/main/java/de/slothsoft/random/example). To see all types have a look at [the package "types"](random/src/main/java/de/slothsoft/random/types).


## License

This project is licensed under the MIT License - see the [MIT license](https://opensource.org/licenses/MIT) for details.
