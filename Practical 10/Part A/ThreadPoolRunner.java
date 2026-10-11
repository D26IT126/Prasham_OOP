import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner
{
    public static void main(String[] args)
    {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor=Executors.newFixedThreadPool(3);

        // Submit 10 tasks to the thread pool
        for(int i=1;i<=10;i++)
        {
            final int taskId=i;

            executor.submit(() ->
            {
                System.out.println("Task "+taskId+" started by "+Thread.currentThread().getName());

                try
                {
                    // Simulate task execution
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task "+taskId+" completed by "+Thread.currentThread().getName());
            });
        }

        // Stop accepting new tasks
        executor.shutdown();

        try
        {
            // Wait for all tasks to complete
            if(!executor.awaitTermination(30, TimeUnit.SECONDS))
            {
                executor.shutdownNow();
            }
        }
        catch(InterruptedException e)
        {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread pool execution finished.");
    }
}