/*
QuickFindUF
Time for calculating PercolationStats :119.189 
N = 200 
T = 100 
mean = 0.5926567500000001 
stddev = 0.010207787465306503 
confidenceLo = 0.5906560236568 
confidenceHi = 0.5946574763432001
WeightedQuickUnionUF
Time for calculating PercolationStats :0.924 
N = 200 
T = 100 
mean = 0.59415225 
stddev = 0.00948017808123983 
confidenceLo = 0.5922941350960771 
confidenceHi = 0.596010364903923 

QuickFindUF
Time for calculating PercolationStats :0.021 
N = 2 
T = 10000 
mean = 0.665775 
stddev = 0.11816850576433076 
confidenceLo = 0.6634588972870191 
confidenceHi = 0.6680911027129809
WeightedQuickUnionUF
N = 2 
T = 100000 
mean = 0.6667375 
stddev = 0.1178266520145013 
confidenceLo = 0.6660072028447017 
confidenceHi = 0.6674677971552982 
*/
import java.lang.*;
import stdlib.*;
import algs4.*;
public class PercolationStats {
    Percolation p;
    double m, sd;
    double[] x;
    int T;
    public PercolationStats(int N, int T) {     // perform T independent experiments on an N-by-N grid
        this.T = T;
        x =  new double[T];
        for(int i = 0; i < T; i++) { //TODO: repeat this T times and use the math functions
            p = new Percolation(N);
            int count=0;
            x[i] = 0.0;
            for(int j=1;j<=N*N;j++) {
                count++;
                int  r = StdRandom.uniform(1, N+1);
                int  s = StdRandom.uniform(1, N+1);
                while(p.isOpen(r, s)) {
                    r = StdRandom.uniform(1, N+1);
                    s = StdRandom.uniform(1, N+1);
                }
                p.open(r, s);
                if(p.percolates()) {
                    break;
                }
            }
            x[i] = (double)count / (double)(N*N);
        }
    }
    public double mean() {                      // sample mean of percolation threshold
        return StdStats.mean(x);
    }
    public double stddev() {                    // sample standard deviation of percolation threshold
        return StdStats.stddev(x);
    }
    public double confidenceLo() {             // low  endpoint of 95% confidence interval
        double sqrtt = Math.sqrt(T);
        double sd = stddev();
        return mean() - ((1.96 * sd)/sqrtt);
    }
    public double confidenceHi() {              // high endpoint of 95% confidence interval
        double sqrtt = Math.sqrt(T);
        double sd = stddev();
        return mean() + ((1.96 * sd)/sqrtt);
    }
    public static void main(String[] args) {    // test client (described below)
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if(N<=0 || T<= 0) throw new IllegalArgumentException();
        Stopwatch sw = new Stopwatch();
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("Time for calculating PercolationStats :" + sw.elapsedTime());
        System.out.println("N = " + N);
        System.out.println("T = " + T);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());        
        System.out.println("confidenceLo = " + ps.confidenceLo());
        System.out.println("confidenceHi = " + ps.confidenceHi());
    }
}