import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new int[][] {
                new int[]{1,2,3},
                new int[]{4,5,6},
                new int[]{7,8,0}
        });
    }

    @Test
    public void boardTest() {
        assertEquals(board.toString(), "3\n1 2 3\n4 5 6\n7 8 0");
    }

}