package service;

import model.Account;
import util.WithdrawRule;

// Service class for performing banking operations
public class BankService
{
    // Withdraw money only if the provided rule allows it
    public static boolean processWithdrawal(Account account, long amount, WithdrawRule rule)
    {
        if(account==null || rule==null || amount<=0)
        {
            return false;
        }

        if(rule.allow(account,amount))
        {
            return account.withdraw(amount);
        }

        return false;
    }
}