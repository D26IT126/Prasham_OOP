package model;

// Current account with overdraft facility
public class CurrentAccount extends Account implements Premium
{
    private long overdraftLimit;

    // Constructor to initialize current account
    public CurrentAccount(String ownerName, long balance, long overdraftLimit)
    {
        super(ownerName,balance);
        this.overdraftLimit=overdraftLimit;
    }

    // Current accounts do not earn interest
    @Override
    public double interestRate()
    {
        return 0.0;
    }

    // Allow withdrawal within the overdraft limit
    @Override
    public boolean canWithdraw(long amount)
    {
        return amount>0 && overdraftLimit>=0 &&
               getBalance()>=-overdraftLimit &&
               amount<=getBalance()+overdraftLimit;
    }

    // Getter for overdraft limit
    public long getOverdraftLimit()
    {
        return overdraftLimit;
    }
}