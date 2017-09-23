import java.util.Iterator;
public class Board {
    private final short[][] tiles;
    private final int N;
    private final int x0, y0;
    private Queue<Board> nq;
    private final int man;
    private final int ham;
    private Board(short[][] tiles) {
        this.N = tiles[0].length;
        this.tiles = new short[N][N];
        int x = 0, y = 0;
        int hammingCount = 0, manhattanCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    x = i;
                    y = j;
                }
                if (i != N-1 || j != N-1) {
                    if (tiles[i][j] != N*i + j + 1) hammingCount++;
                }
                int val = tiles[i][j];
                int origi = i;
                int origj = j;
                if (val != 0) {
                    origi = (val-1)/N;
                    origj = (val-1) % N;
                }
                manhattanCount += mod(origi - i) + mod(origj - j);
            }
        }
        this.x0 = x;
        this.y0 = y;
        this.man = manhattanCount;
        this.ham = hammingCount;
    }
    public Board(int[][] tiles) {
        //construct a board from an N-by-N array of blocks
        this.N = tiles[0].length;
        this.tiles = new short[N][N];
        int x = 0, y = 0;
        int hammingCount = 0, manhattanCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = (short) tiles[i][j];
                if (tiles[i][j] == 0) {
                    x = i;
                    y = j;
                }
                if (i != N-1 || j != N-1) {
                    if (tiles[i][j] != N*i + j + 1) hammingCount++;
                }
                int val = tiles[i][j];
                int origi = i;
                int origj = j;
                if (val != 0) {
                    origi = (val-1)/N;
                    origj = (val-1) % N;
                }
                manhattanCount += mod(origi - i) + mod(origj - j);
            }
        }
        this.x0 = x;
        this.y0 = y;
        this.man = manhattanCount;
        this.ham = hammingCount;
    }
    public int dimension() {
        //board dimension N
        return this.N;
    }
    public int hamming() {
        return ham;
    }
    public int manhattan() {
        return man;
    }
    private int mod(int i) {
        if (i >= 0) return i;
        return -i;
    }
    public boolean isGoal() {
        //is this board the goal board?
        return ham == 0;
    }
    public Board twin() {
        //a board that is obtained by exchanging two adjacent blocks in the same row
        int x1, y1, x2, y2;
        x1 = 0;
        if (x0 == x1) x1++;
        y1 = 0;
        if (y0 == y1) y1++;
        x2 = x1;
        y2 = y1 + 1;
        if (y1 == N-1) y2 = y1 - 1;
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
        nq = new Queue<Board>();
        if (x0 >= 1) {
            exch(x0, y0, x0 - 1, y0);
            newBoard = new Board(tiles);
            nq.enqueue(newBoard);
            exch(x0, y0, x0 - 1, y0);
        }
        if (y0 >= 1) {
            exch(x0, y0, x0, y0 - 1);
            newBoard = new Board(tiles);
            nq.enqueue(newBoard);
            exch(x0, y0, x0, y0 - 1);
        }
        if (x0 <= N-2) {
            exch(x0, y0, x0 + 1, y0);
            newBoard = new Board(tiles);
            nq.enqueue(newBoard);
            exch(x0, y0, x0 + 1, y0);
        }
        if (y0 <= N-2) {
            exch(x0, y0, x0, y0 + 1);
            newBoard = new Board(tiles);
            nq.enqueue(newBoard);
            exch(x0, y0, x0, y0 + 1);
        }
        return nq;
    }
    private void printNeighbors() {
        for (Board board : neighbors())
            StdOut.println(board);
    }
    private void exch(int x0, int y0, int x1, int y1) {
        short tmp = tiles[x0][y0];
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
        short[][] blocks2 = new short[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks2[i][j] = in.readShort();
        Board board1 = new Board(blocks2);
        board1.printNeighbors();
    }
}