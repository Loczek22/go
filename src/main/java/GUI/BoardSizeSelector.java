package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class BoardSizeSelector {

    private final Stage primaryStage;
    private static int selectedBoardSize;

    public BoardSizeSelector(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initSizeSelectionScreen() {
        VBox root = new VBox(10);
        root.setId("sizeSelectionRoot");
        Button size19Button = new Button("9x9");
        size19Button.setId("size9Button");
        Button size13Button = new Button("13x13");
        size13Button.setId("size13Button");
        Button size9Button = new Button("19x19");
        size9Button.setId("size19Button");

        size19Button.setOnAction(e -> {
            selectedBoardSize = 9;
            showGameBoard(9);
        });

        size13Button.setOnAction(e -> {
            selectedBoardSize = 13;
            showGameBoard(13);
        });

        size9Button.setOnAction(e -> {
            selectedBoardSize = 19;
            showGameBoard(19);
        });

        root.getChildren().addAll(size19Button, size13Button, size9Button);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
    }

    void showGameBoard(int boardSize) {
        primaryStage.hide();
        GameGUI gameGUI = new GameGUI(boardSize);
        gameGUI.initGame();
    }

    public static int getSelectedBoardSize() {
        return selectedBoardSize;
    }
}