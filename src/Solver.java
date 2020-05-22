import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class Solver {
    private final Stack<Board> solutionStack;
    private boolean solvable = true;

    private class SearchNode {
        public final Board board;
        public final int moves;
        public final SearchNode previous;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

    }

    private class SearchNodeOrder implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode s1, SearchNode s2) {
            if (s1.moves + s1.board.manhattan() < s2.moves + s2.board.manhattan()) return -1;
            if (s1.moves + s1.board.manhattan() > s2.moves + s2.board.manhattan()) return 1;
            if (s1.board.manhattan() < s2.board.manhattan()) return -1;
            if (s1.board.manhattan() > s2.board.manhattan()) return 1;
            return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solutionStack = new Stack<>();

        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeOrder());
        pq.insert(new SearchNode(initial, 0, null));
        Set<String> visited = new HashSet<>();
        SearchNode node;

        MinPQ<SearchNode> twinPq = new MinPQ<>(new SearchNodeOrder());
        twinPq.insert(new SearchNode(initial.twin(), 0, null));
        Set<String> twinVisited = new HashSet<>();
        SearchNode twinNode;

        while(true) {
            node = pq.delMin();
            for (Board neighbor : node.board.neighbors()) {
                if (!visited.contains(neighbor.toString())) {
                    pq.insert(new SearchNode(neighbor, node.moves+1, node));
                    visited.add(neighbor.toString());
                }
            }
            if (node.board.isGoal()) break;

            twinNode = twinPq.delMin();
            for (Board neighbor : twinNode.board.neighbors()) {
                if (!twinVisited.contains(neighbor.toString())) {
                    twinPq.insert(new SearchNode(neighbor, twinNode.moves+1, twinNode));
                    twinVisited.add(neighbor.toString());
                }
            }
            if (twinNode.board.isGoal()) {
                solvable = false;
                return;
            }
        }

        while (node.previous != null) {
           solutionStack.push(node.board);
           node = node.previous;
        }
        solutionStack.push(initial);

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return solutionStack.size() - 1;
    }

//    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return solvable ? solutionStack : null;
    }
}

