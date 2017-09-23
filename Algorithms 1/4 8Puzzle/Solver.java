import java.util.Iterator;
public class Solver {
    private final int minMoves;
    private final SearchNode solution;
    private final MinPQ<SearchNode> minPQ;
    private final MinPQ<SearchNode> twinMinPQ;
    private int solvable;
    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode previous;
        public SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
        private SearchNode(SearchNode sn) {
            this.board = sn.board;
            this.moves = sn.moves;
            this.previous = sn.previous;
        }
        public int compareTo(SearchNode that) {
            Board b1 = this.board;
            Board b2 = that.board;
            int man1 = b1.manhattan();
            int man2 = b2.manhattan();
            int priority1 = man1 + this.moves;
            int priority2 = man2 + that.moves;
            if (priority1 < priority2) return -1;
            if (priority1 > priority2) return +1;
            if (man1 < man2) return -1;
            if (man1 > man2) return +1;
            return 0;
        }
    }
    public Solver(Board initialBoard) {
        // find a solution to the initial board (using the A* algorithm)
        if (initialBoard == null) throw new java.lang.NullPointerException();
        SearchNode sn = new SearchNode(initialBoard, null, 0);
        SearchNode twinSn = new SearchNode(initialBoard.twin(), null, 4);
        int minmoves = -1;
        SearchNode soln = new SearchNode(initialBoard, null, 0);
        minPQ = new MinPQ<SearchNode>();
        twinMinPQ = new MinPQ<SearchNode>();
        solvable = -1;
        minPQ.insert(sn);
        twinMinPQ.insert(twinSn);
        while (true) {
            if (minPQ.isEmpty()) {
                //reached optimum solution
                break;
            } else {
                if (sn.board.isGoal() && (minmoves == -1 || sn.moves < minmoves)) {
                    soln = sn;
                    solvable = 1;
                    minmoves = sn.moves;
                    if (sn.board.manhattan() == 0 || sn.board.hamming() == 0) break;
                }
                for (Board b: sn.board.neighbors()) {
                    if (sn.previous == null || !b.equals(sn.previous.board)) {
                        SearchNode next = new SearchNode(b, sn, sn.moves+1);
                        minPQ.insert(next);
                    }
                }
                //printMinPQ(minPQ);
                sn = minPQ.delMin();
            }
            if (twinMinPQ.isEmpty()) {
                //twin reached optimum solution - should have breaked before this!!
            } else {
                if (twinSn.board.isGoal()) {
                    solvable = 0;
                    break;
                }
                for (Board b: twinSn.board.neighbors()) {
                    if (twinSn.previous == null || !b.equals(twinSn.previous.board)) {
                        SearchNode next = new SearchNode(b, twinSn, twinSn.moves + 1);
                        twinMinPQ.insert(next);
                    }
                }
                twinSn = twinMinPQ.delMin();
            }
        }
        this.minMoves = minmoves;
        this.solution = soln;
    }
    private void printMinPQ(MinPQ<SearchNode> pq) {
        Iterator<SearchNode> i = pq.iterator();
        StdOut.println("start->");
        while (i.hasNext()) {
            System.out.print(i.next().board);
            StdOut.printf(" ");
        }
        StdOut.println("<-end");
    }
    public boolean isSolvable() {
        // is the initial board solvable?
        return solvable == 1;
    }
    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        if (isSolvable()) return minMoves;
        return -1;
    }
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (isSolvable()) {
            Stack<Board> s = new Stack<Board>();
            SearchNode curr = new SearchNode(solution);
            while (curr != null) {
                s.push(curr.board);
                curr = curr.previous;
            }
            Queue<Board> q = new Queue<Board>();
            while (!s.isEmpty()) {
                q.enqueue(s.pop());
            }
            return q;
        }
        return null;
    }
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readShort();
        Board initialBoard = new Board(blocks);
        //StdOut.printf("initialBoard = %s\n", initialBoard);
        
        // solve the puzzle
        Solver solver = new Solver(initialBoard);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}