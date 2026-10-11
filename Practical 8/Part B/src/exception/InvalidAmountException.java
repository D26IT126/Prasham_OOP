package exception;

// Exception for zero or negative transaction amounts
public class InvalidAmountException extends BankException
{
    public InvalidAmountException(long amount)
    {
        super("Invalid amount: Rs. "+amount+". Amount must be greater than zero.");
    }
}