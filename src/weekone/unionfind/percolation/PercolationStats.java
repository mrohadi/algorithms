package weekone.unionfind.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int gridSize;
    private int trials;
    private double[] trialsResult;

    // Perform independent trials on an n by n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Number must be positive integer");
        }

        gridSize = n;
        this.trials = trials;
        trialsResult = new double[trials];

        runExperiment();
    }

    // Run the experiment based on number of trials
    private void runExperiment() {
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(gridSize);
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, gridSize + 1);
                int col = StdRandom.uniformInt(1, gridSize + 1);
                perc.open(row, col);
            }
            int openSite = perc.numberOfOpenSites();
            double result = (double) openSite / (gridSize * gridSize);
            trialsResult[i] = result;
        }
    }

    // Sample mean of percolation <em>threshold</em>
    public double mean() {
        return StdStats.mean(trialsResult);
    }

    // Determine standard deviation of percolation <em>threshold</em>
    public double stddev() {
        return StdStats.stddev(trialsResult);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        int n = 200;
        int trials = 100;
        if (args.length > 1) {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }

        // Stopwatch stopwatch = new Stopwatch();
        PercolationStats percStats = new PercolationStats(n, trials);
        StdOut.printf("mean                     = %.7f%n", percStats.mean());
        StdOut.printf("stddev                   = %.17f%n", percStats.stddev());
        StdOut.printf("95%% confidence interval  = [%.15f, %.15f]%n",
                percStats.confidenceLo(), percStats.confidenceHi());

        // double timeElapse = stopwatch.elapsedTime();
        // StdOut.printf("Total time elapse: %.9f milisecond)", timeElapse);
    }
}
