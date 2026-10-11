import java.util.Objects;

class Customer implements Cloneable
{
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;

    private static long customerCounter=100;

    // Generate a unique customer ID
    private static String generateCustomerId()
    {
        customerCounter++;
        return "CUST"+customerCounter;
    }

    // Static nested class for customer address
    public static class Address
    {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode)
        {
            this.line=line;
            this.city=city;
            this.pincode=pincode;
        }

        public String getLine()
        {
            return line;
        }

        public String getCity()
        {
            return city;
        }

        public String getPincode()
        {
            return pincode;
        }
    }

    // Customer constructor
    public Customer(String name, String email, String mobile, Address address)
    {
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.customerId=generateCustomerId();
        this.address=address;
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

    public Address getAddress()
    {
        return address;
    }

    // Create a copy of the customer and address
    @Override
    public Customer clone()
    {
        try
        {
            Customer copy=(Customer)super.clone();

            if(address!=null)
            {
                copy.address=new Address(address.line,address.city,address.pincode);
            }

            return copy;
        }
        catch(CloneNotSupportedException e)
        {
            throw new AssertionError(e);
        }
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

    // Deposit money
    public void deposit(long amount)
    {
        if(amount>0 && amount<=Long.MAX_VALUE-balance)
        {
            balance+=amount;
        }
    }

    // Withdraw money if sufficient balance exists
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

    // Return account details as readable text
    @Override
    public String toString()
    {
        return "Account Number: "+accountNumber+
               ", Owner: "+ownerName+
               ", Balance: Rs. "+balance;
    }

    // Compare accounts using account numbers
    @Override
    public boolean equals(Object o)
    {
        if(this==o)
        {
            return true;
        }

        if(!(o instanceof Account))
        {
            return false;
        }

        Account other=(Account)o;
        return Objects.equals(accountNumber,other.accountNumber);
    }

    // Generate hash code using account number
    @Override
    public int hashCode()
    {
        return Objects.hashCode(accountNumber);
    }
}

public class MiniBank
{
    public static void main(String[] args)
    {
        // Create two accounts
        Account a1=new Account("Rahul",5000);
        Account a2=new Account("Amit",3000);

        // Display accounts using toString()
        System.out.println(a1);
        System.out.println(a2);

        // Compare accounts using equals()
        System.out.println("Accounts Equal: "+a1.equals(a2));

        // Check object type using instanceof
        Object obj=a1;

        if(obj instanceof Account)
        {
            System.out.println("Object is an Account");
        }

        // Create customer with address
        Customer.Address address=new Customer.Address("MG Road","Ahmedabad","380001");

        Customer c1=new Customer("Rahul","rahul@gmail.com","9876543210",address);

        // Clone the customer
        Customer c2=c1.clone();

        System.out.println("Original Customer ID: "+c1.getCustomerId());
        System.out.println("Cloned Customer ID: "+c2.getCustomerId());
        System.out.println("Customer City: "+c2.getAddress().getCity());
        System.out.println("Same Customer Object: "+(c1==c2));
    }
}