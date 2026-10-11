import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer
{
    private Queue<Integer> buffer=new LinkedList<>();
    private final int capacity=3;

    // Add an item to the buffer
    public synchronized void produce(int item) throws InterruptedException
    {
        while(buffer.size()==capacity)
        {
            wait();
        }

        buffer.add(item);
        System.out.println("Produced: "+item);

        notify();
    }

    // Remove an item from the buffer
    public synchronized int consume() throws InterruptedException
    {
        while(buffer.isEmpty())
        {
            wait();
        }

        int item=buffer.remove();
        System.out.println("Consumed: "+item);

        notify();
        return item;
    }
}

public class ProducerConsumer
{
    public static void main(String[] args)
    {
        SharedBuffer buffer=new SharedBuffer();

        // Producer thread
        Thread producer=new Thread(() ->
        {
            try
            {
                for(int i=1;i<=10;i++)
                {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer=new Thread(() ->
        {
            try
            {
                for(int i=1;i<=10;i++)
                {
                    buffer.consume();
                    Thread.sleep(150);
                }
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        // Wait for both threads to finish
        try
        {
            producer.join();
            consumer.join();
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Producer-Consumer execution finished.");
    }
}