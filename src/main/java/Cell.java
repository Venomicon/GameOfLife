import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * <p>
 *     This class represents elementary components of a game board. It extends {@link Rectangle} class
 *     for basic graphic visualisation.
 * </p>
 * <p>
 *     It's represented by 15 x 15 pixels rectangles. Each with {@code state} attribute which depicts current state
 *     of a {@code Cell} and its color.
 * </p>
 *
 * @author Jan Gajda
 */
public final class Cell extends Rectangle {
    /**
     * Represents current state of a {@code Cell}. {@code 1} for an alive cell and {@code 0} for a dead cell.
     * The color of a {@code Cell} depends on the value of this attribute.
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
        this.setStrokeType(StrokeType.INSIDE);
        this.setStroke(Color.BLACK);
        if (this.state == 1) {
            this.setFill(Color.RED);
        } else {
            this.setFill(Color.WHITE);
        }
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
            this.setFill(Color.RED);
        } else {
            this.setFill(Color.WHITE);
        }
    }
}
