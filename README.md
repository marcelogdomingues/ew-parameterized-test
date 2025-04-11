# Rabobank Engineers Week - Parameterized Testing

![Alt text](https://cdn.worldvectorlogo.com/logos/rabobank-4.svg "Rabobank Logo")

## Overview
JUnit 5, the latest iteration of the popular testing framework, introduces a host of new features designed to enhance the developer testing experience. Among these features is the powerful concept of parameterized tests. Parameterized tests allow developers to run a single test method multiple times with different sets of parameters, making it easier to test various scenarios and edge cases efficiently.

In this comprehensive tutorial, we will delve into the intricacies of parameterized tests in JUnit 5. We'll cover everything from basic usage to advanced techniques, ensuring you have a thorough understanding of how to leverage this feature to improve your testing strategy.

Let's get started!

## Dependencies

### Utilizing JUnit 5 Parameterized Tests

To utilize JUnit 5 parameterized tests, you need to include the `junit-jupiter-params` artifact from the JUnit Platform in your project dependencies. Here's how you can do it for both Maven and Gradle:

#### Maven

Add the following dependency to your `pom.xml` file:

```xml  
<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params -->  
<dependency>  
 <groupId>org.junit.jupiter</groupId>
 <artifactId>junit-jupiter-params</artifactId>
 <version>5.12.1</version>
 <scope>test</scope>
</dependency>  
```  

#### Gradle

Include the dependency in your `build.gradle` file:

```groovy  
dependencies {  
 testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.1")}  
```  

By adding these dependencies, you enable the use of parameterized tests in JUnit 5, allowing you to run a single test method multiple times with different parameters. This is particularly useful for testing various input scenarios and ensuring your code handles all edge cases effectively.

**You can always use `spring-boot-starter-test`. At the time that this was written, we are using  3.4.4.**


```xml  
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>3.4.4</version>
    <scope>test</scope>
</dependency>
```  

## First Impression

Let's say we have an existing utility function, and we want to ensure its behavior is correct:

```java  
/**
 * The Numbers class provides utility methods for number operations.
 */
public class Numbers {  

    /**
     * Checks if a given number is odd.
     *
     * @param number the number to check
     * @return true if the number is odd, false otherwise
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0; 
    }
}
```  
The "normal way" of testing this class with JUnit would be using the annotation `@Test` and use the

```java  
  @Test
void isOdd_ShouldReturnTrueForOddNumbers() {
    assertTrue(Numbers.isOdd(1));
    assertTrue(Numbers.isOdd(3));
    assertTrue(Numbers.isOdd(5));
    assertTrue(Numbers.isOdd(-3));
    assertTrue(Numbers.isOdd(15));
    assertTrue(Numbers.isOdd(Integer.MAX_VALUE));
}
```  

Parameterized tests are similar to other tests, but we add the `@ParameterizedTest` annotation:

```java  
@ParameterizedTest  
@ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers  
void isOdd_ShouldReturnTrueForOddNumbers(int number) {  
 assertTrue(Numbers.isOdd(number));  
}  
```  

The JUnit 5 test runner executes the above test — and consequently, the `isOdd` method — six times. Each time, it assigns a different value from the `@ValueSource` array to the `number` method parameter.

This example demonstrates two essential components for a parameterized test:

1. **A source of arguments**: In this case, an `int` array.
2. **A way to access them**: In this case, the `number` parameter.

There is still another aspect not evident with this example, so we'll continue exploring.
  
---  

## 1. Argument Sources

As we should know by now, a parameterized test executes the same test multiple times with different arguments.

And we can hopefully do more than just numbers, so let’s explore.

### 1.1. Simple Values

With the `@ValueSource` annotation, we can pass an array of literal values to the test method.

Suppose we’re going to test our simple `isBlank` method:

```java  
public class Strings {  
 public static boolean isBlank(String input) { return input == null || input.trim().isEmpty(); }}  
```  

We expect this method to return `true` for `null` or blank strings. So, we can write a parameterized test to assert this behavior:

```java  
@ParameterizedTest  
@ValueSource(strings = {"", "  "})  
void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

As we can see, JUnit will run this test two times and each time assigns one argument from the array to the method parameter.

One of the limitations of value sources is that they only support these types:

- `short` (with the `shorts` attribute)
- `byte` (`bytes` attribute)
- `int` (`ints` attribute)
- `long` (`longs` attribute)
- `float` (`floats` attribute)
- `double` (`doubles` attribute)
- `char` (`chars` attribute)
- `java.lang.String` (`strings` attribute)
- `java.lang.Class` (`classes` attribute)

Also, we can only pass one argument to the test method each time.

Before going any further, note that we didn’t pass `null` as an argument. That’s another limitation — we can’t pass `null` through a `@ValueSource`, even for `String` and `Class`.

### 1.2. Null and Empty Values

As of JUnit 5.4, we can pass a single `null` value to a parameterized test method using `@NullSource`:

```java  
@ParameterizedTest  
@NullSource  
void isBlank_ShouldReturnTrueForNullInputs(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

Since primitive data types can’t accept `null` values, we can’t use the `@NullSource` for primitive arguments.

Quite similarly, we can pass empty values using the `@EmptySource` annotation:

```java  
@ParameterizedTest  
@EmptySource  
void isBlank_ShouldReturnTrueForEmptyStrings(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

`@EmptySource` passes a single empty argument to the annotated method.

For `String` arguments, the passed value would be as simple as an empty `String`. Moreover, this parameter source can provide empty values for `Collection` types and arrays.

To pass both `null` and empty values, we can use the composed `@NullAndEmptySource` annotation:

```java  
@ParameterizedTest  
@NullAndEmptySource  
void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

As with the `@EmptySource`, the composed annotation works for `Strings`, `Collections`, and arrays.

To pass a few more empty string variations to the parameterized test, we can combine `@ValueSource`, `@NullSource`, and `@EmptySource`:

```java  
@ParameterizedTest  
@NullAndEmptySource  
@ValueSource(strings = {"  ", "\t", "\n"})  
void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

### 1.3. Enum

To run a test with different values from an enumeration, we can use the `@EnumSource` annotation.

For example, we can assert that all month numbers are between 1 and 12:

```java  
@ParameterizedTest  
@EnumSource(Month.class) // passing all 12 months  
void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {  
 int monthNumber = month.getValue();  
 assertTrue(monthNumber >= 1 && monthNumber <= 12);}  
```  

Or, we can filter out a few months by using the `names` attribute.

We could also assert the fact that April, September, June, and November are 30 days long:

```java  
@ParameterizedTest  
@EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})  
void someMonths_Are30DaysLong(Month month) {  
 final boolean isALeapYear = false; assertEquals(30, month.length(isALeapYear));  
}  
```  

By default, the `names` will only keep the matched enum values.

We can turn this around by setting the `mode` attribute to `EXCLUDE`:

```java  
@ParameterizedTest  
@EnumSource(  
 value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"}, mode = EnumSource.Mode.EXCLUDE)void exceptFourMonths_OthersAre31DaysLong(Month month) {  
 final boolean isALeapYear = false; assertEquals(31, month.length(isALeapYear));  
}  
```  

In addition to literal strings, we can pass a regular expression to the `names` attribute:

```java  
@ParameterizedTest  
@EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)  
void fourMonths_AreEndingWithBer(Month month) {  
 EnumSet<Month> months = EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER); assertTrue(months.contains(month));  
}  
```  

Quite similar to `@ValueSource`, `@EnumSource` is only applicable when we’re going to pass just one argument per test execution.

### 1.4. CSV Literals

Suppose we’re going to make sure that the `toUpperCase()` method from `String` generates the expected uppercase value. `@ValueSource` won’t be enough.

To write a parameterized test for such scenarios, we have to:

1. Pass an input value and an expected value to the test method
2. Compute the actual result with those input values
3. Assert the actual value with the expected value

So, we need argument sources capable of passing multiple arguments.

The `@CsvSource` is one of those sources:

```java  
@ParameterizedTest  
@CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})  
void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {  
  String actualValue = input.toUpperCase();  
 assertEquals(expected, actualValue);  
}  
```  

The `@CsvSource` accepts an array of comma-separated values, and each array entry corresponds to a line in a CSV file.

This source takes one array entry each time, splits it by a comma, and passes each array to the annotated test method as separate parameters.

By default, the comma is the column separator, but we can customize it using the `delimiter` attribute:

```java  
@ParameterizedTest  
@CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')  
void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {  
  String actualValue = input.toLowerCase();  
 assertEquals(expected, actualValue);  
}  
```  

Now it’s a colon-separated value, so still a CSV.

### 1.5. CSV Files

Instead of passing the CSV values inside the code, we can refer to an actual CSV file.

For example, we could use a CSV file like this:

```  
input,expected  
test,TEST  
tEst,TEST  
Java,JAVA  
```  

We can load the CSV file and ignore the header column with `@CsvFileSource`:

```java  
@ParameterizedTest  
@CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)  
void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(  
  String input, String expected) {  
  String actualValue = input.toUpperCase();  
 assertEquals(expected, actualValue);  
}  
```  

The `resources` attribute represents the CSV file resources on the classpath to read. And, we can pass multiple files to it.

The `numLinesToSkip` attribute represents the number of lines to skip when reading the CSV files. By default, `@CsvFileSource` does not skip any lines, but this feature is usually useful for skipping the header lines as we did here.

Just like the simple `@CsvSource`, the delimiter is customizable with the `delimiter` attribute.

In addition to the column separator, we have these capabilities:

- The line separator can be customized using the `lineSeparator` attribute — a newline is the default value.
- The file encoding is customizable using the `encoding` attribute — UTF-8 is the default value.

### 1.6. Method

The argument sources we’ve covered so far are somewhat simple and share one limitation. It’s hard or impossible to pass complex objects using them.

One approach to providing more complex arguments is to use a method as an argument source.

Let’s test the `isBlank` method with a `@MethodSource`:

Sure, let's continue from where we left off:

```java  
@ParameterizedTest  
@MethodSource("provideStringsForIsBlank")  
void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {  
 assertEquals(expected, Strings.isBlank(input));  
}  
```  

The name we supply to `@MethodSource` needs to match an existing method.

So, let’s next write `provideStringsForIsBlank`, a static method that returns a `Stream` of `Arguments`:

```java  
private static Stream<Arguments> provideStringsForIsBlank() {  
 return Stream.of( Arguments.of(null, true), Arguments.of("", true), Arguments.of("  ", true), Arguments.of("not blank", false) );}  
