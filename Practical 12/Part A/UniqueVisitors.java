import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueVisitors
{
    public static void main(String[] args)
    {
        // List of login names with repeats
        List<String> logins = Arrays.asList("alice", "bob", "alice", "charlie", "david", "bob", "eve");

        // Use HashSet to count unique users
        Set<String> uniqueSet = new HashSet<>(logins);

        // Use LinkedHashSet to preserve first-seen order
        Set<String> orderedSet = new LinkedHashSet<>(logins);

        // Print unique count
        System.out.println("Unique count: " + uniqueSet.size());

        // Print first three distinct names
        System.out.println("First three distinct names:");
        int count = 0;
        for(String name : orderedSet)
        {
            if(count < 3)
            {
                System.out.println(name);
                count++;
            }
            else
            {
                break;
            }
        }
    }
}