package model;

// Fixed deposit account with locked withdrawals
public class FixedDepositAccount extends Account
{
    // Constructor to initialize fixed deposit account
    public FixedDepositAccount(String ownerName, long balance)
    {
        super(ownerName,balance);
    }

    // Fixed deposits earn 7% yearly interest
    @Override
    public double interestRate()
    {
        return 7.0;
    }

    // Withdrawals are not allowed from fixed deposits
    @Override
    public boolean canWithdraw(long amount)
    {
        return false;
    }
}