package GUI;

import Database.DbLoadGame;
import Database.DbSaveGame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadGameFrame extends Stage {
    private int maxGameID;
    private int selectedGameNumber;
    private final Stage primaryStage;

    public LoadGameFrame(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoadGameFrame() {
        setTitle("GO");

        DbSaveGame dbSaveGame = new DbSaveGame();
        maxGameID = dbSaveGame.getIDGame();

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);

        Label titleLabel = new Label("Choose game you want to load:");

        String[] gameNumbers = setGames();
        ComboBox<String> gameDropdown = new ComboBox<>();
        gameDropdown.getItems().addAll(gameNumbers);
        gameDropdown.setOnAction(event -> {
            String selectedGame = gameDropdown.getValue();
            selectedGameNumber = Integer.parseInt(selectedGame.replaceAll("\\D", ""));
            System.out.println("Selected game: " + selectedGameNumber);
        });

        Button startButton = new Button("Start loading game");
        startButton.setOnAction(event -> {
            DbLoadGame dbLoadGame = new DbLoadGame();
            int boardSize = dbLoadGame.getBoardSize(selectedGameNumber);

            System.out.println("Board size: " + boardSize);
            LoadBoardFrame loadBoardFrame = new LoadBoardFrame(primaryStage);
            loadBoardFrame.initLoadBoard(boardSize);
            System.out.println("Loading game " + selectedGameNumber);
            close();
        });

        root.getChildren().addAll(titleLabel, gameDropdown, startButton);

        setScene(new Scene(root, 300, 200));
        show();
    }

    private String[] setGames() {
        String[] games = new String[maxGameID];
        for (int i = 1; i <= maxGameID; i++) {
            games[i-1] = "Game " + i;
        }
        return games;
    }
}