package elements;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Setup {
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
        gridPane.setPrefSize(750, 750);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

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

    private static GridPane setupQueenBee() {
        GridPane gridPane = new GridPane();
        for (int i =0; i<50; i++) {
            for (int j=0; j<50; j++) {
                gridPane.add(new Cell(0), i, j);
            }
        }
        Cell.getCellFromGridPane(gridPane, 21, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 21, 24).setState(1);
        Cell.getCellFromGridPane(gridPane, 22, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 23, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 24, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 25, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 26, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 27, 24).setState(1);
        Cell.getCellFromGridPane(gridPane, 27, 23).setState(1);
        return gridPane;
    }

    private static GridPane setupTumbler() {
        GridPane gridPane = new GridPane();
        for (int i =0; i<50; i++) {
            for (int j=0; j<50; j++) {
                gridPane.add(new Cell(0), i, j);
            }
        }
        Cell.getCellFromGridPane(gridPane, 20, 22).setState(1);
        Cell.getCellFromGridPane(gridPane, 20, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 20, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 20, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 21, 22).setState(1);
        Cell.getCellFromGridPane(gridPane, 21, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 21, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 21, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 22, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 22, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 23, 21).setState(1);
        Cell.getCellFromGridPane(gridPane, 23, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 23, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 23, 27).setState(1);
        Cell.getCellFromGridPane(gridPane, 24, 21).setState(1);
        Cell.getCellFromGridPane(gridPane, 24, 23).setState(1);
        Cell.getCellFromGridPane(gridPane, 24, 25).setState(1);
        Cell.getCellFromGridPane(gridPane, 24, 27).setState(1);
        Cell.getCellFromGridPane(gridPane, 25, 21).setState(1);
        Cell.getCellFromGridPane(gridPane, 25, 22).setState(1);
        Cell.getCellFromGridPane(gridPane, 25, 26).setState(1);
        Cell.getCellFromGridPane(gridPane, 25, 27).setState(1);
        return gridPane;
    }

}
