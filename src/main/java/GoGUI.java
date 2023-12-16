import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GoGUI extends Application {

    private static final int BOARD_SIZE = 19; // 13 i 9

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Go Game");

        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(700, 700);

        int cellSize = (int) (canvas.getHeight() / BOARD_SIZE);
        GoBoardGUI goBoardGUI = new GoBoardGUI(BOARD_SIZE, cellSize);

        // obsługa naciśnięcia myszy
        canvas.setOnMouseClicked(e -> {
            int x = (int) e.getX() / goBoardGUI.getCellSize();
            int y = (int) e.getY() / goBoardGUI.getCellSize();

            // sprawdzenie, czy kliknięcie nastąpiło w zakresie planszy
            if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                goBoardGUI.placeStone(x, y);
                drawBoard(canvas, goBoardGUI);
            }
        });

        drawBoard(canvas, goBoardGUI);

        root.setCenter(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void drawBoard(Canvas canvas, GoBoardGUI goBoardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        goBoardGUI.draw(gc);
    }
}