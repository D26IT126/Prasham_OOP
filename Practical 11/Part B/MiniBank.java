import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

// Main class for MiniBank Practical 11
public class MiniBank
{
    public static void main(String[] args)
    {
        try
        {
            // Define file locations
            Path dataFile=Paths.get("data","accounts.dat");
            Path logsFolder=Paths.get("logs");
            Path logFile=logsFolder.resolve("transactions.log");
            Path reportFile=Paths.get("reports","summary.txt");

            // Create customers
            Customer customer1=new Customer("Rahul","rahul@gmail.com");
            Customer customer2=new Customer("Amit","amit@gmail.com");

            // Create bank accounts
            Account[] accounts=
            {
                new SavingsAccount("AC0001",customer1,5000,4.5),
                new CurrentAccount("AC0002",customer2,10000,2000)
            };

            // Perform transactions and record them
            accounts[0].deposit(1500);
            TransactionLog.append(logFile,"DEPOSIT,AC0001,1500");

            accounts[1].withdraw(2000);
            TransactionLog.append(logFile,"WITHDRAWAL,AC0002,2000");

            accounts[0].deposit(500);
            TransactionLog.append(logFile,"DEPOSIT,AC0001,500");

            // Save account objects to a file
            StatePersister.save(accounts,dataFile);
            System.out.println("Accounts saved successfully.");

            // Load accounts into a new array
            Account[] loadedAccounts=StatePersister.load(dataFile);
            System.out.println("\nLoaded Account Details:");

            for(Account account:loadedAccounts)
            {
                System.out.println(account);
            }

            // Generate the end-of-day report
            ReportGenerator.generate(logsFolder,reportFile);

            // Read and print the generated report
            System.out.println("\nGenerated Report:");

            try(BufferedReader reader=Files.newBufferedReader(reportFile,StandardCharsets.UTF_8))
            {
                String line;

                while((line=reader.readLine())!=null)
                {
                    System.out.println(line);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("File error: "+e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Unable to load account data: "+e.getMessage());
        }
    }
}