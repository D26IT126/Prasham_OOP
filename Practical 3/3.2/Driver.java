import java.util.Scanner;

public class Driver
{
    public static void main(String args [])
    {
        int count=0;
        String rank,suit;
        Card c[]= new Card[5];
        Scanner sc= new Scanner(System.in);

        for(int i=0;i<5;i++)
        {
            System.out.println("Enter the rank: ");
            rank=sc.next();
            System.out.println("Enter the suit: ");
            suit=sc.next();

            c[i]= new Card(rank, suit);
            if(count==0)
            {
                count++;
                continue;
            }

            if(c[i].equals(c[i-1]))
            {
                System.out.println("Duplicate found: "+c[i].toString());
                break;
            }
            count++;
        }
        sc.close();
    }
}