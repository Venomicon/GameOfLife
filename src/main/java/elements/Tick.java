package elements;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class Tick {
    public static GridPane performTick(GridPane gridPane) {
        GridPane newGrid = new GridPane();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Cell cell = Cell.getCellFromGridPane(gridPane, i, j);
                int sum = countNeighbours(gridPane, cell);
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
        return newGrid;
    }

    private static int countNeighbours(GridPane gridPane, Node node) {
        int sum = 0;
        int row = GridPane.getRowIndex(node);
        int col = GridPane.getColumnIndex(node);
        Cell cell = (Cell) node;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Optional<Cell> cellOptional = Optional.ofNullable(Cell.getCellFromGridPane(gridPane, row + i, col + j));
                if (cellOptional.isPresent()) {
                    sum += cellOptional.get().getState();
                }
            }
        }
        sum -= cell.getState();
        return sum;
    }
}
