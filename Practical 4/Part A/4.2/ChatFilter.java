public class ChatFilter
{

    private String[] logs;
    private String keyword;

    public ChatFilter(String[] logs, String keyword)
    {
        this.logs = logs;
        this.keyword = (keyword != null) ? keyword.toLowerCase() : "";
    }

    public String filterLogs()
    {
        int matches = 0;
        StringBuilder report = new StringBuilder();

        for (String line : logs)
        {
            String[] parts = line.split(" ", 3);

            if (parts.length < 3)
            {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword))
            {
                matches++;
                report.append(time).append(" ").append(user).append(": ").append(message).append("\n");
            }
        }

        return "Matches: " + matches + "\n" + report.toString();
    }
}