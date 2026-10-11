abstract class Account
{
    private String ownerName;
    private long balance;

    // Constructor to initialize account details
    public Account(String ownerName, long balance)
    {
        this.ownerName=ownerName;
        this.balance=balance;
    }

    // Getter methods
    public String getOwnerName()
    {
        return ownerName;
    }

    public long getBalance()
    {
        return balance;
    }

    // Abstract methods implemented by subclasses
    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    // Deposit money into the account
    public void deposit(long amount)
    {
        if(amount>0 && amount<=Long.MAX_VALUE-balance)
        {
            balance+=amount;
        }
    }

    // Withdraw money according to account rules
    public boolean withdraw(long amount)
    {
        if(amount<=0 || !canWithdraw(amount))
        {
            return false;
        }

        balance-=amount;
        return true;
    }
}

// Savings account with minimum balance requirement
class SavingsAccount extends Account
{
    private long minBalance;

    public SavingsAccount(String ownerName, long balance, long minBalance)
    {
        super(ownerName,balance);
        this.minBalance=minBalance;
    }

    @Override
    public double interestRate()
    {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount)
    {
        return amount>0 && getBalance()>=minBalance &&
               amount<=getBalance()-minBalance;
    }

    public long getMinBalance()
    {
        return minBalance;
    }
}

// Current account with overdraft facility
class CurrentAccount extends Account
{
    private long overdraftLimit;

    public CurrentAccount(String ownerName, long balance, long overdraftLimit)
    {
        super(ownerName,balance);
        this.overdraftLimit=overdraftLimit;
    }

    @Override
    public double interestRate()
    {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount)
    {
        return amount>0 && overdraftLimit>=0 &&
               getBalance()>=-overdraftLimit &&
               amount<=getBalance()+overdraftLimit;
    }

    public long getOverdraftLimit()
    {
        return overdraftLimit;
    }
}

// Fixed deposit account with locked withdrawals
class FixedDepositAccount extends Account
{
    public FixedDepositAccount(String ownerName, long balance)
    {
        super(ownerName,balance);
    }

    @Override
    public double interestRate()
    {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount)
    {
        return false;
    }
}

public class MiniBank
{
    public static void main(String[] args)
    {
        // Create accounts of different types
        Account[] accounts=new Account[3];

        accounts[0]=new SavingsAccount("Rahul",10000,2000);
        accounts[1]=new CurrentAccount("Amit",5000,3000);
        accounts[2]=new FixedDepositAccount("Priya",20000);

        // Demonstrate runtime polymorphism
        for(Account account:accounts)
        {
            System.out.println("Account Type: "+account.getClass().getSimpleName());
            System.out.println("Owner: "+account.getOwnerName());
            System.out.println("Balance: Rs. "+account.getBalance());
            System.out.println("Interest Rate: "+account.interestRate()+"%");

            // Pattern matching with instanceof
            if(account instanceof SavingsAccount savings)
            {
                System.out.println("Minimum Balance: Rs. "+savings.getMinBalance());
            }

            // Test withdrawal rules
            boolean success=account.withdraw(6000);

            System.out.println("Withdrawal Successful: "+success);
            System.out.println("Final Balance: Rs. "+account.getBalance());
            System.out.println("-----------------------------");
        }
    }
}