```  

Here we’re returning a stream of arguments, but it’s not a strict requirement. For example, we can return any other collection-like interfaces like `List`.

If we’re going to provide just one argument per test invocation, then it’s not necessary to use the `Arguments` abstraction:

```java  
@ParameterizedTest  
@MethodSource // hmm, no method name ...void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
  
private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {  
 return Stream.of(null, "", "  ");}  
```  

When we don’t provide a name for the `@MethodSource`, JUnit will search for a source method with the same name as the test method.

Sometimes, it’s useful to share arguments between different test classes. In these cases, we can refer to a source method outside of the current class by its fully qualified name:

```java  
class StringsUnitTest {  
 @ParameterizedTest @MethodSource("com.baeldung.parameterized.StringParams#blankStrings") void isBlank_ShouldReturnTrueForNullOrBlankStringsExternalSource(String input) { assertTrue(Strings.isBlank(input)); }}  
  
public class StringParams {  
  
 static Stream<String> blankStrings() { return Stream.of(null, "", "  "); }}  
```  

Using the `FQN#methodName` format, we can refer to an external static method.

### 1.7. Field

Using a method as the argument source proved to be a useful way to supply the test data. Consequently, starting with JUnit 5.11, we can now use a similar feature with static fields, through the experimental annotation `@FieldSource`:

