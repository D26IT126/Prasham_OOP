import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Account model implementing Comparable by account number
class Account implements Comparable<Account>
{
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    @Override
    public int compareTo(Account other)
    {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString()
    {
        return "Account{" + "accountNumber='" + accountNumber + '\'' + ", balance=" + balance + '}';
    }
}

// AccountStore managing accounts in a HashMap and transactions in an ArrayList
class AccountStore
{
    private Map<String, Account> accounts = new HashMap<>();
    private List<String> transactionHistory = new ArrayList<>();

    public void add(Account account)
    {
        accounts.put(account.getAccountNumber(), account);
        transactionHistory.add("Added account: " + account.getAccountNumber());
    }

    public Account get(String accountNumber)
    {
        return accounts.get(accountNumber);
    }

    public List<Account> getAll()
    {
        return new ArrayList<>(accounts.values());
    }

    public List<String> getTransactionHistory()
    {
        return transactionHistory;
    }

    // Returns a list sorted by balance (ascending) using a Comparator
    public List<Account> sortedByBalance()
    {
        List<Account> list = getAll();
        Collections.sort(list, new Comparator<Account>()
        {
            @Override
            public int compare(Account a1, Account a2)
            {
                return Double.compare(a1.getBalance(), a2.getBalance());
            }
        });
        return list;
    }
}

// TransactionProcessor using ConcurrentHashMap for thread-safe access
class TransactionProcessor
{
    private Map<String, Account> sharedAccounts = new ConcurrentHashMap<>();

    public void addSharedAccount(Account account)
    {
        sharedAccounts.put(account.getAccountNumber(), account);
    }

    public Account getSharedAccount(String accountNumber)
    {
        return sharedAccounts.get(accountNumber);
    }
}

public class MiniBank
{
    public static void main(String[] args)
    {
        AccountStore store = new AccountStore();

        // Adding several accounts
        store.add(new Account("ACC101", 1500.50));
        store.add(new Account("ACC102", 500.25));
        store.add(new Account("ACC103", 3200.00));
        store.add(new Account("ACC104", 1200.75));

        // Printing accounts sorted by balance
        System.out.println("Accounts sorted by balance:");
        List<Account> sortedAccounts = store.sortedByBalance();
        for(Account acc : sortedAccounts)
        {
            System.out.println(acc);
        }
    }
}