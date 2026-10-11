import model.annotation.*;

// Account class with annotated fields
public class Account
{
    private static long accountCounter=0;

    @Id
    private final String accountNumber;

    @MaxLength(30)
    private String ownerName;

    @Positive
    private long balance;

    // Constructor to initialize account details
    public Account(String ownerName, long balance)
    {
        accountCounter++;
        this.accountNumber=String.format("AC%04d",accountCounter);
        this.ownerName=ownerName;
        this.balance=balance;
    }

    // Getter methods
    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public long getBalance()
    {
        return balance;
    }

    // Display account information
    @Override
    public String toString()
    {
        return "Account Number: "+accountNumber+
               ", Owner: "+ownerName+
               ", Balance: Rs. "+balance;
    }

    // Compare accounts using account numbers
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
        {
            return true;
        }

        if(!(obj instanceof Account))
        {
            return false;
        }

        Account other=(Account)obj;
        return accountNumber.equals(other.accountNumber);
    }

    // Generate hash code using account number
    @Override
    public int hashCode()
    {
        return accountNumber.hashCode();
    }
}