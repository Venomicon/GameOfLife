package elements;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

@Getter
public class Cell extends Rectangle {
    private int state;

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

    public static Cell getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Cell) node;
            }
        }
        return null;
    }

    public void setState(int state) {
        this.state = state;
        if (state == 1) {
            this.setFill(Paint.valueOf("red"));
        } else {
            this.setFill(Paint.valueOf("white"));
        }
    }
}