```java  
static List<String> cities = Arrays.asList("Madrid", "Rome", "Paris", "London");  
  
@ParameterizedTest  
@FieldSource("cities")  
void isBlank_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter(String arg) {  
 assertFalse(Strings.isBlank(arg));}  
```  

As we can see, the annotation points to a static field referencing the test data, which can be represented as a `Collection`, an `Iterable`, an object array, or a `Supplier<Stream>`. After that, the parameterized test will be executed for each test input. Similar to `@MethodSource`, if the name of the static field matches the name of the test, the value of the annotation can be omitted:

```java  
static String[] isEmpty_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter = { "Spain", "Italy", "France", "England" };  
  
@ParameterizedTest  
@FieldSource  
void isEmpty_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter(String arg) {  
 assertFalse(arg.isEmpty());}  
```  

### 1.8. Custom Argument Provider

Another advanced approach to pass test arguments is to use a custom implementation of an interface called `ArgumentsProvider`:

```java  
class BlankStringsArgumentsProvider implements ArgumentsProvider {  
 @Override public Stream<? extends Arguments> provideArguments(ExtensionContext context) { return Stream.of( Arguments.of((String) null),          Arguments.of(""),   
          Arguments.of("   ")   
        );  
 }}  
```  

Then we can annotate our test with the `@ArgumentsSource` annotation to use this custom provider:

