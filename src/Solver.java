import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

public class Solver {
    private MinPQ<Board> pq;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        pq = new MinPQ<>(Comparator.comparingInt(Board::hamming));
        pq.insert(initial);
    }

//    // is the initial board solvable? (see below)
//    public boolean isSolvable()
//
    // min number of moves to solve initial board
    public int moves() {
        int searchDepth = 0;
        Board board = pq.delMin();
        while(!board.isGoal()) {
            board.neighbors().forEach(pq::insert);
            board = pq.delMin();
            searchDepth++;
        }

        return searchDepth;
    }

//    // sequence of boards in a shortest solution
//    public Iterable<Board> solution()

}

