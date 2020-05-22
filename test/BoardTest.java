import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board finishedBoard;
    private Board inprogressBoard;
    private Board unorderedBoard;

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

        unorderedBoard = new Board(new int[][] {
                new int[]{4,3,1},
                new int[]{7,0,2},
                new int[]{8,5,6}
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
        assertEquals(5, inprogressBoard.manhattan());
        assertEquals(10, unorderedBoard.manhattan());
    }

    @Test
    void isGoal() {
        assertTrue(finishedBoard.isGoal());
        assertFalse(inprogressBoard.isGoal());
    }

    @Test
    void equals() {
        Board otherBoard = new Board(new int[][] {
                new int[]{1,2,3},
                new int[]{4,5,6},
                new int[]{7,8,0}
        });

        assertEquals(finishedBoard, otherBoard);
        assertNotEquals(finishedBoard, otherBoard.toString());
        assertNotEquals(finishedBoard, inprogressBoard);
    }

    @Test
    void neighbors() {
        Board rightNeighbor = new Board(new int[][]{
                new int[]{3, 2, 1},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0}
        });
        Board upNeighbor = new Board(new int[][]{
                new int[]{3, 2, 1},
                new int[]{4, 0, 6},
                new int[]{7, 5, 8}
        });
        Board leftNeighbor = new Board(new int[][]{
                new int[]{3, 2, 1},
                new int[]{4, 5, 6},
                new int[]{0, 7, 8}
        });

        ArrayList<Board> neighborList = new ArrayList<>();
        inprogressBoard.neighbors().forEach(neighborList::add);

        assertEquals(3, neighborList.size());
        assertThat(neighborList, containsInAnyOrder(leftNeighbor, upNeighbor, rightNeighbor));
    }

    @Test
    void twin() {
        Board twin = finishedBoard.twin();
        assertEquals(2, twin.hamming());
    }
}