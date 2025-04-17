package nl.engineers.week.examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonthTest {

    /**
     * Parameterized test to verify {@code Month.getValue()} returns valid month numbers.
     * <p>
     * This test ensures that the {@code getValue()} method of the {@code Month} enum
     * always returns values between 1 (January) and 12 (December), inclusive.
     * <p>
     * The {@code @EnumSource(Month.class)} annotation supplies all 12 months as test arguments,
     * verifying correctness across the entire {@code Month} enum.
     *
     * @param month the month to be tested (expected to return values between 1 and 12)
     */
    @ParameterizedTest
    @EnumSource(Month.class) // passing all 12 months
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12); //Condition always true, just testing purpose
    }

    /**
     * Parameterized test to verify that certain months have 30 days.
     * <p>
     * This test checks that {@code Month.length(false)} correctly returns {@code 30}
     * for the months April, June, September, and November.
     * <p>
     * The {@code @EnumSource} annotation supplies only these four months as test arguments,
     * ensuring the method handles them appropriately.
     *
     * @param month the month to be tested (expected to have 30 days)
     */
    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void someMonths_Are30DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }

    /**
     * Parameterized test to verify that months other than April, June, September,
     * November, and February have 31 days.
     * <p>
     * This test checks that {@code Month.length(false)} correctly returns {@code 31}
     * for all months except the specified four.
     * <p>
     * The {@code @EnumSource} annotation supplies all months except April, June,
     * September, November, and February as test arguments, ensuring the method handles them appropriately.
     *
     * @param month the month to be tested (expected to have 31 days)
     */
    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
            mode = EnumSource.Mode.EXCLUDE)
    void exceptFourMonths_OthersAre31DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(31, month.length(isALeapYear));
    }

    /**
     * Parameterized test to verify months that end with "ber."
     * <p>
     * This test checks that the {@code Month} enum correctly matches months whose names
     * end with "ber": September, October, November, and December.
     * <p>
     * The {@code @EnumSource} annotation uses a regex pattern to match any {@code Month}
     * whose name ends in "BER," ensuring the test applies only to the intended months.
     *
     * @param month the month to be tested (expected to be September, October, November, or December)
     */
    @ParameterizedTest
    @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
    void fourMonths_AreEndingWithBer(Month month) {
        EnumSet<Month> months = EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        assertTrue(months.contains(month));
    }

}
