import elements.GridSetup;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOfLife extends Application implements EventHandler<ActionEvent> {
    int iteration = 0;
    GridPane gridPane = GridSetup.setupGrid(iteration);
    StackPane rootPane = new StackPane(gridPane);
    Scene scene = new Scene(rootPane, 750, 750);
    Stage primaryStage;
    final Timeline timeline = new Timeline();

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().addAll(new KeyFrame(
                Duration.seconds(1),
                this
        ));
        timeline.play();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                iteration++;
                if (iteration < 3) {
                    timeline.stop();
                    gridPane = GridSetup.setupGrid(iteration);
                    scene.setRoot(new StackPane(gridPane));
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    timeline.play();
                } else {
                    Platform.exit();
                }
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        gridPane = GridSetup.setupGridAfterTick(gridPane);
        scene.setRoot(new StackPane(gridPane));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
