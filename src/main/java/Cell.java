import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

/**
 * <p>
 *     This class represents elementary components of a game board. It extends {@link Rectangle} class
 *     for basic graphic visualisation.
 * </p>
 * <p>
 *     It's represented by 15 x 15 pixels rectangles. Each with {@code state} attribute which depicts current state
 *     of a {@code Cell} and its color.
 * </p>
 * <p>
 *     It provides user with static methods for obtaining {@code Cell} from {@link GridPane} game board
 *     and performing operations necessary for game logic.
 * </p>
 *
 * @author Jan Gajda
 */
public final class Cell extends Rectangle {
    /**
     * Represents current state of a {@code Cell}. {@code 1} for an alive cell and {@code 0} for a dead cell.
     * The color of a {@code Cell} depends on the value of this attribute.
     * {@code int} type is mandatory for proper calculations in method {@link Cell#countNeighbours(GridPane, Cell)}.
     */
    private int state;

    /**
     * Public constructor for {@code Cell} class. It sets the width and height inherited from {@link Rectangle} class.
     * It takes one argument of {@code int} type, which specifies either the {@code Cell} is dead or alive.
     *
     * @param state state of a Cell. 1 for an alive cell, 0 for a dead cell.
     */
    public Cell(int state) {
        this.state = state;
        this.setWidth(15);
        this.setHeight(15);
        if (this.state == 1) {
            this.setFill(Paint.valueOf("red"));
        } else {
            this.setFill(Paint.valueOf("white"));
        }
    }

    /**
     * Returns the {@link Cell} from target {@link GridPane}, specified by provided number of row and column.
     *
     * @param gridPane {@link GridPane} in which the cells are located.
     * @param row the row of a cell.
     * @param col the column of a cell.
     * @return the specific {@link Optional} from gridPane. Wrapped with Optional to prevent
     *      throwing of {@link NullPointerException} at the boundaries of gridPane.
     */
    public static Optional<Cell> getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            try {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return Optional.ofNullable((Cell) node);
                }
            } catch (Exception e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the number of alive cells, surrounding the target {@link Cell}.
     *
     * @param gridPane {@link GridPane} in which the cells are located.
     * @param cell target {@link Cell}.
     * @return the sum of states of surrounding cells.
     */
    public static int countNeighbours(GridPane gridPane, Cell cell) {
        int sum = 0;
        int row = GridPane.getRowIndex(cell);
        int col = GridPane.getColumnIndex(cell);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Optional<Cell> cellOptional = getCellFromGridPane(gridPane, row + i, col + j);
                if (cellOptional.isPresent()) {
                    sum += cellOptional.get().getState();
                }
            }
        }
        sum -= cell.getState();
        return sum;
    }

    /**
     * Returns the {@code state} attribute, which determines either the {@code Cell} is dead or alive.
     *
     * @return state of a Cell. 1 for an alive cell, 0 for a dead cell.
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state of a {@code Cell} to the argument's value. It also sets the color of a {@code Cell}.
     * Red for an alive cell and white for a dead cell.
     *
     * @param state state of a Cell. 1 for an alive cell, 0 for a dead cell.
     */
    public void setState(int state) {
        this.state = state;
        if (state == 1) {
            this.setFill(Paint.valueOf("red"));
        } else {
            this.setFill(Paint.valueOf("white"));
        }
    }
}
