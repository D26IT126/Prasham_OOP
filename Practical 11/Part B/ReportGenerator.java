import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.charset.StandardCharsets;

// Generate a summary report from transaction logs
public class ReportGenerator
{
    public static void generate(Path logsFolder,Path reportFile) throws IOException
    {
        long totalDeposits=0;
        long totalWithdrawals=0;

        StringBuilder report=new StringBuilder();

        report.append("MINIBANK END-OF-DAY REPORT\n");
        report.append("==========================\n\n");

        // Create the logs folder if it does not exist
        Files.createDirectories(logsFolder);

        // Read all files inside the logs folder
        try(DirectoryStream<Path> files=Files.newDirectoryStream(logsFolder))
        {
            for(Path file:files)
            {
                if(!Files.isRegularFile(file))
                {
                    continue;
                }

                // Read file size and last modification time
                BasicFileAttributes attributes=Files.readAttributes(
                    file,BasicFileAttributes.class
                );

                report.append("File: ").append(file.getFileName()).append("\n");
                report.append("Size: ").append(attributes.size()).append(" bytes\n");
                report.append("Last Modified: ").append(attributes.lastModifiedTime()).append("\n\n");

                // Read the transaction log line by line
                try(BufferedReader reader=Files.newBufferedReader(file,StandardCharsets.UTF_8))
                {
                    String line;

                    while((line=reader.readLine())!=null)
                    {
                        String[] parts=line.split(",");

                        if(parts.length!=3)
                        {
                            continue;
                        }

                        try
                        {
                            long amount=Long.parseLong(parts[2].trim());

                            if(parts[0].trim().equalsIgnoreCase("DEPOSIT"))
                            {
                                totalDeposits+=amount;
                            }
                            else if(parts[0].trim().equalsIgnoreCase("WITHDRAWAL"))
                            {
                                totalWithdrawals+=amount;
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Skipping invalid log entry: "+line);
                        }
                    }
                }
            }
        }

        // Add transaction totals to the report
        report.append("TRANSACTION SUMMARY\n");
        report.append("-------------------\n");
        report.append("Total Deposits: Rs. ").append(totalDeposits).append("\n");
        report.append("Total Withdrawals: Rs. ").append(totalWithdrawals).append("\n");
        report.append("Net Amount: Rs. ").append(totalDeposits-totalWithdrawals).append("\n");

        // Create the report directory if necessary
        if(reportFile.getParent()!=null)
        {
            Files.createDirectories(reportFile.getParent());
        }

        // Save the generated report
        Files.write(reportFile,report.toString().getBytes(StandardCharsets.UTF_8));

        System.out.println("Report generated successfully.");
    }
}