package nl.rabobank.ew.parameterizedtest.examples;

import java.util.stream.Stream;

/**
 * Provides test cases for the {@code isBlank()} method.
 * This external method supplies a stream of blank or null strings for testing.
 */
public class StringParams {
    static Stream<String> blankStrings() {
        return Stream.of(null, "", "  ");
    }
}
