import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}