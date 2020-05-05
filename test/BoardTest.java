import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board finishedBoard;
    private Board inprogressBoard;

    @BeforeEach
    void setUp() {
        finishedBoard = new Board(new int[][] {
                new int[]{1,2,3},
                new int[]{4,5,6},
                new int[]{7,8,0}
        });

        inprogressBoard = new Board(new int[][] {
                new int[]{3,2,1},
                new int[]{4,5,6},
                new int[]{7,0,8}
        });
    }

    @Test
    public void boardTest() {
        assertEquals("3\n1 2 3\n4 5 6\n7 8 0", finishedBoard.toString());
    }

    @Test
    void dimension() {
        assertEquals(3, finishedBoard.dimension());
    }

    @Test
    void hamming() {
        assertEquals(0, finishedBoard.hamming());
        assertEquals(4, inprogressBoard.hamming());
    }

    @Test
    void manhattanDistance() {
        assertEquals(0, finishedBoard.manhattan());
        assertEquals(6, inprogressBoard.manhattan());
    }

    @Test
    void isGoal() {
        assertTrue(finishedBoard.isGoal());
        assertFalse(inprogressBoard.isGoal());
    }
}