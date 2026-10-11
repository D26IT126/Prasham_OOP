import java.util.Scanner;

record Vehicle(String v_num, String v_type) {}

public class TollBooth
{
    public static void main(String args[])
    {
        long running_toll_total=0, c_bike=0, c_car=0, c_truck=0;
        String frequent;
        Scanner sc=new Scanner(System.in);

        while(true)
        {
            System.out.println("Enter vehicle number:");
            String num=sc.next();

            if(num.equals("done"))
            {
                break;
            }

            System.out.println("Enter vehicle type (bike/car/truck):");
            String type=sc.next().toLowerCase();

            Vehicle vehicle=new Vehicle(num,type);

            int toll=switch(vehicle.v_type())
            {
                case "bike" ->
                {
                    c_bike++;
                    yield 20;
                }

                case "car" ->
                {
                    c_car++;
                    yield 50;
                }

                case "truck" ->
                {
                    c_truck++;
                    yield 100;
                }

                default ->
                {
                    System.out.println("Invalid Vehicle Type. Enter Again.");
                    yield 0;
                }
            };

            running_toll_total+=toll;
        }

        if(c_bike>=c_car && c_bike>=c_truck)
        {
            frequent="bike";
        }
        else if(c_car>=c_bike && c_car>=c_truck)
        {
            frequent="car";
        }
        else
        {
            frequent="truck";
        }
        System.out.println("Total Toll: "+running_toll_total);
        System.out.println("Frequent: "+frequent);
        sc.close();
    }
}