import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameGUI {

    public GameGUI(int boardSize) {
        this.boardSize = boardSize;
    }

    private final int boardSize;
    private BoardGUI BoardGUI;

    public void initGame() {
        Stage gameStage = new Stage();
        BoardGUI = new BoardGUI(boardSize, 700 / boardSize);

        gameStage.setTitle("GO GAME");
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(700, 700);

        int cellSize = (int) (canvas.getHeight() / boardSize);

        // obsługa naciśnięcia myszy
        canvas.setOnMouseClicked(e -> {
            int x = (int) e.getX() / cellSize;
            int y = (int) e.getY() / cellSize;

            // sprawdzenie, czy kliknięcie nastąpiło w zakresie planszy
            if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
                BoardGUI.placeStone(x, y);
            }
            drawBoard(canvas, BoardGUI);
        });

        drawBoard(canvas, BoardGUI);

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.show();
    }

    public static void drawBoard(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boardGUI.draw(gc);
    }
}