// Shared counter interface so both versions can use the same test harness
interface Counter {
    void increment();
    int get();
}

// No synchronization: count++ is read-modify-write, so updates get lost
class UnsafeCounter implements Counter {
    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}

// synchronized makes each increment atomic (one thread at a time)
class SafeCounter implements Counter {
    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public synchronized int get() {
        return count;
    }
}

public class CounterRace {

    static final int THREADS = 8;
    static final int INCREMENTS_PER_THREAD = 200_000;
    static final int RUNS = 5;

    // Runs THREADS threads, each incrementing the counter many times
    static int runTest(Counter counter) throws InterruptedException {
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join(); // wait for all threads before reading the total
        }
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int expected = THREADS * INCREMENTS_PER_THREAD;
        System.out.println("Threads: " + THREADS + ", increments each: " + INCREMENTS_PER_THREAD);
        System.out.println("Expected total: " + expected + "\n");

        System.out.println("--- WITHOUT synchronization ---");
        for (int run = 1; run <= RUNS; run++) {
            int actual = runTest(new UnsafeCounter());
            System.out.printf("Run %d: got %d (lost %d) %s%n",
                    run, actual, expected - actual,
                    actual == expected ? "" : "<-- WRONG");
        }

        System.out.println("\n--- WITH synchronized ---");
        boolean allCorrect = true;
        for (int run = 1; run <= RUNS; run++) {
            int actual = runTest(new SafeCounter());
            boolean ok = (actual == expected);
            allCorrect &= ok;
            System.out.printf("Run %d: got %d %s%n", run, actual, ok ? "OK" : "<-- WRONG");
        }

        System.out.println("\nSynchronized version correct in all runs: " + allCorrect);
    }
}