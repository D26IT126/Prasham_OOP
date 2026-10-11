public class TransactionBuffer
{
    private Runnable[] buffer=new Runnable[5];
    private int count=0;

    // Add a transaction to the buffer
    public synchronized void produce(Runnable task) throws InterruptedException
    {
        // Wait if the buffer is full
        while(count==buffer.length)
        {
            wait();
        }

        buffer[count]=task;
        count++;

        System.out.println("Transaction added. Buffer size: "+count);

        // Notify the waiting consumer
        notify();
    }

    // Remove a transaction from the buffer
    public synchronized Runnable consume() throws InterruptedException
    {
        // Wait if the buffer is empty
        while(count==0)
        {
            wait();
        }

        count--;
        Runnable task=buffer[count];
        buffer[count]=null;

        System.out.println("Transaction removed. Buffer size: "+count);

        // Notify the waiting producer
        notify();

        return task;
    }
}