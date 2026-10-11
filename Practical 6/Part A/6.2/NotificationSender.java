@FunctionalInterface
interface Notifier
{
    void send(String message);
}

//Marker/Maker Interface
interface Urgent
{

}

public class NotificationSender
{
    public static void main(String args[])
    {
        Notifier email= message -> System.out.println("Email: "+message);
        Notifier sms= (Notifier & Urgent) message -> System.out.println("SMS: "+message);

        Notifier senders[]= {email,sms};

        String message= "Hello, programmer!";
        String message_urgent="Hello, programmer! This is URGENT.";

        for(Notifier sender: senders)
        {
            sender.send(message);

            if(sender instanceof Urgent)
            {
                sender.send(message_urgent);
            }
        }
    }
}