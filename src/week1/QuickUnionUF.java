package week1;

// import analysis_algo.Stopwatch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement quick union in Union find
 */
public class QuickUnionUF {
    private int[] id;

    /**
     * Initialize an empty union-find data structure with
     * {@code n} element through {@code n - 1}.
     * Initially, each element is in its own set.
     * 
     * @param n The number of element
     * @throws IllegalArgumentException if {@code n < 1}
     */
    public QuickUnionUF(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * Check the root id for element.
     * 
     * @param element Element to check the root id
     * @return The root id
     */
    private int root(int element) {
        if (element >= id.length || element < 0)
            throw new IndexOutOfBoundsException("Number must be between 0 to " + (id.length - 1));

        while (element != id[element]) {
            element = id[element];
        }
        return element;
    }

    /**
     * 
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        if (p >= id.length || p < 0)
            throw new IndexOutOfBoundsException("Number p=" + p + " must be between 0 to " + (id.length - 1));
        if (q >= id.length || q < 0)
            throw new IndexOutOfBoundsException("Number q=" + q + " must be between 0 to " + (id.length - 1));

        return root(p) == root(q);
    }

    /**
     * 
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        if (p >= id.length || p < 0)
            throw new IndexOutOfBoundsException("Number p=" + p + " must be between 0 to " + (id.length - 1));
        if (q >= id.length || q < 0)
            throw new IndexOutOfBoundsException("Number q=" + q + " must be between 0 to " + (id.length - 1));

        int rootP = root(p);
        int rootQ = root(q);
        if (rootP != rootQ) {
            id[rootP] = rootQ;
        }
    }

    public static void main(String[] args) {
        int input = StdIn.readInt();
        StdOut.println(input);

        QuickUnionUF qu = new QuickUnionUF(10);
        qu.union(4, 3);
        System.out.println(qu.connected(4, 3));
        qu.union(3, 8);
        System.out.println(qu.connected(3, 8));
        System.out.println(qu.connected(4, 8));
        qu.union(6, 5);
        System.out.println(qu.connected(6, 5));
        qu.union(9, 4);
        qu.union(2, 1);
        qu.union(5, 0);
        qu.union(7, 2);
        qu.union(6, 1);
        qu.union(7, 3);
        System.out.println(qu.connected(6, 9));
    }
}
