package model;

// Savings account with minimum balance requirement
public class SavingsAccount extends Account
{
    private long minBalance;

    // Constructor to initialize savings account
    public SavingsAccount(String ownerName, long balance, long minBalance)
    {
        super(ownerName,balance);
        this.minBalance=minBalance;
    }

    // Savings account earns 4% yearly interest
    @Override
    public double interestRate()
    {
        return 4.0;
    }

    // Allow withdrawal only if minimum balance is maintained
    @Override
    public boolean canWithdraw(long amount)
    {
        return amount>0 && getBalance()>=minBalance &&
               amount<=getBalance()-minBalance;
    }

    // Getter for minimum balance
    public long getMinBalance()
    {
        return minBalance;
    }
}