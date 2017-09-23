public class PercolationStats {
    private double[] x;
    private int T;
    public PercolationStats(int N, int T) {
        this.T = T;
        x =  new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;
            x[i] = 0.0;
            for (int j = 1; j <= N*N; j++) {
                count++;
                int  r = StdRandom.uniform(1, N+1);
                int  s = StdRandom.uniform(1, N+1);
                while (p.isOpen(r, s)) {
                    r = StdRandom.uniform(1, N+1);
                    s = StdRandom.uniform(1, N+1);
                }
                p.open(r, s);
                if (p.percolates()) {
                    break;
                }
            }
            x[i] = (double) count/ (double) (N*N);
        }
    }
    public double mean() {
        return StdStats.mean(x);
    }
    public double stddev() {
        return StdStats.stddev(x);
    }
    public double confidenceLo() {
        double sqrtt = Math.sqrt(T);
        return mean() - ((1.96 * stddev())/sqrtt);
    }
    public double confidenceHi() {
        double sqrtt = Math.sqrt(T);
        return mean() + ((1.96 * stddev())/sqrtt);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());        
        System.out.println("95% confidence interval = " + ps.confidenceLo()
                           + ", " + ps.confidenceHi());
    }
}