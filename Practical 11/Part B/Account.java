import java.io.Serializable;

// Bank account supporting serialization
public class Account implements Serializable
{
    private static final long serialVersionUID=1L;

    private String accountNumber;
    private Customer customer;
    private long balance;

    public Account(String accountNumber,Customer customer,long balance)
    {
        this.accountNumber=accountNumber;
        this.customer=customer;
        this.balance=balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public long getBalance()
    {
        return balance;
    }

    // Deposit money into the account
    public void deposit(long amount)
    {
        if(amount>0)
        {
            balance+=amount;
        }
    }

    // Withdraw money from the account
    public void withdraw(long amount)
    {
        if(amount>0 && amount<=balance)
        {
            balance-=amount;
        }
    }

    @Override
    public String toString()
    {
        return "Account Number: "+accountNumber+
               ", "+customer+
               ", Balance: Rs. "+balance;
    }
}