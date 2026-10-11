import java.io.*;
import java.nio.file.*;

// Save and load bank accounts using serialization
public class StatePersister
{
    // Save all accounts to a file
    public static void save(Account[] accounts,Path file) throws IOException
    {
        // Create the parent directory if necessary
        if(file.getParent()!=null)
        {
            Files.createDirectories(file.getParent());
        }

        // Write the account array to the file
        try(ObjectOutputStream output=new ObjectOutputStream(Files.newOutputStream(file)))
        {
            output.writeObject(accounts);
        }
    }

    // Load all accounts from a file
    public static Account[] load(Path file) throws IOException,ClassNotFoundException
    {
        // Read the account array from the file
        try(ObjectInputStream input=new ObjectInputStream(Files.newInputStream(file)))
        {
            return (Account[])input.readObject();
        }
    }
}