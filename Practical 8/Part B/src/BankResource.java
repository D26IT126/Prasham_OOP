// Resource for demonstrating try-with-resources
public class BankResource implements AutoCloseable
{
    public BankResource()
    {
        System.out.println("Bank resource opened.");
    }

    public void showMessage()
    {
        System.out.println("Bank resource is being used.");
    }

    @Override
    public void close()
    {
        System.out.println("Bank resource closed automatically.");
    }
}