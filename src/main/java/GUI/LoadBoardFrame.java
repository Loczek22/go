package GUI;

import Database.DbLoadGame;
import Database.DbSaveGame;
import Database.Move;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoadBoardFrame {

    private static Map<Integer,Integer> moves; // todo
    Canvas canvas;
    private static int gameId;
    private final Stage primaryStage;
    private static int maxMoveId;

    public LoadBoardFrame(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameId = gameId;
    }

    public void initLoadBoard(int boardSize) {

        DbSaveGame dbSaveGame = new DbSaveGame();
        DbLoadGame dbLoadGame = new DbLoadGame();
        Move move;
        maxMoveId = dbSaveGame.getMoveId(gameId);
        int[] xOfMove = new int[maxMoveId-1];
        int[] yOfMove = new int[maxMoveId-1];
        String[] colorOfMove = new String[maxMoveId-1];
        String[] typeOfMove = new String[maxMoveId-1];
        String winner = dbLoadGame.getWinner(gameId);

        int x;
        int y;

        ArrayList<Integer> xToDelete = new ArrayList<Integer>();
        ArrayList<Integer> yToDelete = new ArrayList<Integer>();

        for (int i = 1; i < maxMoveId; i++) {
            move = dbLoadGame.getMove(gameId, i);
            typeOfMove[i-1] = move.getMoveType();
            xOfMove[i-1] = move.getX();
            yOfMove[i-1] = move.getY();
            colorOfMove[i-1] = move.getColor();
        }

        moves = new HashMap<>();

        Stage gameStage = new Stage();

        gameStage.setTitle("GO GAME");
        Group root = new Group();
        StackPane pane = new StackPane();
        root.getChildren().add(pane);
        root.setId("gameBoardRoot");
        canvas = new Canvas(640, 600);
        Button nextMoveButton = new Button("NEXT MOVE");

        nextMoveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("B"+maxMoveId);
                int i = 0;
                if(i < maxMoveId-1){
                    int x = xOfMove[i];
                    int y = yOfMove[i];

                    String colorString = colorOfMove[i];
                    Color color = null;
                    if (Objects.equals(colorString, "WHITE")) {
                        color = Color.WHITE;
                    }
                    else if (Objects.equals(colorString, "BLACK")) {
                        color = Color.BLACK;
                    }

                    if(Objects.equals(typeOfMove[i], "add")) {
                        addStone(x, y, color);
                        System.out.printf(String.valueOf(x));
                        System.out.printf(String.valueOf(y));
                        for(int j = 0; j < xToDelete.size(); j++){
                            removeStone(xToDelete.get(j), yToDelete.get(j));
                        }
                        xToDelete.clear();
                        yToDelete.clear();
                        // update board
                    }
                    i++;
                }
                else if (i == maxMoveId-1) {
                    System.out.printf("GAME OVER");
                }
            }
        });

        VBox vbox = new VBox(nextMoveButton);
        vbox.setAlignment(Pos.TOP_CENTER);

        HBox hbox = new HBox(canvas, vbox);
        hbox.setAlignment(Pos.CENTER);
        pane.getChildren().add(hbox);

        HBox.setMargin(vbox, new Insets(50, 50, 0, 0 ));

        drawBoard(canvas.getGraphicsContext2D(), boardSize);

        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.show();
    }

    public void drawBoard(GraphicsContext gc, int boardSize) {
        int cellSize = 600 / boardSize;
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, boardSize * cellSize, boardSize * cellSize);

        gc.setStroke(Color.BLACK);
        for (int k = 0; k < boardSize; k++) {
            double xLine = k * cellSize + cellSize / 2.0;
            double yLine = k * cellSize + cellSize / 2.0;

            gc.strokeLine(xLine, 0, xLine, (boardSize - 1) * cellSize + cellSize);
            gc.strokeLine(0, yLine, (boardSize - 1) * cellSize + cellSize, yLine);
        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, boardSize * cellSize, boardSize * cellSize);
    }


    private void addStone(int x, int y, Color color) {

    }

    private void removeStone(int x, int y) {

    }
}
