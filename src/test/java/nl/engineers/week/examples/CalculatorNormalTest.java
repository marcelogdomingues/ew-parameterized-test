package nl.engineers.week.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.engineers.week.parameterizedtest.examples.CalculatorExample;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link CalculatorExample}.
 * This class tests the addition functionality of {@code CalculatorExample}.
 */
class CalculatorNormalTest {

    /**
     * Tests the {@link CalculatorExample#add(int, int)} method.
     * Verifies that addition operations return the expected results. */  @Test
    void testAddition() {
        CalculatorExample calculator = new CalculatorExample();

        // Multiple assertions within a single test method
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
        assertEquals(0, calculator.add(-1, 1), "-1 + 1 should equal 0");
        assertEquals(-3, calculator.add(-1, -2), "-1 + (-2) should equal -3");
    }

    /*
    Alternative
     */
    @Test
    void testAdditionPositiveNumbers() {
        CalculatorExample calculator = new CalculatorExample();
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    /**
     * Tests the addition of two negative numbers. * Verifies that negative numbers are summed correctly. */
    @Test
    void testAdditionNegativeNumbers() {
        CalculatorExample calculator = new CalculatorExample();
        assertEquals(-3, calculator.add(-1, -2), "-1 + (-2) should equal -3");
    }

    /**
     * Tests the addition of a negative and a positive number. * Verifies that neutralization through addition works as expected. */@Test
    void testAdditionNeutralNumbers() {
        CalculatorExample calculator = new CalculatorExample();
        assertEquals(0, calculator.add(-1, 1), "-1 + 1 should equal 0");
    }
}