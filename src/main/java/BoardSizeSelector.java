import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardSizeSelector {

    private final Stage primaryStage;

    public BoardSizeSelector(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initSizeSelectionScreen() {
        VBox root = new VBox(10);
        Button size19Button = new Button("19x19");
        Button size13Button = new Button("13x13");
        Button size9Button = new Button("9x9");

        size19Button.setOnAction(e -> {
            showGameBoard(19);
        });

        size13Button.setOnAction(e -> {
            showGameBoard(13);
        });

        size9Button.setOnAction(e -> {
            showGameBoard(9);
        });

        root.getChildren().addAll(size19Button, size13Button, size9Button);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
    }

    private void showGameBoard(int boardSize) {
        primaryStage.hide();
        GameGUI gameGUI = new GameGUI(boardSize);
        gameGUI.initGame();
    }
}