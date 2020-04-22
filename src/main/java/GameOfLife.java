import elements.Setup;
import elements.Tick;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    int iteration = 0;
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        gridPane = Setup.setupGrid(iteration);
        gridPane.setPrefSize(750, 750);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);

        StackPane rootPane = new StackPane(gridPane);

        Scene scene = new Scene(rootPane, 750, 750);

        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gridPane = Tick.performTick(gridPane);
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setGridLinesVisible(true);
                gridPane.setPrefSize(750, 750);
                scene.setRoot(new StackPane(gridPane));
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
    }
}

