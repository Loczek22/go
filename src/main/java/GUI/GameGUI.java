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


public class GameGUI implements Runnable{

    public int getBoardSize() {
        return boardSize;
    }

    private final int boardSize;
    BoardGUI boardGUI;
    private boolean gameEnded = false;
    private boolean isYourTurn = true;
    private final Player player;
    Canvas canvas;

    public GameGUI(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.player = player;
    }

    Label playerMoveLabel = new Label("YOUR TURN");

    public void initGame() {
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        boardGUI = new BoardGUI(boardSize, 600 / boardSize, player.getStoneColor(), player, this);
        gameStage.setTitle("GO GAME");
        Group root = new Group();
        StackPane pane = new StackPane();
        root.getChildren().add(pane);
        root.setId("gameBoardRoot");
        canvas = new Canvas(640, 600);
        Button confirmMoveButton = new Button("CONFIRM MOVE");
        confirmMoveButton.setId("confirmButton");
        Button doNotMoveButton = new Button("DON'T MOVE");
        doNotMoveButton.setId("doNotMoveButton");



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

    private void updateLabel(String text) {
        playerMoveLabel.setText(text);
    }

    public static Group createStones(BoardGUI boardGUI) {
        return boardGUI.createStones();
    }

    public static void drawB(Canvas canvas, BoardGUI boardGUI) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boardGUI.drawBoard(gc);
    }


    private void handleDoNotMove() {
        if (!gameEnded) {
            int i;
            if (true) {
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

    @Override
    public void run() {
        try {
            while (!gameEnded) {
                if (isYourTurn) {
                    waitForMove();
                    System.out.println("KUTAS");
                    //sendMove(boardGUI.getStone());
                    receiveInfoFromServer();

                }
            }
        }catch(InterruptedException ex){}
    }

    public void waitForMove() throws InterruptedException{
        while(!(isYourTurn && boardGUI.getStone() == null) || !isYourTurn){
            Thread.sleep(100);
        }
        System.out.println("CHUJ");
    }
    /*public void sendMove(StoneGUI stone){
        System.out.println("vsoub1"+player.isYourTurn());
        if(player.isYourTurn()){
            player.sendMove(stone.getX(), stone.getY());
            boardGUI.setStone(null);
            String info = player.getMessageFromServer();
            System.out.println(info);
            if(info.equals("ok")){
                System.out.println("vsoub2");
                stone.changeState(player.getStoneColor());
                player.setYourTurn(false);
                System.out.println("vsoub3");
                player.getMessageFromServer();
            }else{
                stone.changeState(player.getStoneColor());
            }
        }
    }*/
    public void receiveInfoFromServer() {
        String info = player.getMessageFromServer();
    }
}