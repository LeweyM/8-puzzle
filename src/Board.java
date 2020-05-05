import java.util.Arrays;

public class Board {

    private final int[][] b;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        b = tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(b.length);
        for (int[] row : b) {
            sb.append("\n");
            sb.append(Arrays.toString(row).replaceAll("\\[|\\]|,|", ""));
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return b.length;
    }

//    // number of tiles out of place
    public int hamming() {
        int hammingCount = 0;
        for (int col = 0; col < b.length; col++) {
            int[] column = b[col];
            for (int row = 0; row < column.length; row++) {
                int cell = column[row];
                int desiredIndex = col * dimension() + row + 1;
                if (desiredIndex == dimension()*dimension()) desiredIndex = 0;
                if (cell != desiredIndex) hammingCount++;
            }
        }

        return hammingCount;
    }

//    // sum of Manhattan distances between tiles and goal
//    public int manhattan()
//
//    // is this board the goal board?
//    public boolean isGoal()
//
//    // does this board equal y?
//    public boolean equals(Object y)
//
//    // all neighboring boards
//    public Iterable<Board> neighbors()
//
//    // a board that is obtained by exchanging any pair of tiles
//    public Board twin()
//
//    // unit testing (not graded)
//    public static void main(String[] args)

}