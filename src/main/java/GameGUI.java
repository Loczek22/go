import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class GameGUI {

    private final int boardSize;
    private BoardGUI boardGUI;

    public GameGUI(int boardSize) {
        this.boardSize = boardSize;
    }

    Label playerMoveLabel = new Label("YOUR TURN");
    public void initGame() {
        Stage gameStage = new Stage();
        boardGUI = new BoardGUI(boardSize, 600 / boardSize);

        gameStage.setTitle("GO GAME");
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(650, 600);
        Button confirmMoveButton = new Button("CONFIRM MOVE");
        Button doNotMoveButton = new Button("DON'T MOVE");

        int cellSize = (int) (canvas.getHeight() / boardSize);

        canvas.setOnMouseClicked(e -> {
            int x = (int) e.getX() / cellSize;
            int y = (int) e.getY() / cellSize;

            // sprawdzenie, czy kliknięcie nastąpiło w zakresie planszy
            if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
                boardGUI.placeStone(x, y);
            }
            draw(canvas, boardGUI);
        });

        confirmMoveButton.setOnAction(e -> {
            // TODO: obsługa potwierdzenia ruchu
            updateLabel("WAIT FOR YOUR OPPONENT'S MOVE");
        });

        doNotMoveButton.setOnAction(e -> {
            // TODO: obsługa zrezygnowania ruchu
        });

        HBox buttonBox = new HBox(confirmMoveButton, doNotMoveButton);
        buttonBox.setSpacing(30);
        buttonBox.setAlignment(Pos.TOP_LEFT);

        VBox buttonLabelBox = new VBox(buttonBox, playerMoveLabel);
        buttonLabelBox.setSpacing(500);

        HBox hbox = new HBox(canvas, buttonLabelBox);
        hbox.setAlignment(Pos.CENTER);
        root.getChildren().add(hbox);

        // margines
        HBox.setMargin(buttonLabelBox, new Insets(50, 50, 0, 0 ));

        draw(canvas, boardGUI);

        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.show();
    }
    private void updateLabel(String text) {
        playerMoveLabel.setText(text);
    }

    public static void draw(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boardGUI.drawBoard(gc);
        boardGUI.drawStones(gc);
    }
}