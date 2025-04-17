package nl.engineers.week.parameterizedtest.bank;

public class BankAccountExample {

    private final String accountNumber;
    private double balance;

    /**
     * Creates a new BankAccount with the given account number and initial balance.
     *
     * @param accountNumber the account number
     * @param initialBalance the initial balance
     */
    public BankAccountExample(String accountNumber, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if the amount is negative
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if the amount is negative or exceeds the balance
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}
