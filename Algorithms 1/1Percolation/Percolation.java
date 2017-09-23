public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF qf;
    private int N;
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.grid = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                this.grid[i][j] = 0;
            }
        }
        this.qf = new WeightedQuickUnionUF(N*N + 2);
        this.N = N;
    }
    public void open(int i, int j) {
        validateIndices(i, j);
        if (!isOpen(i, j)) {
            grid[i][j] = 1;
            if (i != 1 && isOpen(i-1, j)) qf.union(xyTo1D(i-1, j), xyTo1D(i, j));
            if (j != 1 && isOpen(i, j-1)) qf.union(xyTo1D(i, j-1), xyTo1D(i, j));
            if (i != N && isOpen(i+1, j)) qf.union(xyTo1D(i+1, j), xyTo1D(i, j)); 
            if (j != N && isOpen(i, j+1)) qf.union(xyTo1D(i, j+1), xyTo1D(i, j));
            if (i == 1) qf.union(0, xyTo1D(i, j));
            if (i == N) qf.union(N*N + 1, xyTo1D(i, j));
        }
    }
    private int xyTo1D(int x, int y) {
        return (this.N * (x-1)) + y;
    }
    private void validateIndices(int x, int y) {
        if (x < 1 || y < 1 || x > N || y > N) throw new IndexOutOfBoundsException();
    }
    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        return (grid[i][j] == 1);
    }
    public boolean isFull(int i, int j) {
        validateIndices(i, j);
            return qf.connected(0, xyTo1D(i, j));
    }
    public boolean percolates() {
        return qf.connected(0, N*N + 1);
    }
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system

        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            System.out.println("perc.isFull(" + i + ", " + j + ") = " + perc.isFull(i, j));
            System.out.println("perc.isOpen(" + i + ", " + j + ") = " + perc.isOpen(i, j));
            System.out.println("perc.percolates() = " + perc.percolates());
        }
    }
}
