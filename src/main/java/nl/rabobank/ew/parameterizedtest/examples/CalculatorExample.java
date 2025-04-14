package nl.rabobank.ew.parameterizedtest.examples;

public class CalculatorExample {

    /**
     * Checks if a number is even.
     *
     * @param number the number to check
     * @return true if the number is even, false otherwise
     */
    public boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     * Adds two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second integer from the first integer.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the difference between a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second integer.
     * Throws IllegalArgumentException if the divisor is zero.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of a divided by b
     * @throws IllegalArgumentException if b is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }

    /**
     * Finds the remainder of the division of the first integer by the second integer.
     * Throws IllegalArgumentException if the divisor is zero.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the remainder of a divided by b
     * @throws IllegalArgumentException if b is zero
     */
    public int modulo(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a % b;
    }

    /**
     * Calculates the power of a base raised to an exponent.
     *
     * @param base     the base number
     * @param exponent the exponent
     * @return the result of base raised to the power of exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Calculates the square root of a number.
     * Throws IllegalArgumentException if the number is negative.
     *
     * @param number the number to find the square root of
     * @return the square root of the number
     * @throws IllegalArgumentException if the number is negative
     */
    public double sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Square root of negative number is not allowed.");
        }
        return Math.sqrt(number);
    }

}