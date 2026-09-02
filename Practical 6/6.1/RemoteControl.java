interface Switchable
{
    void on();
    void off();

    default void toggle()
    {
        on();
        off();
    }
}

class Fan implements Switchable
{
    public void on()
    {
        System.out.println("Fan is on.");
    }

    public void off()
    {
        System.out.println("Fan is off.");
    }
}

class Light implements Switchable
{
    public void on()
    {
        System.out.println("Light is on.");
    }

    public void off()
    {
        System.out.println("Light is off.");
    }
}

@FunctionalInterface
interface Permission
{
    boolean maySwitchOn(Switchable device, int hour);
}


public class RemoteControl 
{
    public static void main(String args[])
    {
        Switchable devices[]=
        {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling Devices:");

        for(Switchable device: devices)
        {
            device.toggle();
        }

        //Anonymous Class
        Permission p1= new Permission()
        {
            public boolean maySwitchOn(Switchable device, int hour)
            {
                return hour>=6 && hour<=22;
            }
        };

        System.out.println("\nAnonymous Class:");
        System.out.println(p1.maySwitchOn(devices[0], 15));

        //Lambda Expression
        Permission p2= (device, hour) -> hour>=6 && hour<=22;

        System.out.println("\nLambda Expression:");
        System.out.println(p2.maySwitchOn(devices[1], 23));
    }
}