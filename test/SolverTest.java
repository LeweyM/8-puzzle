import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private Board board;
    private Solver solver;

    @BeforeEach
    void setUp() {
        board = new Board(new int[][]{
                new int[]{0, 1, 3},
                new int[]{4, 2, 5},
                new int[]{7, 8, 6},
        });
        solver = new Solver(board);
    }

    @Test
    void moves() {
        assertEquals(4, solver.moves());
    }

    @Test
    void solvable() {
        Board unsolvableBoard = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{8, 7, 0},
        });
        solver = new Solver(unsolvableBoard);

        assertFalse(solver.isSolvable());
    }

    @Test
    void solution() {
        Board step1 = new Board(new int[][]{
                new int[]{1, 0, 3},
                new int[]{4, 2, 5},
                new int[]{7, 8, 6},
        });
        Board step2 = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 0, 5},
                new int[]{7, 8, 6},
        });
        Board step3 = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 0},
                new int[]{7, 8, 6},
        });
        Board goal = new Board(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
        });

        ArrayList<Board> solutionSteps = new ArrayList<>();
        solver.solution().forEach(solutionSteps::add);

        assertThat(solutionSteps, contains(board, step1, step2, step3, goal));
    }

    @Test
    void example2x2() {
        board = new Board(new int[][]{
                new int[]{2, 3},
                new int[]{1, 0},
        });
        Board step1 = new Board(new int[][]{
                new int[]{2, 0},
                new int[]{1, 3},
        });
        Board step2 = new Board(new int[][]{
                new int[]{0, 2},
                new int[]{1, 3},
        });
        Board step3 = new Board(new int[][]{
                new int[]{1, 2},
                new int[]{0, 3},
        });
        Board goal = new Board(new int[][]{
                new int[]{1, 2},
                new int[]{3, 0},
        });

        solver = new Solver(board);
        ArrayList<Board> solutionSteps = new ArrayList<>();
        solver.solution().forEach(solutionSteps::add);

        assertEquals(4, solver.moves());
        assertThat(solutionSteps, contains(board, step1, step2, step3, goal));
    }
}