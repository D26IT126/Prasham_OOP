package model;

// Abstract account class implementing banking interfaces
public abstract class Account implements Transactable, InterestBearing
{
    private String ownerName;
    private long balance;

    // Constructor to initialize account details
    public Account(String ownerName, long balance)
    {
        this.ownerName=ownerName;
        this.balance=balance;
    }

    // Getter methods
    public String getOwnerName()
    {
        return ownerName;
    }

    public long getBalance()
    {
        return balance;
    }

    // Abstract methods implemented by account subclasses
    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    // Deposit money into the account
    @Override
    public void deposit(long amount)
    {
        if(amount>0 && amount<=Long.MAX_VALUE-balance)
        {
            balance+=amount;
        }
    }

    // Withdraw money according to account rules
    @Override
    public boolean withdraw(long amount)
    {
        if(amount<=0 || !canWithdraw(amount))
        {
            return false;
        }

        balance-=amount;
        return true;
    }
}