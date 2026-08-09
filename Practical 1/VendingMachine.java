import java.util.Scanner;

public class VendingMachine
{
    enum Coin
    {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String args [])
    {
        final int Total=15;
        int Running_Total=0,change;
        Scanner sc = new Scanner(System.in);

        while(Running_Total<Total)
        {
            System.out.println("Enter a coin (words only):");
            Coin coin=Coin.valueOf(sc.next().toUpperCase());
            
            int value= switch(coin)
            {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            Running_Total+=value;

            System.out.println("Total so far:"+Running_Total);
        }

        change=Running_Total-Total;
        System.out.println("Paid. Change:"+change);
        sc.close();
    }
}