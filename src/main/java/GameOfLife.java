import elements.Setup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    int iteration = 0;
    GridPane gridPane;
    StackPane rootPane = new StackPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gridPane = Setup.setupGrid(iteration);
        rootPane.getChildren().addAll(gridPane);

        Scene scene = new Scene(rootPane, 750, 750);

        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

