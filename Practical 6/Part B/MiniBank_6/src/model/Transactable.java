package model;

// Interface for basic banking transactions
public interface Transactable
{
    // Deposit money into an account
    void deposit(long amount);

    // Withdraw money from an account
    boolean withdraw(long amount);
}