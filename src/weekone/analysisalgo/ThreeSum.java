package weekone.analysisalgo;

import analysis_algo.Stopwatch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSum {
    private ThreeSum() {
    }

    public static int count(int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] input = StdIn.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(ThreeSum.count(input));
        StdOut.println(stopwatch.elapsedTime());
    }
}
