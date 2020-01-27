# Random Framework

[![Build Status](https://travis-ci.org/slothsoft/framework-random.svg?branch=master)](https://travis-ci.org/slothsoft/framework-random) [![Maven Central](https://img.shields.io/maven-central/v/de.slothsoft.random/random.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22de.slothsoft.random%22%20AND%20a:%22random%22)

- **Author:** [Stef Schulz](mailto:s.schulz@slothsoft.de)
- **Repository:** <https://github.com/slothsoft/framework-random>
- **Open Issues:** <https://github.com/slothsoft/framework-random/issues>
- **Wiki:** none
- **Developer Resources:** [JavaDoc](http://slothsoft.github.io/framework-random), [Executed Tests](http://slothsoft.github.io/framework-random/tests), [Code Coverage](http://slothsoft.github.io/framework-random/coverage)


A framework for creating dummy data for [POJO](https://de.wikipedia.org/wiki/Plain_Old_Java_Object)s. It can fill a lot of fields with random data, and is even able to recognize some fields that need special values to look pretty.

**Content:**

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installing](#installing)
    - [Using the Framework](#using-the-framework)
- [Versions](#versions)
- [Features](#features)
- [Developer Manual](#developer-manual)
- [License](#license)


## Getting Started

### Prerequisites

You need at least **Java 1.8** or above to use this library. You can use Maven as a build tool, but Gradle or using plain JARs should work as well.

### Installing

This library is in Maven Central, so you can easily add it like this:

```xml
<dependency>
    <groupId>de.slothsoft.random</groupId>
    <artifactId>random</artifactId>
    <version>2.0.2</version>
</dependency>
```

For other build tools and the JAR take a look at [Maven Central](https://search.maven.org/artifact/de.slothsoft.random/random/2.0.0/jar) or the [MVN Repository](https://mvnrepository.com/artifact/de.slothsoft.random/random).


### Using the Framework

The new and improved API is really easy to use. To create a simple POJO without much ado, do this:

```java
class MyPerson { 
    private String firstName;
    private Date birthdate;
    
    // add getters and setters!
}

RandomFactory<MyPerson> factory = RandomFactory.forClass(MyPerson.class);

// create a single POJO
System.out.println(factory.createSingle());


// create many POJOs
for (final MyPerson person : factory.create(5)) {
    System.out.println(person);
}
```

If you want to have more control over the generated values, you cann add your own `RandomFields` like this:


```java
class MyPerson { 
    private String firstName;
    private Date burzeltag;
    
    // add getters and setters!
}

RandomFactory<MyPojo> factory = RandomFactory.forClass(MyPerson.class);
factory.addRandomField("firstName", new FirstNameRandomField().gender(Gender.MALE));
factory.addRandomField("burzeltag", new BirthdayRandomField());


System.out.println(factory.createSingle());
```

And finally, to create a hierarchical structure of POJOs, use the `RandomIndustrialArea` like this:

```java
class MyPerson { 
    private MyPersonsAddress address;
    // add getter and setter!
}
class MyPersonsAddress { 
    private String city;
    // add getter and setter!
}

RandomIndustrialArea factory = RandomIndustrialArea.forClasses(MyPerson.class, MyPersonsAddress.class);

for (final MyPerson person : factory.create(MyPerson.class, 5)) {
	System.out.println(person);
}
```

Even more examples are located [here](https://github.com/slothsoft/framework-random/tree/master/random-example/src/main/java/). To see all types have a look at [the package "types"](https://github.com/slothsoft/framework-random/tree/master/random/src/main/java/de/slothsoft/random/types) or the [JavaDoc](http://slothsoft.github.io/framework-random/de/slothsoft/random/types/package-summary.html).


##  Versions


| Version       | Changes       |
| ------------- | ------------- |
| [2.1.0](https://github.com/slothsoft/framework-random/milestone/2?closed=1) | new fields |
| [2.0.2](https://github.com/slothsoft/framework-random/milestone/3?closed=1) | fixed file access |
| 2.0.1         | Made _pom.xml_ pretty |
| [2.0.0](https://github.com/slothsoft/framework-random/milestone/1?closed=1) | streamlined the API and documentation; moved to Maven Central |
| 1.0.0         | internal version with basic API |


##  Features

The following classes and semantic fields are supported.

- arrays
- `BigDecimal`
- `BigInteger`
- `Boolean`
- `Calendar`
- `Character` and `char`
- `Collection` (including `List`, `Set` and `Queue`
- `Date` (and "birthdays", which have another range)
- `Double` and `double`
- `Enum`
- `Float` and `float`
- `Integer` and `int`
- `LocalDateTime`
- `LocalDate`
- `LocalTime`
- `Long` and `long`
- `Short` and `short`
- some special `Strings`
    * cities
    * first names
    * last names
    * patterns (i.e. combination of all other fields)
    * postal codes
    * streets (with house number)
    * word (random letters)
    * words (random letters with spaces, dots and paragraphs between them)
    
If something is missing, request it via [a new issue](https://github.com/slothsoft/framework-random/issues/new).



# Developer Manual

### How to Release?

The release process is basically [as defined in this guide](https://github.com/slothsoft/charts/wiki/How-To-Release):

- **Pre-Release Steps**
    1. Check TODOs and FIXMEs: Create issues for the next milestone(s) or fix them 
    1. Update documentation via `mvn clean verify -Pdoc` or the Eclipse launch config "Create Doc.launch" 
    1. Commit documentation 
    1. Update README.md with the planned version:
        - **Getting Started** > **Installing** - change Maven version
        - **Versions** - add new milestone / version here
- **Release**
    1. `mvn clean release:prepare -DreleaseVersion=2.1.0 -DdevelopmentVersion=2.2.0-SNAPSHOT -DscmCommentPrefix="[#26] " -B`
    1. `mvn release:perform`
    1. `git push origin -–tags`
    1. `git push origin master`   
- **Post-Release Steps**
    1. Check that the project got released to [Sonatype](https://oss.sonatype.org/)


### How to Create a New Random Field?

Create new implementation of `RandomField` in `de.slothsoft.random.types`. To test this implementation, you can (should) use:

- `RandomFactoryTest` - to test that the basic setup of setting a property of a POJO works
- `AbstractRandomFieldTest` - to test that the interface is correctly implemented

Also it should be added to the [Features](#features) section of this README.



## License

This project is licensed under the MIT License - see the [MIT license](LICENSE) for details.
