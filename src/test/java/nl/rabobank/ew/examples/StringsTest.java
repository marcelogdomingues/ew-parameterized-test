package nl.rabobank.ew.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.rabobank.ew.parameterizedtest.examples.StringsExample;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

/**
 * Unit test for {@link StringsExample#isBlank(String)} method.
 * Tests cases where the input should be considered blank.
 */
class StringsTest {

    /**
     * Tests the {@code isBlank} method to ensure it correctly identifies blank strings.
     * <p>
     * This test uses a parameterized approach with {@link ValueSource} to pass different blank
     * strings, including an empty string and a string with only whitespace.
     * </p>
     *
     * @param input the input string to be checked for blankness
     */
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(StringsExample.isBlank(input), "Expected input to be considered blank");
    }

    /**
     * Tests {@code StringsExample.isBlank()} with different inputs.
     * <p>
     * This parameterized test verifies that {@code isBlank()} correctly returns {@code true}
     * when provided with {@code null} inputs.
     *
     * @param input the input string to be tested (in this case, {@code null})
     */
    @ParameterizedTest
    @NullSource
    void isBlank_ShouldReturnTrueForNullInputs(String input) {
        assertTrue(StringsExample.isBlank(input));
    }

    /**
     * Parameterized test to verify {@code StringsExample.isBlank()} behavior with empty inputs.
     * <p>
     * This test checks that {@code isBlank()} correctly returns {@code true}
     * when provided with an empty string input.
     * <p>
     * The {@code @EmptySource} annotation supplies an empty string as a test argument,
     * ensuring the method properly handles such cases.
     *
     * @param input the input string to be tested (expected to be empty)
     */
    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings(String input) {
        assertTrue(StringsExample.isBlank(input));
    }

    /**
     * Parameterized test to verify {@code StringsExample.isBlank()} behavior with null and empty inputs.
     * <p>
     * This test checks that {@code isBlank()} correctly returns {@code true}
     * when provided with either a {@code null} input or an empty string.
     * <p>
     * The {@code @NullAndEmptySource} annotation supplies both {@code null} and an empty string
     * as test arguments, ensuring the method handles these cases correctly.
     *
     * @param input the input string to be tested (expected to be {@code null} or empty)
     */
    @ParameterizedTest
    @NullAndEmptySource
    void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String input) {
        assertTrue(StringsExample.isBlank(input));
    }

    /**
     * Tests the {@code isBlank} method from {@code StringsExample}
     * using various types of blank strings.
     *
     * @param input the string to be tested for blankness
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
        assertTrue(StringsExample.isBlank(input));
    }


    /**
     * This test verifies that the {@code isBlank()} method correctly identifies
     * null or blank strings. The test uses a method source to provide test cases.
     */
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        // Assert that the isBlank method correctly identifies blank or null strings
        assertEquals(expected, StringsExample.isBlank(input));
    }

    /**
     * Provides test cases for the {@code isBlank()} method.
     * The method returns a stream of arguments containing various input strings
     * and their expected boolean results.
     *
     * @return a stream of test cases for the {@code isBlank()} method
     */
    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    /**
     * This test verifies that the {@code isBlank()} method correctly identifies
     * null or blank strings using a single argument. The test method automatically
     * matches the method source since their names are identical.
     */
    @ParameterizedTest
    @MethodSource // hmm, no method name ...
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        // Assert that the isBlank method correctly identifies blank or null strings
        assertTrue(StringsExample.isBlank(input));
    }

    /**
     * Provides test cases for the {@code isBlank()} method.
     * Returns a stream of strings containing various input values.
     *
     * @return a stream of blank or null strings
     */
    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }

    @ParameterizedTest
    @MethodSource("nl.rabobank.ew.parameterizedtest.examples.StringParams#blankStrings")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsExternalSource(String input) {
        // Assert that the isBlank method correctly identifies blank or null strings
        assertTrue(StringsExample.isBlank(input));
    }

    static String[] isEmpty_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter = { "Spain", "Italy", "France", "England" };

    /**
     * Unit test for {@code String.isEmpty()} method.
     * Ensures that the method returns {@code false} when
     * the argument has at least one character.
     *
     * @param arg a non-null string argument provided by the FieldSource
     */
    @ParameterizedTest
    @FieldSource
    void isEmpty_ShouldReturnFalseWhenTheArgHasAtLeastOneCharacter(String arg) {
        assertFalse(arg.isEmpty());
    }

}