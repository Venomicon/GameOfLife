import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * This {@code Tick} class provides logic necessary for performing ticks.
 *
 * @author Jan Gajda
 */
public class Tick {
    /**
     * <p>
     *    Given a 2D array of integers representing state of each {@link Cell}, applies the rules of Game Of Life to each
     *    {@code Cell} and collects them in 2D array representing state of each {@code Cell} after applying these rules.
     * </p>
     * <p>
     * The rules are:
     *      <ol>
     *           <li>Any live cell with fewer than two live neighbours dies, as if by underpopulation.</li>
     *           <li>Any live cell with two or three live neighbours lives on to the next generation.</li>
     *           <li>Any live cell with more than three live neighbours dies, as if by overpopulation.</li>
     *           <li>Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
     *      </ol>
     * </p>
     *
     * @param beforeTick 2D array representing state of each {@code Cell} on a game board.
     * @return 2D array representing state of each {@code Cell} on a game board after applying the rules
     * of the John Conway's "Game Of Life".
     */
    public static int[][] getValuesAfterTick(int[][] beforeTick) {
        int[][] afterTick = new int[beforeTick.length][beforeTick.length];
        for (int row = 0; row < beforeTick.length; row++) {
            for (int col = 0; col < beforeTick[row].length; col++) {
                int sum = countNeighbours(beforeTick, row, col);
                if (beforeTick[row][col] == 1) {
                    if (sum < 2 || sum > 3) {
                        afterTick[row][col] = 0;
                    } else {
                        afterTick[row][col] = 1;
                    }
                } else {
                    if (sum == 3) {
                        afterTick[row][col] = 1;
                    } else {
                        afterTick[row][col] = 0;
                    }
                }
            }
        }
        return afterTick;
    }

    /**
     * Given a 2D array of integers representing state of each {@link Cell} after applying the rules of
     * "Game Of Life", creates new {@code Cell} objects and adds them to {@link GridPane} for visual representation
     * of the game board.
     *
     * @param afterTick 2D array representing state of each {@code Cell} on a game board after applying the rules
     *      of the John Conway's "Game Of Life".
     * @return visual representation of a game board after performing the tick.
     */
    public static GridPane buildGridAfterTick(int[][] afterTick) {
        GridPane gridPane = new GridPane();
        for (int row = 0; row < GameOfLife.BOARD_SIZE; row++) {
            for (int col = 0; col < GameOfLife.BOARD_SIZE; col++) {
                Cell cell = new Cell(afterTick[row][col]);
                gridPane.add(cell, col, row);
                GameOfLife.beforeTick[row][col] = cell.getState();
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    /**
     * Counts the number of alive {@link Cell}s surrounding the target {@code Cell}.
     *
     * @param board target game board.
     * @param row row number of a target {@code Cell}.
     * @param col column number of a target {@code Cell}.
     * @return sum of states of {@code Cell}s sorrounding the target {@code Cell}.
     */
    private static int countNeighbours(int[][] board, int row, int col) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    sum += board[row + i][col + j];
                } catch (IndexOutOfBoundsException e) {
                    //Catching IndexOutOfBoundException
                }
            }
        }
        sum -= board[row][col];
        return sum;
    }

}
