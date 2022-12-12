package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;

    /**
     * Initialize an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     * @param n the number of element
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public QuickFindUF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * Merge components containing {@code p} and {@code q}, and after that 
     * change all object entries from {@code id[p]} to {@code id[q]}
     * @param p Component to merge from
     * @param q Component to merge to
     */
    void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }

    /**
     * Check whether {@code component p} and {@code component q} are connected.
     * In other word they have the same entry id
     * @param p Component {@code p}
     * @param q Component {@code q}
     * @return {@code True} if component {@code p} and {@code q} have the same entry id,
     * otherwise {@code False}
     */
    boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * Find the entry in {@code id[]} array for a given component
     * @param p Component to find
     * @return Entry or index of given component in {@code id[]} array
     */
    int find(int p) {
        return id[p];
    }

    /**
     * Print to standard output entry for every component 
     * in {@code id[]} array
     */
    void getInfoId() {
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();      
        StdOut.println(n);

        QuickFindUF uf = new QuickFindUF(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            uf.union(p, q);

            StdOut.println(p + " " + q);
        }

        uf.getInfoId();
    }
}
