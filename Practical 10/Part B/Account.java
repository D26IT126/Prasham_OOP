public class Account
{
    private final long accountNumber;
    private long balance;

    public Account(long accountNumber,long balance)
    {
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    // Deposit money safely
    public synchronized void deposit(long amount)
    {
        if(amount>0)
        {
            balance+=amount;
        }
    }

    // Withdraw money safely
    public synchronized void withdraw(long amount)
    {
        if(amount>0 && balance>=amount)
        {
            balance-=amount;
        }
    }

    // Get the current balance safely
    public synchronized long getBalance()
    {
        return balance;
    }

    // Transfer money using a specified lock order
    public void transfer(Account to,long amount,boolean correctOrder)
    {
        Account first=this;
        Account second=to;

        // Always lock the smaller account number first
        if(correctOrder && first.accountNumber>second.accountNumber)
        {
            first=to;
            second=this;
        }

        synchronized(first)
        {
            synchronized(second)
            {
                if(amount>0 && balance>=amount)
                {
                    balance-=amount;
                    to.balance+=amount;
                }
            }
        }
    }
}