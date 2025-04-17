package nl.engineers.week.examples;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GeneralTest {

    /**
     * Parameterized test to verify the behavior of {@code String.toUpperCase()}.
     * <p>
     * This test checks that converting a string to uppercase produces the expected result.
     * <p>
     * The {@code @CsvSource} annotation supplies various input-output pairs,
     * ensuring the method handles different capitalizations correctly.
     *
     * @param input    the original string to be converted
     * @param expected the expected uppercase output
     */
    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    /**
     * This test verifies that the {@code toLowerCase()} method correctly converts
     * various input strings to their expected lowercase representations.
     */
    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        // Convert the input string to lowercase
        String actualValue = input.toLowerCase();

        // Assert that the actual value matches the expected lowercase representation
        assertEquals(expected, actualValue);
    }


    static List<String> cities = Arrays.asList("Madrid", "Rome", "Paris", "London");

    /**
     * Unit test for {@code Strings.isBlank(String)} method.
     * Ensures that the method returns {@code false} when
     * the argument has at least one character.
     *
     * @param arg a non-null string argument provided by the FieldSource "cities"
     */
    @ParameterizedTest
    @FieldSource("cities")
    void isBlank_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter(String arg) {
        assertFalse(Strings.isBlank(arg));
    }

}
