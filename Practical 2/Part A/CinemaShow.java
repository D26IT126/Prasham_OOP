public class CinemaShow
{
    @SuppressWarnings("unused")
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked=0;

    public CinemaShow(String title, int capacity)
    {
        this.title=title;
        this.seatsAvailable=capacity;
        this.capacity=capacity;
    }

    public CinemaShow(String title)
    {
        this(title,100);
    }

    public boolean book(int n)
    {
        if(n<=seatsAvailable)
        {
            seatsAvailable-=n;
            totalBooked+=n;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void cancel(int n)
    {
        if(seatsAvailable<=capacity)
        {
            seatsAvailable+=n;
            totalBooked-=n;        
        }
    }

    public int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    public static int getTotalBooked()
    {
        return totalBooked;
    }
}

class Test
{
    public static void main(String args[])
    {
        CinemaShow Screen_1= new CinemaShow("SpiderMan: Brand New Day", 10);
        CinemaShow Screen_2= new CinemaShow("The Odyssey");

        System.out.println("Screen 1: SpiderMan: Brand New Day");
        System.out.println("Total Available Seats: "+Screen_1.getSeatsAvailable());
        System.out.println("Book: 6 | "+Screen_1.book(6));
        System.out.println("Seats Available After 1st Booking: "+Screen_1.getSeatsAvailable());

        System.out.println("Book 8: | "+Screen_1.book(8)+"\nSorry! Insufficient Seats.");
        System.out.println("Total Available Seats: "+Screen_1.getSeatsAvailable());

        System.out.println("Cancelled: 1");Screen_1.cancel(1);

        System.out.println("\nScreen 2: The Odyssey");
        System.out.println("Total Available Seats: "+Screen_2.getSeatsAvailable());
        System.out.println("Book: 10 | "+Screen_2.book(10));
        System.out.println("Seats Available After 1st Booking: "+Screen_2.getSeatsAvailable());

        System.out.println("Number of Seats Booked: "+CinemaShow.getTotalBooked());
    }
}