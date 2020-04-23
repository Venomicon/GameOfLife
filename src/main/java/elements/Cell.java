package elements;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

import java.util.Optional;

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
            try {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (Cell) node;
                }
            } catch (Exception e) {
                return new Cell(0);
            }
        }
        return null;
    }

    public static int countNeighbours(GridPane gridPane, Node node) {
        int sum = 0;
        int row = GridPane.getRowIndex(node);
        int col = GridPane.getColumnIndex(node);
        Cell cell = (Cell) node;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Optional<Cell> cellOptional = Optional.ofNullable(getCellFromGridPane(gridPane, row + i, col + j));
                if (cellOptional.isPresent()) {
                    sum += cellOptional.get().getState();
                }
            }
        }
        sum -= cell.getState();
        return sum;
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
