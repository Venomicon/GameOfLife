import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * <p>
 * The main class for John Conway's "Game Of Life".
 * </p>
 * <div>
 *     <p>
 *         The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells,
 *         each of which is in one of two possible states, alive or dead, (or populated and unpopulated, respectively).
 *         Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically,
 *         or diagonally adjacent. At each step in time, the following transitions occur:
 *     </p>
 *     <ol>
 *          <li>Any live cell with fewer than two live neighbours dies, as if by underpopulation.</li>
 *           <li>Any live cell with two or three live neighbours lives on to the next generation.</li>
 *           <li>Any live cell with more than three live neighbours dies, as if by overpopulation.</li>
 *           <li>Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
 *      </ol>
 *      <p>
 *          The initial pattern constitutes the seed of the system.
 *          The first generation is created by applying the above rules simultaneously to every cell in the seed;
 *          births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick.
 *          Each generation is a pure function of the preceding one.
 *          The rules continue to be applied repeatedly to create further generations.
 *      </p>
 *  </div>
 *
 * @author Jan Gajda
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life</a>
 */
public final class GameOfLife extends Application implements EventHandler<ActionEvent> {
    /**
     * Describes one of 3 available pattern of {@link Cell}s on a {@link GridPane} game board.
     * <p>These 3 types required by recruiting team are:</p>
     * <ol>
     *      <li>Random pattern - {@code iteration = 0}</li>
     *      <li>Queen Bee pattern - {@code iteration = 1}</li>
     *      <li>Tumbler pattern - {@code iteration = 2}</li>
     * </ol>
     */
    int iteration = 0;

    public static final int BOARD_SIZE = 50;
    public static final double GAME_SPEED_IN_SECONDS = 1.0 / 30;
    public static int[][] beforeTick = new int[BOARD_SIZE][BOARD_SIZE];
    public static int[][] afterTick = new int[BOARD_SIZE][BOARD_SIZE];

    private final GameBoard gameBoard = new GameBoard();
    private final Timeline timeline = new Timeline();
    private GridPane gridPane = gameBoard.setupGrid(iteration);
    private Scene scene = new Scene(gridPane);
    private Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point of JavaFX application. It controls the {@link Timeline} object, which together with
     * {@link GameOfLife#handle(ActionEvent)} method are responsible for proper game loop logic.
     * It manages scenes and stages.
     * <p>
     * It also implements {@link EventHandler} of {@link javafx.scene.input.KeyEvent} generic type.
     * It listens for a user to press {@code ENTER} key which is responsible for increasing the {@link GameOfLife#iteration},
     * therefore cycling through the available {@code GridPane} game board patterns.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @see Application#start(Stage)
     */
    @Override
    public void start(Stage primaryStage) {
        //Game scene
        stage.setResizable(false);
        stage.setWidth(16 * BOARD_SIZE);
        stage.setHeight(16 * BOARD_SIZE);
        stage.setTitle("Game Of Life");
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.setScene(scene);
        stage.show();

        //Animation controller
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setDelay(Duration.seconds(2));
        timeline.getKeyFrames().addAll(new KeyFrame(
                Duration.seconds(GAME_SPEED_IN_SECONDS),
                this
        ));
        timeline.play();

        //ENTER key listener
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                iteration++;
                if (iteration < 3) {
                    timeline.stop();
                    GridPane newGrid = gameBoard.setupGrid(iteration);
                    scene.setRoot(newGrid);
                    stage.setScene(scene);
                    stage.show();
                    timeline.play();
                } else {
                    Platform.exit();
                }
            }
        });
    }

    /**
     * Event handler for {@link Timeline} attribute.
     * This handler is responsible for performing tick logic via {@link Tick} class,
     * and forwarding the resulting {@link GridPane} to the main {@link Stage}, which renders it on screen.
     *
     * @param event target {@link ActionEvent}
     * @see EventHandler#handle(Event)
     */
    @Override
    public void handle(ActionEvent event) {
        afterTick = Tick.getValuesAfterTick(beforeTick);
        gridPane = Tick.buildGridAfterTick(afterTick);
        gridPane.setAlignment(Pos.CENTER);
        scene.setRoot(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
