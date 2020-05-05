import java.util.ArrayList;
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
            for (int row = 0; row < b[col].length; row++) {
                int tileValue = b[col][row];
                if (tileValue != goal(col, row)) hammingCount++;
            }
        }
        return hammingCount;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int totalDistance = 0;
        for (int col = 0; col < dimension(); col++) {
            for (int row = 0; row < dimension(); row++) {
                int tileGoal = goal(col, row);
                int tileValue = b[col][row];
                if (tileValue != tileGoal) {
                    int destinationIndex = findIndexOf(tileValue);
                    totalDistance += distance(toCol(destinationIndex), toRow(destinationIndex), col, row);
                }
            }
        }
        return totalDistance;
    }

    //    // is this board the goal board?
    public boolean isGoal() {
        for (int col = 0; col < b.length; col++) {
            for (int row = 0; row < b[col].length; row++) {
                if (b[col][row] != goal(col, row)) return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return y instanceof Board
                && ((Board) y).dimension() == dimension()
                && y.toString().equals(toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();

        int emptyTileIndex = findIndexOf(0);
        int col = toCol(emptyTileIndex);
        int row = toRow(emptyTileIndex);

        if (col + 1 < dimension())  neighbors.add(swap(col, row, col + 1, row));
        if (col - 1 >= 0)           neighbors.add(swap(col, row, col - 1, row));
        if (row + 1 < dimension())  neighbors.add(swap(col, row, col, row + 1));
        if (row - 1 >= 0)           neighbors.add(swap(col, row, col, row - 1));

        return neighbors;
    }

    private Board swap(int col, int row, int col2, int row2) {
        int[][] tilesClone = Arrays.stream(this.b).map(int[]::clone).toArray(int[][]::new);
        swap(tilesClone, col, row, col2, row2);
        return new Board(tilesClone);
    }
//
//    // a board that is obtained by exchanging any pair of tiles
//    public Board twin()
//
//    // unit testing (not graded)
//    public static void main(String[] args)

    private void swap(int[][] board, int col, int row, int col2, int row2) {
        int temp = board[row][col];
        board[row][col] = board[row2][col2];
        board[row2][col2] = temp;
    }

    private int distance(int col, int row, int toCol, int toRow) {
        return Math.abs(col - toCol) + Math.abs(row - toRow);
    }

    private int toRow(int i) {
        return i / dimension();
    }

    private int toCol(int i) {
        return i % dimension();
    }

    private int goal(int col, int row) {
        return isLastTile(col, row)
                ? 0
                : getIndex(col, row) + 1;
    }

    private int findIndexOf(int value) {
        for (int col = 0; col < dimension(); col++) {
            for (int row = 0; row < dimension(); row++) {
                if (b[col][row] == value) return getIndex(col, row);
            }
        }
        return value;
    }

    private int getIndex(int col, int row) {
        return col * dimension() + row;
    }

    private boolean isLastTile(int col, int row) {
        return col == dimension() - 1 && row == dimension() - 1;
    }
}