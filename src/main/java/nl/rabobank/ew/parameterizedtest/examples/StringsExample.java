package nl.rabobank.ew.parameterizedtest.examples;

/**
 * Utility class for string-related operations.
 */
public class StringsExample {

    /**
     * Checks if a given string is blank.
     * A string is considered blank if it is null, empty, or contains only whitespace.
     *
     * @param input the string to check
     * @return {@code true} if the input is blank, {@code false} otherwise
     */
    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}
