package model;

// Interface for accounts that earn interest
public interface InterestBearing
{
    // Return the annual interest rate in percentage
    double interestRate();

    // Calculate yearly interest using balance and interest rate
    long getBalance();

    default double yearlyInterest()
    {
        return getBalance()*interestRate()/100.0;
    }
}