package exception;

// Exception for insufficient account balance
public class InsufficientFundsException extends BankException
{
    private long shortfall;

    public InsufficientFundsException(long shortfall)
    {
        super("Insufficient funds. Shortfall: Rs. "+shortfall);
        this.shortfall=shortfall;
    }

    public long getShortfall()
    {
        return shortfall;
    }
}