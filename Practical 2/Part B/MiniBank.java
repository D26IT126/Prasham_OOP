class Customer
{
    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter=100;

    // Generate a unique customer ID
    private static String generateCustomerId()
    {
        customerCounter++;
        return "CUST"+customerCounter;
    }

    // Customer constructor
    public Customer(String name, String email, String mobile)
    {
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.customerId=generateCustomerId();
    }

    // Getter methods
    public String getCustomerId()
    {
        return customerId;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getMobile()
    {
        return mobile;
    }
}

class Account
{
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static long accountCounter=0;

    // Generate a unique account number
    private static String generateAccountNumber()
    {
        accountCounter++;
        return String.format("AC%04d",accountCounter);
    }

    // Constructor with opening balance
    public Account(String ownerName, long balance)
    {
        this.accountNumber=generateAccountNumber();
        this.ownerName=ownerName;
        this.balance=Math.max(0,balance);
        this.active=true;
    }

    // Constructor with zero opening balance
    public Account(String ownerName)
    {
        this(ownerName,0);
    }

    // Deposit money into the account
    public void deposit(long amount)
    {
        if(amount>0 && amount<=Long.MAX_VALUE-balance)
        {
            balance+=amount;
        }
    }

    // Withdraw money if sufficient balance is available
    public boolean withdraw(long amount)
    {
        if(amount<=0 || amount>balance)
        {
            return false;
        }

        balance-=amount;
        return true;
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

    public boolean isActive()
    {
        return active;
    }
}

public class MiniBank
{
    public static void main(String[] args)
    {
        // Create three accounts using an array
        Account[] accounts=new Account[3];

        accounts[0]=new Account("Rahul",5000);
        accounts[1]=new Account("Amit",3000);
        accounts[2]=new Account("Priya");

        // Perform deposit operations
        accounts[0].deposit(2000);
        accounts[1].deposit(1500);
        accounts[2].deposit(4000);

        // Perform withdrawal operations
        accounts[0].withdraw(1000);
        accounts[1].withdraw(500);
        accounts[2].withdraw(1500);

        // Display account details and final balances
        for(Account account:accounts)
        {
            System.out.println("Account Number: "+account.getAccountNumber());
            System.out.println("Owner Name: "+account.getOwnerName());
            System.out.println("Balance: Rs. "+account.getBalance());
            System.out.println("Active: "+account.isActive());
            System.out.println("-------------------------");
        }
    }
}