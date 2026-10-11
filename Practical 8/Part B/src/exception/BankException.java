package exception;

// Base checked exception for banking errors
public class BankException extends Exception
{
    public BankException(String message)
    {
        super(message);
    }
}