public class Board {
    private final int[][] tiles;
    private final int N;
    private final int x0, y0;
    private final Queue<Board> neighborsQueue = new Queue<Board>();
    public Board(int[][] tiles) {
        //construct a board from an N-by-N array of blocks
        this.N = tiles[0].length;
        this.tiles = new int[N][N];
        int x0 = 0, y0 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                }
            }
        }
        this.x0 = x0;
        this.y0 = y0;
    }
    public int dimension() {
        //board dimension N
        return this.N;
    }
    public int hamming() {
        //number of blocks out of place
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != N-1 || j != N-1) {
                    if (tiles[i][j] != N*i + j + 1) count++;
                }
            }
        }
        return count;
    }
    public int manhattan() {
        //sum of Manhattan distances between blocks and goal
        int count = 0;
        int origi, origj;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //StdOut.printf("\ncount = %d\n", count);
                int val = tiles[i][j];
                origi = i;
                origj = j;
                if (val != 0) {
                    origi = (val-1)/N;
                    origj = (val-1) % N;
                }
                //StdOut.printf("%d currt (%d, %d)\n", val, i, j);
                //StdOut.printf("%d right (%d, %d)\n", val, origi, origj);
                count += mod(origi - i) + mod(origj - j);
                //StdOut.printf("new count = %d\n", count);
            }
        }
        return count;
    }
    private int mod(int i) {
        if (i >= 0) return i;
        return -i;
    }
    public boolean isGoal() {
        //is this board the goal board?
        int n = 1;
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = n++;
            }
        }
        arr[N-1][N-1] = 0;
        Board goalBoard = new Board(arr);
        return this.equals(goalBoard);
        /*for (int i = 0; i< N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N-1 && j == N-1) {
                    if(tiles[i][j] != 0) return false;
                } else {
                    if(tiles[i][j] != N*i + j + 1) return false;
                }
            }
        }
        return true;*/
    }
    public Board twin() {
        //a board that is obtained by exchanging two adjacent blocks in the same row
        int x1, y1, x2, y2;
        x1 = StdRandom.uniform(N);
        while (x1 == x0) {
            x1 = StdRandom.uniform(N);
        }
        y1 = StdRandom.uniform(N);
        while (y1 == x0) {
            y1 = StdRandom.uniform(N);
        }
        x2 = x1;
        y2 = y1 + 1;
        if (y1 == N-1) y2 = y1 - 1;
        /*StdOut.printf("x0 = %d, y0 = %d\n", x0, y0);
        StdOut.printf("x1 = %d, y1 = %d\n", x1, y1);
        StdOut.printf("x2 = %d, y2 = %d\n", x2, y2);*/
        exch(x1, y1, x2, y2);
        Board twin = new Board(tiles);
        exch(x1, y1, x2, y2);
        return twin;
    }
    public boolean equals(Object y) {
        //does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != N) return false;
        return that.toString().equals(this.toString());
    }
    public Iterable<Board> neighbors() {
        //all neighboring boards
        Board newBoard = new Board(tiles);
        if (x0 >= 1) {
            exch(x0, y0, x0 - 1, y0);
            newBoard = new Board(tiles);
            neighborsQueue.enqueue(newBoard);
            exch(x0, y0, x0 - 1, y0);
        }
        if (y0 >= 1) {
            exch(x0, y0, x0, y0 - 1);
            newBoard = new Board(tiles);
            neighborsQueue.enqueue(newBoard);
            exch(x0, y0, x0, y0 - 1);
        }
        if (x0 <= N-2) {
            exch(x0, y0, x0 + 1, y0);
            newBoard = new Board(tiles);
            neighborsQueue.enqueue(newBoard);
            exch(x0, y0, x0 + 1, y0);
        }
        if (y0 <= N-2) {
            exch(x0, y0, x0, y0 + 1);
            newBoard = new Board(tiles);
            neighborsQueue.enqueue(newBoard);
            exch(x0, y0, x0, y0 + 1);
        }
        return neighborsQueue;
    }
    private void exch(int x0, int y0, int x1, int y1) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = tiles[i][j];
            }
        }
        int tmp = tiles[x0][y0];
        tiles[x0][y0] = tiles[x1][y1];
        tiles[x1][y1] = tmp;
    }
    public String toString() {
        //string representation of this board
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String[] args) {
        //unit tests
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks2 = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks2[i][j] = in.readInt();
        Board board1 = new Board(blocks2);
        StdOut.println(board1);
    }
}