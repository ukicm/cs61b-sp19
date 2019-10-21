package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTopOnly;
    private int numOpenSites = 0;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new boolean[N][N];
        size = N;
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufTopOnly = new WeightedQuickUnionUF(N * N + 1);
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
        }
    }

    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    private void validate (int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        validate(row, col);
        return ufTopOnly.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numOpenSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return uf.connected(bottom,top);
    }

    public static void main(String[] args) {
        // use for unit testing (not required, but keep this here for the autograder)
    }
}
