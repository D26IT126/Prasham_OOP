package util;

import model.Account;

// Functional interface for checking withdrawal permission
@FunctionalInterface
public interface WithdrawRule
{
    boolean allow(Account account, long amount);
}