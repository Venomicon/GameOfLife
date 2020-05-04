import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.Random;

/**
 * <p>
 * This utility class was extracted to provide transparent methods alternating the {@link GridPane} game board.
 * </p>
 * <p>
 * It contains of factory method for setting up initial game board in 3 different
 * patterns required by the recruiting team.
 * </p>
 *
 * @author Jan Gajda
 */
public final class GameBoard {
    /**
     * This factory method returns a {@link GridPane} with certain pattern of {@link Cell}s,
     * specified by {@link GameOfLife#iteration}.
     * <ol>
     *     <li>Random pattern - {@code iteration = 0}</li>
     *     <li>Queen Bee pattern - {@code iteration = 1}</li>
     *     <li>Tumbler pattern - {@code iteration = 2}</li>
     * </ol>
     *
     * @param iteration the one of three game patterns required by recruiting team.
     * @return the GridPane with distinct pattern of cells.
     * @throws IllegalArgumentException if the iteration param is not equal to {0, 1, 2}.
     */
    public GridPane setupGrid(int iteration) {
        GridPane gridPane;
        switch (iteration) {
            case 0:
                gridPane = setupRandom();
                break;
            case 1:
                gridPane = setupQueenBee();
                break;
            case 2:
                gridPane = setupTumbler();
                break;
            default:
                throw new IllegalArgumentException(iteration + " not allowed");
        }
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    /**
     * Sets up a {@link GridPane} with cells in a random pattern using pseudorandom generator of {@link Random} class.
     *
     * @return GridPane with cells in a random pattern.
     */
    private GridPane setupRandom() {
        GridPane gridPane = new GridPane();
        Random generator = new Random();
        for (int row = 0; row < GameOfLife.BOARD_SIZE; row++) {
            for (int col = 0; col < GameOfLife.BOARD_SIZE; col++) {
                Cell cell = new Cell(generator.nextInt(2));
                gridPane.add(cell, col, row);
                GameOfLife.beforeTick[row][col] = cell.getState();
            }
        }
        return gridPane;
    }

    /**
     * Sets up a {@link GridPane} with cells in a Queen Bee pattern.
     *
     * @return GridPane with cells in a Queen Bee pattern.
     */
    private GridPane setupQueenBee() {
        GridPane gridPane = setupClearBoard();
        int centerIndex = getCenterIndex();
        //Creating the pattern
        {
            GameOfLife.beforeTick[centerIndex - 3][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex - 3);
            GameOfLife.beforeTick[centerIndex - 3][centerIndex] = 1;
            gridPane.add(new Cell(1), centerIndex, centerIndex - 3);

            GameOfLife.beforeTick[centerIndex - 2][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex - 2);

            GameOfLife.beforeTick[centerIndex - 1][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex - 1);
            GameOfLife.beforeTick[centerIndex][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex);
            GameOfLife.beforeTick[centerIndex + 1][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex + 1);

            GameOfLife.beforeTick[centerIndex + 2][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex + 2);

            GameOfLife.beforeTick[centerIndex + 3][centerIndex] = 1;
            gridPane.add(new Cell(1), centerIndex, centerIndex + 3);
            GameOfLife.beforeTick[centerIndex + 3][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex + 3);
        }
        return gridPane;
    }

    /**
     * Sets up a {@link GridPane} with cells in a Tumbler pattern.
     *
     * @return GridPane with cells in a Tumbler pattern.
     */
    private GridPane setupTumbler() {
        GridPane gridPane = setupClearBoard();
        int centerIndex = getCenterIndex();
        //Creating the pattern
        {
            GameOfLife.beforeTick[centerIndex - 4][centerIndex - 2] = 1;
            gridPane.add(new Cell(1), centerIndex - 2, centerIndex - 4);
            GameOfLife.beforeTick[centerIndex - 4][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex - 4);
            GameOfLife.beforeTick[centerIndex - 4][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex - 4);
            GameOfLife.beforeTick[centerIndex - 4][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex - 4);

            GameOfLife.beforeTick[centerIndex - 3][centerIndex - 2] = 1;
            gridPane.add(new Cell(1), centerIndex - 2, centerIndex - 3);
            GameOfLife.beforeTick[centerIndex - 3][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex - 3);
            GameOfLife.beforeTick[centerIndex - 3][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex - 3);
            GameOfLife.beforeTick[centerIndex - 3][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex - 3);

            GameOfLife.beforeTick[centerIndex - 2][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex - 2);
            GameOfLife.beforeTick[centerIndex - 2][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex - 2);

            GameOfLife.beforeTick[centerIndex - 1][centerIndex - 3] = 1;
            gridPane.add(new Cell(1), centerIndex - 3, centerIndex - 1);
            GameOfLife.beforeTick[centerIndex - 1][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex - 1);
            GameOfLife.beforeTick[centerIndex - 1][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex - 1);
            GameOfLife.beforeTick[centerIndex - 1][centerIndex + 3] = 1;
            gridPane.add(new Cell(1), centerIndex + 3, centerIndex - 1);

            GameOfLife.beforeTick[centerIndex][centerIndex - 3] = 1;
            gridPane.add(new Cell(1), centerIndex - 3, centerIndex);
            GameOfLife.beforeTick[centerIndex][centerIndex - 1] = 1;
            gridPane.add(new Cell(1), centerIndex - 1, centerIndex);
            GameOfLife.beforeTick[centerIndex][centerIndex + 1] = 1;
            gridPane.add(new Cell(1), centerIndex + 1, centerIndex);
            GameOfLife.beforeTick[centerIndex][centerIndex + 3] = 1;
            gridPane.add(new Cell(1), centerIndex + 3, centerIndex);

            GameOfLife.beforeTick[centerIndex + 1][centerIndex - 3] = 1;
            gridPane.add(new Cell(1), centerIndex - 3, centerIndex + 1);
            GameOfLife.beforeTick[centerIndex + 1][centerIndex - 2] = 1;
            gridPane.add(new Cell(1), centerIndex - 2, centerIndex + 1);
            GameOfLife.beforeTick[centerIndex + 1][centerIndex + 2] = 1;
            gridPane.add(new Cell(1), centerIndex + 2, centerIndex + 1);
            GameOfLife.beforeTick[centerIndex + 1][centerIndex + 3] = 1;
            gridPane.add(new Cell(1), centerIndex + 3, centerIndex + 1);
        }
        return gridPane;
    }

    /**
     * Sets up a clear {@link GridPane}, mainly as a base for Queen Bee and Tumbler patterns.
     *
     * @return clear GridPane.
     */
    private GridPane setupClearBoard() {
        GridPane gridPane = new GridPane();
        for (int row = 0; row < GameOfLife.BOARD_SIZE; row++) {
            for (int col = 0; col < GameOfLife.BOARD_SIZE; col++) {
                Cell cell = new Cell(0);
                gridPane.add(cell, col, row);
                GameOfLife.beforeTick[row][col] = cell.getState();
            }
        }
        return gridPane;
    }

    /**
     * Returns the index of a center-located {@link Cell} on a game board.
     *
     * @return index of a center-located {@code Cell}
     */
    private int getCenterIndex() {
        if (GameOfLife.BOARD_SIZE % 2 == 0) {
            return (GameOfLife.BOARD_SIZE / 2) - 1;
        } else {
            return GameOfLife.BOARD_SIZE / 2;
        }
    }
}
