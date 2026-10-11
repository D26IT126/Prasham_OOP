public class MiniBank
{
    public static void main(String[] args) throws InterruptedException
    {
        // Create two bank accounts
        Account account1=new Account(1001,10000);
        Account account2=new Account(1002,10000);

        // Part 1: ExecutorService
        System.out.println("=== Thread Pool Demonstration ===");

        TransactionProcessor processor=new TransactionProcessor();

        for(int i=0;i<10;i++)
        {
            final int transactionNumber=i+1;

            processor.submit(() ->
            {
                account1.deposit(500);

                System.out.println(Thread.currentThread().getName()+
                                   " deposited Rs. 500 (Transaction "+transactionNumber+")");
            });

            processor.submit(() ->
            {
                account1.withdraw(200);

                System.out.println(Thread.currentThread().getName()+
                                   " withdrew Rs. 200");
            });
        }

        processor.stop();

        System.out.println("Balance after thread pool: Rs. "+account1.getBalance());

        // Part 2: Producer-Consumer
        System.out.println("\n=== Producer-Consumer Demonstration ===");

        TransactionBuffer buffer=new TransactionBuffer();

        Thread producer=new Thread(() ->
        {
            try
            {
                for(int i=0;i<5;i++)
                {
                    buffer.produce(() -> account1.deposit(100));
                }
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        },"Producer");

        Thread consumer=new Thread(() ->
        {
            try
            {
                for(int i=0;i<5;i++)
                {
                    Runnable task=buffer.consume();
                    task.run();

                    System.out.println(Thread.currentThread().getName()+
                                       " processed a transaction");
                }
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        },"Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Balance after producer-consumer: Rs. "+account1.getBalance());

        // Part 3: Deadlock Demonstration
        System.out.println("\n=== Deadlock Demonstration ===");

        Thread thread1=new Thread(() ->
        {
            synchronized(account1)
            {
                System.out.println("Thread 1 locked Account 1001");

                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }

                synchronized(account2)
                {
                    account1.transfer(account2,500,false);
                }
            }
        },"Thread-1");

        Thread thread2=new Thread(() ->
        {
            synchronized(account2)
            {
                System.out.println("Thread 2 locked Account 1002");

                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }

                synchronized(account1)
                {
                    account2.transfer(account1,500,false);
                }
            }
        },"Thread-2");

        // Daemon threads allow the program to exit despite deadlock
        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        Thread.sleep(500);

        if(thread1.isAlive() && thread2.isAlive())
        {
            System.out.println("Deadlock detected: Both threads are waiting.");
        }

        // Part 4: Deadlock Prevention
        System.out.println("\n=== Deadlock Prevention ===");

        // Use separate accounts because the previous ones may be deadlocked
        Account safe1=new Account(2001,5000);
        Account safe2=new Account(2002,5000);

        Thread safeThread1=new Thread(() ->
        {
            safe1.transfer(safe2,500,true);
            System.out.println("Safe transfer 1 completed.");
        });

        Thread safeThread2=new Thread(() ->
        {
            safe2.transfer(safe1,300,true);
            System.out.println("Safe transfer 2 completed.");
        });

        safeThread1.start();
        safeThread2.start();

        safeThread1.join();
        safeThread2.join();

        System.out.println("Account 2001 Balance: Rs. "+safe1.getBalance());
        System.out.println("Account 2002 Balance: Rs. "+safe2.getBalance());

        System.out.println("\nPractical 10 completed.");
    }
}