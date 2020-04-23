import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.Optional;
import java.util.Random;

/**
 * <p>
 *     This utility class was extracted to provide transparent static methods alternating the {@link GridPane} game board.
 * </p>
 * <p>
 *     It contains of factory method for setting up initial game board in 3 different
 *     patterns required by the recruiting team, {@code GridPane} adjustment method,
 *     as well as {@link GridUtils#performTick(GridPane)} method providing logic for creating new generation of cells
 *     and alternating existing {@code GridPane}.
 * </p>
 *
 * @author Jan Gajda
 */
public final class GridUtils {
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
    public static GridPane setupGrid(int iteration) {
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
        adjustGridPane(gridPane);
        return gridPane;
    }

    /**
     * Returns a new {@link GridPane} of cells, after iterating through the target {@code GridPane},
     * counting up each individual {@link Cell}'s alive neighbours and
     * applying the rules of "Game Of Life" by John Conway to them based on a number of alive neighbouring cells.
     * The rules are:
     * <ol>
     *     <li>Any live cell with fewer than two live neighbours dies, as if by underpopulation.</li>
     *     <li>Any live cell with two or three live neighbours lives on to the next generation.</li>
     *     <li>Any live cell with more than three live neighbours dies, as if by overpopulation.</li>
     *     <li>Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
     * </ol>
     *
     * @param gridPane target GridPane
     * @return a new GridPane with new generation of Cells.
     */
    public static GridPane performTick(GridPane gridPane) {
        GridPane newGrid = new GridPane();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Optional<Cell> cellOptional = Cell.getCellFromGridPane(gridPane, i, j);
                if (cellOptional.isPresent()) {
                    Cell cell = cellOptional.get();
                    int sum = Cell.countNeighbours(gridPane, cell);
                    if (cell.getState() == 1) {
                        if (sum < 2 || sum > 3) {
                            newGrid.add(new Cell(0), j, i);
                        } else {
                            newGrid.add(new Cell(1), j, i);
                        }
                    } else {
                        if (sum == 3) {
                            newGrid.add(new Cell(1), j, i);
                        } else {
                            newGrid.add(new Cell(0), j, i);
                        }
                    }
                }
            }
        }
        GridUtils.adjustGridPane(newGrid);
        return newGrid;
    }

    /**
     * Adjusts the properties of target {@link GridPane}.
     * <ul>
     *     <li>Sets center alignment</li>
     *     <li>Sets grid lines to visible</li>
     *     <li>Sets the preferred size to 750 x 750 pixels</li>
     * </ul>
     *
     * @param gridPane target GridPane.
     */
    public static void adjustGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setPrefSize(750, 750);
    }

    /**
     * Sets up a {@link GridPane} with cells in a random pattern using pseudorandom generator of {@link Random} class.
     *
     * @return GridPane with cells in a random pattern.
     */
    private static GridPane setupRandom() {
        GridPane gridPane = new GridPane();
        Random generator = new Random();
        for (int i =0; i<50; i++) {
            for (int j=0; j<50; j++) {
                gridPane.add(new Cell(generator.nextInt(2)), i, j);
            }
        }
        return gridPane;
    }

    /**
     * Sets up a {@link GridPane} with cells in a Queen Bee pattern.
     *
     * @return GridPane with cells in a Queen Bee pattern.
     */
    private static GridPane setupQueenBee() {
        GridPane gridPane = new GridPane();
        for (int i =0; i<50; i++) {
            for (int j=0; j<50; j++) {
                gridPane.add(new Cell(0), i, j);
            }
        }
        Cell.getCellFromGridPane(gridPane, 21, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 21, 24).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 22, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 23, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 24, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 25, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 26, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 27, 24).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 27, 23).ifPresent(cell -> cell.setState(1));
        return gridPane;
    }

    /**
     * Sets up a {@link GridPane} with cells in a Tumbler pattern.
     *
     * @return GridPane with cells in a Tumbler pattern.
     */
    private static GridPane setupTumbler() {
        GridPane gridPane = new GridPane();
        for (int i =0; i<50; i++) {
            for (int j=0; j<50; j++) {
                gridPane.add(new Cell(0), i, j);
            }
        }
        Cell.getCellFromGridPane(gridPane, 20, 22).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 20, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 20, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 20, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 21, 22).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 21, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 21, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 21, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 22, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 22, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 23, 21).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 23, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 23, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 23, 27).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 24, 21).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 24, 23).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 24, 25).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 24, 27).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 25, 21).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 25, 22).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 25, 26).ifPresent(cell -> cell.setState(1));
        Cell.getCellFromGridPane(gridPane, 25, 27).ifPresent(cell -> cell.setState(1));
        return gridPane;
    }

}
