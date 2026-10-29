import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency
{
    public static void main(String[] args)
    {
        String paragraph = "Java is a programming language. Java is widely used, and Java is versatile.";

        // Split paragraph into words
        String[] words = paragraph.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");

        // Count frequencies in a HashMap
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(String word : words)
        {
            if(!word.isEmpty())
            {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Convert map entries to list for sorting
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

        // Sort by frequency (highest first) using a Comparator
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2)
            {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        // Print sorted words and frequencies
        for(Map.Entry<String, Integer> entry : entryList)
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}