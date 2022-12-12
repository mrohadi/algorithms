package analysis_algo;

import edu.princeton.cs.algs4.StdOut;

public class Stopwatch {
    private final long start;

    /**
     * Initialize a new stopwatch
     */
    public Stopwatch() {
        start = System.nanoTime();
    }

    /**
     * 
     * @return
     */
    public double elapsedTime() {
        long now = System.nanoTime();
        return (now - start) / 1000000000.00;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Stopwatch stopwatch = new Stopwatch();
        double totalSum = 0.0;
        for (int i = 0; i <= n; i++) {
            totalSum += Math.sqrt(i);
        }
        double time1 = stopwatch.elapsedTime();
        StdOut.printf("%e (%.9f seconds)", totalSum, time1);
    }
}
 