import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

interface Booking {
    boolean book();          
    int getSeatsLeft();
}

// No synchronization: check-then-act race, so several threads can pass the check
class UnsafeBooking implements Booking {
    private int seatsLeft;

    UnsafeBooking(int seats) { this.seatsLeft = seats; }

    @Override
    public boolean book() {
        if (seatsLeft > 0) {                  
            try {
                Thread.sleep(5);              
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            seatsLeft--;                      
            return true;
        }
        return false;
    }

    @Override
    public int getSeatsLeft() { return seatsLeft; }
}

// synchronized: check and decrement happen as one atomic step
class SafeBooking implements Booking {
    private int seatsLeft;

    SafeBooking(int seats) { this.seatsLeft = seats; }

    @Override
    public synchronized boolean book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(5);              // same delay, but now harmless
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            seatsLeft--;
            return true;
        }
        return false;
    }

    @Override
    public synchronized int getSeatsLeft() { return seatsLeft; }
}

public class SeatBooking {

    static final int SEATS = 5;
    static final int THREADS = 10;
    static final int RUNS = 5;

    // Returns how many bookings succeeded
    static int runTest(Booking booking) throws InterruptedException {
        AtomicInteger successes = new AtomicInteger(0);
        CountDownLatch startSignal = new CountDownLatch(1);
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                try {
                    startSignal.await();      // all threads wait, then start together
                } catch (InterruptedException e) {
                    return;
                }
                if (booking.book()) {
                    successes.incrementAndGet();
                }
            });
            threads[i].start();
        }

        startSignal.countDown();              // release all threads at once
        for (Thread t : threads) t.join();
        return successes.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Seats: " + SEATS + ", threads (customers): " + THREADS + "\n");

        System.out.println("--- WITHOUT synchronization ---");
        for (int run = 1; run <= RUNS; run++) {
            UnsafeBooking b = new UnsafeBooking(SEATS);
            int booked = runTest(b);
            System.out.printf("Run %d: %d bookings succeeded, seatsLeft = %d %s%n",
                    run, booked, b.getSeatsLeft(),
                    booked > SEATS ? "<-- OVERSOLD" : "");
        }

        System.out.println("\n--- WITH synchronized book() ---");
        boolean allCorrect = true;
        for (int run = 1; run <= RUNS; run++) {
            SafeBooking b = new SafeBooking(SEATS);
            int booked = runTest(b);
            boolean ok = (booked == SEATS && b.getSeatsLeft() == 0);
            allCorrect &= ok;
            System.out.printf("Run %d: %d bookings succeeded, seatsLeft = %d %s%n",
                    run, booked, b.getSeatsLeft(), ok ? "OK" : "<-- WRONG");
        }

        System.out.println("\nExactly " + SEATS + " booked in every synchronized run: " + allCorrect);
    }
}
