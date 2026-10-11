import java.util.concurrent.*;

// Manage banking transactions using a thread pool
public class TransactionProcessor
{
    private ExecutorService executor=Executors.newFixedThreadPool(4);

    // Submit a transaction for execution
    public void submit(Runnable task)
    {
        executor.execute(task);
    }

    // Stop accepting tasks and wait for completion
    public void stop()
    {
        executor.shutdown();

        try
        {
            if(!executor.awaitTermination(60,TimeUnit.SECONDS))
            {
                System.out.println("Some transactions are still running.");
            }
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Transaction processing interrupted.");
        }
    }
}