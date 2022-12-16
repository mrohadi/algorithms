package weekone.unionfind.percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridSize;
    private int gridSquare;
    private int virtualTop;
    private int virtualBot;
    private int openSite;
    private boolean[][] grid;
    private WeightedQuickUnionUF wquGrid;
    private WeightedQuickUnionUF wquFull;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Argument must be positive number");

        gridSize = n;
        gridSquare = n * n;
        virtualTop = gridSquare;
        virtualBot = gridSquare + 1;
        openSite = 0;
        grid = new boolean[gridSize][gridSize];
        wquGrid = new WeightedQuickUnionUF(gridSquare + 2);
        wquFull = new WeightedQuickUnionUF(gridSquare + 1);
    }

    public void open(int row, int col) {
        validate(row, col);

        int shiftRow = row - 1;
        int shiftCol = col - 1;
        int site = getSite(row, col);

        // Check whether the site is alerady open
        if (isOpen(row, col)) {
            return;
        }

        // Open the site
        grid[shiftRow][shiftCol] = true;
        openSite++;

        // Check top row and union to virtual top
        if (row == 1) {
            wquGrid.union(virtualTop, site);
            wquFull.union(virtualTop, site);
        }

        if (row == gridSize) {
            wquGrid.union(virtualBot, site);
        }

        // Check left and union
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) {
            wquGrid.union(site, getSite(row, col - 1));
            wquFull.union(site, getSite(row, col - 1));
        }

        // Check right and union
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
            wquGrid.union(site, getSite(row, col + 1));
            wquFull.union(site, getSite(row, col + 1));
        }

        // Check up and union
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
            wquGrid.union(site, getSite(row - 1, col));
            wquFull.union(site, getSite(row - 1, col));
        }

        // Check down and union
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
            wquGrid.union(site, getSite(row + 1, col));
            wquFull.union(site, getSite(row + 1, col));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return wquFull.find(virtualTop) == wquFull.find(getSite(row, col));
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        return wquGrid.find(virtualTop) == wquGrid.find(virtualBot);
    }

    private void validate(int row, int col) {
        if (!isOnGrid(row, col))
            throw new IllegalArgumentException("Number out of bonds");
    }

    private boolean isOnGrid(int row, int col) {
        int shiftRow = row - 1;
        int shiftCol = col - 1;
        return (shiftRow >= 0 && shiftRow < gridSize && shiftCol >= 0 && shiftCol < gridSize);
    }

    private int getSite(int row, int col) {
        validate(row, col);
        return (gridSize * (row - 1) + col) - 1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int row = 0;
        int col = 0;

        int numOpenSite = 1;
        int testRow = 1;
        int testCol = 1;
        if (args.length > 0) {
            numOpenSite = Integer.parseInt(args[0]);
            testRow = Integer.parseInt(args[1]);
            testCol = Integer.parseInt(args[2]);
        }

        Percolation perc = new Percolation(n);
        while (!StdIn.isEmpty()) {
            row = StdIn.readInt();
            col = StdIn.readInt();
            perc.open(row, col);

            if (perc.numberOfOpenSites() == numOpenSite) {
                StdOut.println("Is full: " + perc.isFull(testRow, testCol));
                StdOut.println("Is percolate: " + perc.percolates());
            }
        }
    }
}
