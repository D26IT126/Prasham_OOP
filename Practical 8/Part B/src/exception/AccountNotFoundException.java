package exception;

// Exception when a bank account is not found
public class AccountNotFoundException extends BankException
{
    public AccountNotFoundException(String accountNumber)
    {
        super("Account not found: "+accountNumber);
    }
}