package GUI;

import GUI.BoardSizeSelector;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class PlayerOptions {

    private final Stage primaryStage;

    public PlayerOptions(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showPlayerOptions() {
        VBox root = new VBox(10);
        root.setId("playerOptionsRoot");
        Button twoPlayersButton = new Button("2 PLAYERS");
        twoPlayersButton.setId("twoPlayersButton");
        Button botButton = new Button("GAME WITH BOT");
        botButton.setId("botButton");

        twoPlayersButton.setOnAction(e -> {
            BoardSizeSelector boardSizeSelector = new BoardSizeSelector(primaryStage);
            boardSizeSelector.initSizeSelectionScreen();
        });

        botButton.setOnAction(e -> {
            // TODO: implementacja gry z botem
            //GUI.BoardSizeSelector boardSizeSelector = new GUI.BoardSizeSelector(primaryStage);
            //boardSizeSelector.initSizeSelectionScreen();
        });

        root.getChildren().addAll(twoPlayersButton, botButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
    }
}