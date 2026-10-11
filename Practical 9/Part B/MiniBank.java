class Account
{
    private long balance;

    public Account(long balance)
    {
        this.balance=balance;
    }

    // Deposit without synchronization
    public void deposit(long amount)
    {
        balance+=amount;
    }

    // Withdraw without synchronization
    public boolean withdraw(long amount)
    {
        if(amount>0 && balance>=amount)
        {
            balance-=amount;
            return true;
        }

        return false;
    }

    public long getBalance()
    {
        return balance;
    }
}

// Worker that performs repeated deposits
class AccountWorker implements Runnable
{
    private Account account;
    private int times;
    private long amount;

    public AccountWorker(Account account,int times,long amount)
    {
        this.account=account;
        this.times=times;
        this.amount=amount;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName()+" started");

        for(int i=0;i<times;i++)
        {
            account.deposit(amount);
        }

        System.out.println(Thread.currentThread().getName()+" finished");
    }
}

public class MiniBank
{
    public static void main(String[] args) throws InterruptedException
    {
        Account account=new Account(0);

        int threadCount=10;
        int times=100000;
        long amount=1;

        Thread[] threads=new Thread[threadCount];

        // Create and start ten threads
        for(int i=0;i<threadCount;i++)
        {
            AccountWorker worker=new AccountWorker(account,times,amount);
            threads[i]=new Thread(worker,"Worker-"+(i+1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for(Thread thread:threads)
        {
            thread.join();
        }

        // Compare expected and actual balance
        long expectedBalance=(long)threadCount*times*amount;

        System.out.println("\nExpected Balance: "+expectedBalance);
        System.out.println("Actual Balance: "+account.getBalance());
    }
}