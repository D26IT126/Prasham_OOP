import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class LogAnalyzer
{
    public static void main(String[] args)
    {
        String keyword="ERROR";
        int totalLines=0;
        int keywordCount=0;

        Path[] files={
            Paths.get("log1.txt"),
            Paths.get("log2.txt"),
            Paths.get("log3.txt")
        };

        // Create sample log files
        List<String> log1=Arrays.asList(
            "INFO: Application started",
            "ERROR: Database connection failed",
            "INFO: Connection restored"
        );

        List<String> log2=Arrays.asList(
            "WARNING: Low memory",
            "ERROR: File not found",
            "ERROR: Access denied"
        );

        List<String> log3=Arrays.asList(
            "INFO: User logged in",
            "WARNING: Slow response",
            "ERROR: Server timeout"
        );

        try
        {
            Files.write(files[0], log1);
            Files.write(files[1], log2);
            Files.write(files[2], log3);

            System.out.println("Log files created successfully.\n");

            // Analyze each file
            for(Path file:files)
            {
                int fileLines=0;
                int fileKeywordCount=0;

                try(BufferedReader reader=Files.newBufferedReader(file))
                {
                    String line;

                    while((line=reader.readLine())!=null)
                    {
                        fileLines++;

                        if(line.contains(keyword))
                        {
                            fileKeywordCount++;
                        }
                    }
                }

                // Read file attributes to get its size
                BasicFileAttributes attributes=Files.readAttributes(file, BasicFileAttributes.class);

                System.out.println("File: "+file.getFileName());
                System.out.println("Size: "+attributes.size()+" bytes");
                System.out.println("Total Lines: "+fileLines);
                System.out.println("Lines containing "+keyword+": "+fileKeywordCount);
                System.out.println();

                totalLines+=fileLines;
                keywordCount+=fileKeywordCount;
            }

            System.out.println("Overall Summary:");
            System.out.println("Total Lines: "+totalLines);
            System.out.println("Total Lines containing "+keyword+": "+keywordCount);
        }
        catch(IOException e)
        {
            System.out.println("File Error: "+e.getMessage());
        }
    }
}