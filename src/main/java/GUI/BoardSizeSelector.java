package GUI;


import Server.Client;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
            setSelectedBoardSize(9);
            waitForOpponent();
        });

        size13Button.setOnAction(e -> {
            setSelectedBoardSize(13);
            waitForOpponent();
        });

        size9Button.setOnAction(e -> {
            setSelectedBoardSize(19);
            waitForOpponent();
        });

        root.getChildren().addAll(size19Button, size13Button, size9Button);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
    }

    void showGameBoard(int boardSize) {
        primaryStage.hide();
        Client client = new Client(boardSize);
        System.out.println("client created");
        client.start();
        System.out.println("client started");
    }

    public static int getSelectedBoardSize() {
        return selectedBoardSize;
    }

    public static void setSelectedBoardSize(int selectedBoardSize) {
        BoardSizeSelector.selectedBoardSize = selectedBoardSize;
    }
    private void waitForOpponent() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("INFO");
        alert.setHeaderText("WAIT FOR YOUR OPPONENT");
        ButtonType OK = new ButtonType("OK");
        ButtonType CANCEL = new ButtonType("CANCEL");
        alert.getButtonTypes().setAll(OK, CANCEL);

        // Dodanie nasłuchiwania na kliknięcia przycisków
        Button okButton = (Button) alert.getDialogPane().lookupButton(OK);
        okButton.setOnAction(event -> {
            System.out.println("OK");
            alert.hide();
            showGameBoard(getSelectedBoardSize());
        });

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(CANCEL);
        cancelButton.setOnAction(event -> {
            System.out.println("CANCEL");
            alert.hide();
            BoardSizeSelector boardSizeSelector = new BoardSizeSelector(primaryStage);
            boardSizeSelector.initSizeSelectionScreen();
        });

        alert.show();
    }


}