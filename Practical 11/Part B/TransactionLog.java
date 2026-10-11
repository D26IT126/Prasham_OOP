import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

// Append transactions to a log file
public class TransactionLog
{
    public static void append(Path file,String line) throws IOException
    {
        // Create the logs directory if necessary
        if(file.getParent()!=null)
        {
            Files.createDirectories(file.getParent());
        }

        // Append the transaction without removing existing records
        Files.write(
            file,
            (line+System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
}