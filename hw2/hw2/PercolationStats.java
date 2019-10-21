package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] threshold;
    private double threshold_sum;
    private int numTrials;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        numTrials = T;
        threshold = new double[numTrials];

        for (int i = 0; i < numTrials; i ++) {
            int openNums = 0;
            Percolation new_per = pf.make(N);

            while (!new_per.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                if (!new_per.isOpen(row, col)) {
                    new_per.open(row, col);
                    openNums += 1;
                }
            }
            threshold[i] = (double) openNums / (N * N);
            threshold_sum += threshold[i];
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return threshold_sum / numTrials;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        double dev_sum = 0;
        double dev_squ;
        for (int i = 0; i < numTrials; i += 1) {
            dev_sum += Math.pow((threshold[i] - mean()), 2);
        }
        dev_squ = dev_sum / (numTrials - 1);
        return Math.sqrt(dev_squ);
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(numTrials);
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(numTrials);
    }

    public static void main(String[] args) {
        int N = 100;
        int T = 10;
        PercolationStats newpercol = new PercolationStats(N, T, new PercolationFactory());
        System.out.println("The confidenceLow threshold of " + "a " + N + " * " + N + " square in " + T + " times experiments is " + newpercol.confidenceLow());
        System.out.println("The confidenceHigh threshold of " + "a " + N + " * " + N + " square in " + T + " times experiments is " + newpercol.confidenceHigh());
    }
}