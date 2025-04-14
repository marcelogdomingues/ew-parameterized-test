package nl.rabobank.ew.examples;

import nl.rabobank.ew.parameterizedtest.examples.CalculatorExample;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorParamTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // List of test cases
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        CalculatorExample calculator = new CalculatorExample();

        assertTrue(calculator.isOdd(number));
    }

}
