package weekone.unionfind;

import analysis_algo.Stopwatch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] size;

    private int count;

    /**
     * Initialize an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n - 1}.
     * Initially, each element is in its own set.
     * 
     * @param n number element to initialize
     * @throws IllegalArgumentException when {@code n element < 0}.
     */
    public WeightedQuickUnionUF(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Number must be greater than 0");

        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of sets
     * 
     * @return the number of sets (between {@code 1} to {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * 
     * @param element
     * @return Index find of {@code element}
     * @throws IndexOutOfBoundsException if
     *                                   {@code element >= array length or element < 0}
     */
    public int find(int element) {
        validate(element);
        while (element != id[element]) {
            id[element] = id[id[element]];
            element = id[element];
        }
        return element;
    }

    /**
     * Check whether {@code element p} and {@code elment q} have the same root.
     * 
     * @param p an element p
     * @param q an element q
     * @return {@code true} if {@code element p} and {@code element q} have the same
     *         root, otherwise {@code false}
     * @throws IllegalArgumentException when {@code 0 < p >= n} or
     *                                  {@code 0 < q >= n}
     */
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    /**
     * 
     * @param index
     */
    private void validate(int index) {
        if (index >= id.length || index < 0)
            throw new IllegalArgumentException("Index " + index + " is not between 0 to " + (id.length - 1));
    }

    /**
     * 
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        validate(p);
        validate(q);

        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int n = StdIn.readInt();

        WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            wqu.union(p, q);
        }

        StdOut.printf("%d - %d - %dn", wqu.find(3), wqu.find(1), wqu.find(9));
        ;
        System.out.println();

        double timeElapsed = stopwatch.elapsedTime();
        StdOut.println(timeElapsed);
    }
}
