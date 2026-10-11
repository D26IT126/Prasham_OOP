import exception.*;

// Bank account with exception handling
public class Account
{
    private static long accountCounter=0;

    private final String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String ownerName,long balance)
    {
        accountCounter++;
        this.accountNumber=String.format("AC%04d",accountCounter);
        this.ownerName=ownerName;
        this.balance=balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public long getBalance()
    {
        return balance;
    }

    // Deposit money into the account
    public void deposit(long amount) throws InvalidAmountException
    {
        if(amount<=0)
        {
            throw new InvalidAmountException(amount);
        }

        balance+=amount;
    }

    // Withdraw money from the account
    public void withdraw(long amount) throws InsufficientFundsException,InvalidAmountException
    {
        if(amount<=0)
        {
            throw new InvalidAmountException(amount);
        }

        if(amount>balance)
        {
            throw new InsufficientFundsException(amount-balance);
        }

        balance-=amount;
    }

    // Transfer money from this account to another account
    public void transfer(Account to,long amount) throws BankException
    {
        boolean withdrawn=false;

        try
        {
            if(to==null)
            {
                throw new AccountNotFoundException("Unknown");
            }

            withdraw(amount);
            withdrawn=true;
            to.deposit(amount);

            System.out.println("Transfer successful.");
        }
        catch(BankException e)
        {
            // Refund the amount if deposit fails after withdrawal
            if(withdrawn)
            {
                try
                {
                    deposit(amount);
                }
                catch(InvalidAmountException refundError)
                {
                    e.addSuppressed(refundError);
                }
            }

            throw e;
        }
        finally
        {
            System.out.println("Transfer operation completed.");
        }
    }

    @Override
    public String toString()
    {
        return "Account Number: "+accountNumber+
               ", Owner: "+ownerName+
               ", Balance: Rs. "+balance;
    }
}