package GUI;

import Board.Board;
import Board.Player;
import GUI.BoardGUI;
import Server.Client;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class GameGUI {

    private final int boardSize;
    BoardGUI boardGUI;
    private boolean gameEnded = false;
    private boolean isYourTurn = true;
    private final Client client;
    Canvas canvas;

    public GameGUI(int boardSize, Client client) {
        this.boardSize = boardSize;
        this.client = client;
    }

    Label playerMoveLabel = new Label("YOUR TURN");

    public void initGame() {
        Stage gameStage = new Stage();
        boardGUI = new BoardGUI(boardSize, 600 / boardSize);

        gameStage.setTitle("GO GAME");
        StackPane root = new StackPane();
        root.setId("gameBoardRoot");
        canvas = new Canvas(650, 600);
        Button confirmMoveButton = new Button("CONFIRM MOVE");
        confirmMoveButton.setId("confirmButton");
        Button doNotMoveButton = new Button("DON'T MOVE");
        doNotMoveButton.setId("doNotMoveButton");

        int cellSize = (int) (canvas.getHeight() / boardSize);

        canvas.setOnMouseClicked(e -> {
            handleMouseClick((int) e.getX(), (int) e.getY(), cellSize);
        });

        confirmMoveButton.setOnAction(e -> {
            handleConfirmMove();
        });

        doNotMoveButton.setOnAction(e -> {
            handleDoNotMove();
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

        drawB(canvas, boardGUI);

        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.show();
    }

    private void updateLabel(String text) {
        playerMoveLabel.setText(text);
    }

    public static void drawS(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //boardGUI.drawBoard(gc);
        boardGUI.drawStones(gc);
    }

    public static void drawB(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boardGUI.drawBoard(gc);
    }

    private void handleMouseClick(int x, int y, int cellSize) {
        if (!gameEnded && isYourTurn) {
            int clickedX = x / cellSize;
            int clickedY = y / cellSize;

            // sprawdzenie, czy kliknięcie nastąpiło w zakresie planszy
            if (clickedX >= 0 && clickedX < boardSize && clickedY >= 0 && clickedY < boardSize & boardGUI.isEmpty(clickedX, clickedY)) {

                // Sprawdzanie, czy na wybranym polu istnieje kamień
                if (boardGUI.hasStone(clickedX, clickedY)) {
                    boardGUI.removeStone(clickedX, clickedY);
                }
                boardGUI.placeStone(clickedX, clickedY);
                drawS(canvas, boardGUI);
                //isYourTurn = false;
            }
        }
    }

    private void handleConfirmMove() {
        // TODO: zmienić na rezygnacje
        /*if (!gameEnded) {
            int i;
            if (BoardGUI.getCurrentPlayer() == 1) {
                i = 2;
            } else {
                i = 1;
            }
            drawS(canvas, boardGUI);
            BoardGUI.setCurrentPlayer(i);
            updateLabel("WAIT FOR YOUR OPPONENT'S MOVE");
            client.sendMoveToServer(lastX, lastY);
            // odbierz z serwera
            // isYourTurn = true;
        }*/
    }

    private void handleDoNotMove() {
        if (!gameEnded) {
            int i;
            if (BoardGUI.getCurrentPlayer() == 1) {
                i = 2;
            } else {
                i = 1;
            }
            BoardGUI.setCurrentPlayer(i);
            updateLabel("WAIT FOR YOUR OPPONENT'S MOVE");
            //client.sendMoveToServer(-1, -1);
            //decideOnWinner();
        }
    }

    private void decideOnWinner() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("CHOOSE WINNER");
        ButtonType buttonWhite = new ButtonType("WHITE");
        ButtonType buttonBlack = new ButtonType("BLACK");
        alert.getButtonTypes().setAll(buttonWhite, buttonBlack);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonWhite) {
                updateLabel("PLAYER 1 WON");
            } else if (buttonType == buttonBlack) {
                updateLabel("PLAYER 2 WON");
            }
            gameEnded = true;
        });
    }
}