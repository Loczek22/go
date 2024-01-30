package GUI;

import Board.Board;
import Board.Player;
import GUI.BoardGUI;
import GUI.StoneGUI.StoneGUI;
import Server.Client;
import javafx.geometry.Insets;
import javafx.scene.Group;
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


public class GameGUI{

    public int getBoardSize() {
        return boardSize;
    }

    private final int boardSize;
    BoardGUI boardGUI;
    private boolean gameEnded = false;
    private boolean isYourTurn;
    private final Player player;
    Label playerMoveLabel;
    Canvas canvas;

    public GameGUI(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.player = player;
        this.playerMoveLabel = player.isYourTurn() ? new Label("Your turn") : new Label("Opponents move");
    }



    public void initGame() {
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        boardGUI = new BoardGUI(boardSize, 600 / boardSize, player.getStoneColor(), player, this);
        if (!player.isYourTurn()){
            Receiver receiver = new Receiver(player, boardGUI, this);
            Thread t = new Thread(receiver);
            t.start();
        }
        gameStage.setTitle("GO GAME");
        Group root = new Group();
        StackPane pane = new StackPane();
        root.getChildren().add(pane);
        root.setId("gameBoardRoot");
        canvas = new Canvas(640, 600);
        Button passButton = new Button("PASS");
        passButton.setId("passButton");
        Button surrenderButton = new Button("SURRENDER");
        surrenderButton.setId("doNotMoveButton");

        passButton.setOnAction(e -> {
            if(player.isYourTurn()){
                player.sendPass();
                String info = player.getMessageFromServer();
                if(info.equals("ok")){
                    String board = player.getMessageFromServer();
                    boardGUI.updateBoard(board);
                    player.setYourTurn(false);
                    Receiver receiver = new Receiver(player, boardGUI,this);
                    Thread t = new Thread(receiver);
                    t.start();
                }
                else{
                    String[] end = info.split(" ");
                    System.out.println(end[2]);
                    decideOnWinner(end[0], end[1]);
                }
            }
        });
        surrenderButton.setOnAction(e -> {
            if(player.isYourTurn()){
                player.giveUp();
                String info = player.getMessageFromServer();
                giveUp(info);
            }
        });

        HBox buttonBox = new HBox(passButton, surrenderButton);
        buttonBox.setSpacing(30);
        buttonBox.setAlignment(Pos.TOP_LEFT);

        VBox buttonLabelBox = new VBox(buttonBox, playerMoveLabel);
        buttonLabelBox.setSpacing(500);

        HBox hbox = new HBox(canvas, buttonLabelBox);
        hbox.setAlignment(Pos.CENTER);
        pane.getChildren().add(hbox);

        // margines
        HBox.setMargin(buttonLabelBox, new Insets(50, 50, 0, 0 ));

        drawB(canvas, boardGUI);
        Group group = createStones(boardGUI);
        root.getChildren().add(group);
        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.show();
    }

    public void updateLabel() {
        playerMoveLabel.setText(player.isYourTurn() ? "Your turn" : "Opponents turn");
    }
    public void updateLabel(String s){
        playerMoveLabel.setText(s);
    }

    public static Group createStones(BoardGUI boardGUI) {
        return boardGUI.createStones();
    }

    public static void drawB(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boardGUI.drawBoard(gc);
    }
    public void decideOnWinner(String white, String black) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("CHOOSE WINNER");
        ButtonType buttonWhite = new ButtonType("WHITE");
        ButtonType buttonBlack = new ButtonType("BLACK");
        alert.getButtonTypes().setAll(buttonWhite, buttonBlack);
        Label text = new Label("White "+white+" : "+black+ "Black");
        alert.getDialogPane().setContent(text);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonWhite) {
                updateLabel("WHITE WON");
            } else if (buttonType == buttonBlack) {
                updateLabel("BLACK 2 WON");
            }
            gameEnded = true;
        });
    }

    public void giveUp(String s){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(s);
        ButtonType button = new ButtonType("OK");
        alert.getButtonTypes().setAll(button);
        alert.showAndWait().ifPresent(buttonType -> {
            gameEnded = true;
        });
    }


}