```java  
@ParameterizedTest  
@ArgumentsSource(BlankStringsArgumentsProvider.class)  
void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {  
 assertTrue(Strings.isBlank(input));  
}  
```  

Let’s make the custom provider a more pleasant API to use with a custom annotation.

### 1.9. Custom Annotation

Suppose we want to load the test arguments from a static variable:

```java  
static Stream<Arguments> arguments = Stream.of(  
 Arguments.of(null, true), // null strings should be considered blank Arguments.of("", true), Arguments.of("  ", true), Arguments.of("not blank", false));  
  
@ParameterizedTest  
@VariableSource("arguments")  
void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(  
 String input, boolean expected) { assertEquals(expected, Strings.isBlank(input));}  
```  

Actually, JUnit 5 does not provide this. However, we can roll our own solution.

First, we can create an annotation:

```java  
@Documented  
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@ArgumentsSource(VariableArgumentsProvider.class)  
public @interface VariableSource {  
 /** * The name of the static variable */  String value();  
}  
```  

Then we need to somehow consume the annotation details and provide test arguments. JUnit 5 provides two abstractions to achieve those:

- `AnnotationConsumer` to consume the annotation details
- `ArgumentsProvider` to provide test arguments

So, we next need to make the `VariableArgumentsProvider` class read from the specified static variable and return its value as test arguments:

```java  
class VariableArgumentsProvider   
  implements ArgumentsProvider, AnnotationConsumer<VariableSource> {  
  
 private String variableName;  
 @Override public Stream<? extends Arguments> provideArguments(ExtensionContext context) { return context.getTestClass() .map(this::getField) .map(this::getValue) .orElseThrow(() ->                  new IllegalArgumentException("Failed to load test arguments"));  
 }  
 @Override public void accept(VariableSource variableSource) { variableName = variableSource.value(); }  
 private(Class<?> clazz) { try { return clazz.getDeclaredField(variableName); } catch (Exception e) { return null; } }  
 @SuppressWarnings("unchecked") private Stream<Arguments> getValue(Field field) { Object value = null; try { value = field.get(null); } catch (Exception ignored) {}  
 return value == null ? null : (Stream<Arguments>) value; }}  
```  

### Difference Between Multiple Tests and a Single Parameterized Test

#### Single Parameterized Test

Using a single parameterized test allows you to run the same test logic with different sets of input values. This approach reduces code duplication and makes the test cases more maintainable.

```java  
@ParameterizedTest  
@CsvSource({  
 "1, 1, 2", "2, 3, 5", "-1, -1, -2", "-1, 1, 0"})  
void testAdd(int a, int b, int expected) {  
 assertEquals(expected, calculator.add(a, b));  
}  
```  

**Advantages:**
- **Less Code Duplication:** The test logic is written once and executed with different inputs.
- **Easier Maintenance:** Adding or modifying test cases is simpler as you only need to update the `CsvSource`.
- **Clearer Test Structure:** All test cases for a particular method are grouped together, making it easier to understand the coverage.

#### Multiple Individual Tests

Writing multiple individual tests involves creating separate test methods for each set of input values. This approach can lead to code duplication and make the tests harder to maintain.

```java  
@Test  
void testAdd1() {  
 assertEquals(2, calculator.add(1, 1));}  
  
@Test  
void testAdd2() {  
 assertEquals(5, calculator.add(2, 3));}  
  
@Test  
void testAdd3() {  
 assertEquals(-2, calculator.add(-1, -1));}  
  
@Test  
void testAdd4() {  
 assertEquals(0, calculator.add(-1, 1));}  
```  

**Advantages:**
- **Explicit Test Cases:** Each test case is explicitly defined, which can make it easier to understand individual test scenarios.
- **Granular Control:** Allows for more granular control over each test case, including specific setup or teardown logic.

**Disadvantages:**
- **Code Duplication:** The same test logic is repeated for each test case, leading to more code.
- **Harder Maintenance:** Adding or modifying test cases requires changes to multiple methods.
- **Cluttered Test Class:** The test class can become cluttered with many small test methods, making it harder to navigate.