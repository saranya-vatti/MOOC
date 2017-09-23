import java.lang.*;
import stdlib.*;
import algs4.*;
public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF qf;
    private int N;
    public Percolation(int N) {
        if(N <= 0) throw new IllegalArgumentException();
        this.grid = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                this.grid[i][j]=0;
            }
        }
        this.qf = new WeightedQuickUnionUF(N*N + 2);
        this.N = N;
    }
    public void open(int i, int j) {
        if(!(i >= 1 && i <= N && j >= 1 && j <= N)) {
            throw new IndexOutOfBoundsException();
        }
        if(grid[i][j] != 1) {
            if(i != 1 && isOpen(i-1,j)) qf.union(N*(i-2) + j, N*(i-1) + j); //connect to top adjacent cell
            if(j != 1 && isOpen(i,j-1)) qf.union(N*(i-1) + j-1, N*(i-1) + j); // connect to left adjacent cell
            if(i != N && isOpen(i+1,j)) qf.union(N*(i) + j, N*(i-1) + j); // connect to bottom adjacent cell
            if(j != N && isOpen(i,j+1)) qf.union(N*(i-1) + j+1, N*(i-1) + j); // connect to right adjacent cell
            if(i==1) qf.union(0, N*(i-1) + j); //connect top dot to the top row
            if(i==N) qf.union(N*N + 1, N*(i-1) + j); //connect bottom dot to bottom row
            grid[i][j] = 1;
        }
    }
    public boolean isOpen(int i, int j) {
        if(i < 1 || j < 1 || i > N || j > N) throw new IndexOutOfBoundsException();
        return (grid[i][j] == 1);
    }
    public boolean isFull(int i, int j) {
        if(i < 1 || j < 1 || i > N || j > N) throw new IndexOutOfBoundsException();
        return (grid[i][j] == 0);
    }
    public boolean percolates() {
        return qf.connected(0, N*N + 1);
    }
    public void printGrid() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(this.isOpen(i,j)) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int N = 20;
        int T = 40;
        Percolation p = new Percolation(N);
        int count=0;
        for(int t = 0; t <= T; t++) { //TODO: repeat this T times and use the math functions
            double percolationThreshold = 0.0;
            for(int i=1;i<=N*N;i++) {
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
            percolationThreshold = (double)count / (double)(N*N);
        }
    }
}
