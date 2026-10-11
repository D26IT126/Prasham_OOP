import java.util.Scanner;

public class Driver
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String[] tempLogs = new String[100];
        int count = 0;

        System.out.println("Enter chat log lines (press Enter on an empty line to finish):");

        while (count < tempLogs.length)
        {
            String line = scanner.nextLine();

            if (line.trim().isEmpty())
            {
                break;
            }
            tempLogs[count] = line;
            count++;
        }

        // Copy elements to an array of exact size
        String[] logs = new String[count];
        System.arraycopy(tempLogs, 0, logs, 0, count);

        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        // Instantiate ChatFilter using constructor
        ChatFilter filter = new ChatFilter(logs, keyword);

        // Print final report
        System.out.println("\n--- Filter Output ---");
        System.out.print(filter.filterLogs());

        scanner.close();
    }
}