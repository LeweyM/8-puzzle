import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private final Board root;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        root = initial;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        MinPQ<Board> pq = new MinPQ<>(Comparator.comparingInt(Board::manhattan));
        pq.insert(root);
        Board board = pq.delMin();

        Board twin = board.twin();
        MinPQ<Board> twinPq = new MinPQ<>(Comparator.comparingInt(Board::manhattan));

        HashSet<String> visited = new HashSet<>();
        HashSet<String> twinVisited = new HashSet<>();

        while(!board.isGoal() && !twin.isGoal()) {
            visitNeighbors(pq, visited, board);
            board = pq.delMin();

            visitNeighbors(twinPq, twinVisited, twin);
            twin = twinPq.delMin();
        }

        return board.isGoal();
    }

    // min number of moves to solve initial board
    public int moves() {
        int searchDepth = 0;
        MinPQ<Board> pq = new MinPQ<>(Comparator.comparingInt(Board::manhattan));
        pq.insert(root);
        Board board = pq.delMin();

        HashSet<String> visited = new HashSet<>();

        while(!board.isGoal()) {
            visitNeighbors(pq, visited, board);
            board = pq.delMin();
            searchDepth++;
        }

        return searchDepth;
    }

//    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        MinPQ<Board> pq = new MinPQ<>(Comparator.comparingInt(Board::manhattan));
        pq.insert(root);
        Board board = pq.delMin();
        HashMap<String, Board> rootMap = new HashMap<>();
        rootMap.put(board.toString(), null);

        while(!board.isGoal()) {
            for (Board neighbor : board.neighbors()) {
                if (notVisited(rootMap, neighbor)) {
                    pq.insert(neighbor);
                }
            }
            Board nextBoard = pq.delMin();
            rootMap.put(nextBoard.toString(), board);
            board = nextBoard;
        }

        Deque<Board> stepStack = new ArrayDeque<>();
        Board parent = rootMap.get(board.toString());
        while (parent != null) {
            stepStack.push(parent);
            parent = rootMap.get(parent.toString());
        }

        ArrayList<Board> steps = new ArrayList<>();
        while (!stepStack.isEmpty()) {
            steps.add(stepStack.pop());
        }
        steps.add(board);

        return steps;
    }

    private boolean notVisited(HashMap<String, Board> rootMap, Board b) {
        return !rootMap.containsKey(b.toString());
    }

    private void visitNeighbors(MinPQ<Board> pq, HashSet<String> visited, Board board) {
        for (Board neighbor : board.neighbors()) {
            if (!visited.contains(neighbor.toString())) pq.insert(neighbor);
        }
        visited.add(board.toString());
    }
}

