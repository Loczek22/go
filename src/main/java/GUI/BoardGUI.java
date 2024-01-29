package GUI;

import Board.Player;
import Board.StoneColor;
import GUI.StoneGUI.StoneGUI;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardGUI {

    private final StoneGUI[][] board;
    private final int cellSize;
    private static Player player;
    private GameGUI gameGui;
    private StoneGUI move;

    public BoardGUI(int size, int cellSize, StoneColor color, Player player, GameGUI gameGUI) {
        this.board = new StoneGUI[size][size];
        this.cellSize = cellSize;
        this.player = player;
        this.gameGui = gameGUI;
    }

    public static void setCurrentPlayer(int i) {
        //currentPlayer = i;
    }

    public boolean isEmpty(int x, int y) {
        if(!board[x][y].isVisible()) {
            return true;
        } else {
            return false;
        }
    }


    public void drawBoard(GraphicsContext gc) {
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, board.length * cellSize, board.length * cellSize);

        gc.setStroke(Color.BLACK);
        for (int k = 0; k < board.length; k++) {
            double xLine = k * cellSize + cellSize / 2.0;
            double yLine = k * cellSize + cellSize / 2.0;

            gc.strokeLine(xLine, 0, xLine, (board[0].length - 1) * cellSize + cellSize);
            gc.strokeLine(0, yLine, (board.length - 1) * cellSize + cellSize, yLine);
        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, board.length * cellSize, board.length * cellSize);
    }

    public Group createStones() {
        Group group = new Group();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                double radius = cellSize / 4.0;
                double x = i * cellSize + cellSize / 4.0;
                double y = j * cellSize + cellSize / 4.0;
                StoneGUI stone = new StoneGUI(StoneColor.BLACK, x, y, radius, i, j);
                stone.setOnMouseClicked(e -> {
                    System.out.println(stone.getFill());
                    if(player.isYourTurn()){
                        player.sendMove(stone.getX(), stone.getY());
                        String answer = player.getMessageFromServer();
                        System.out.println(answer);
                        System.out.println(stone.getFill());
                        if(answer.equals("ok")){
                            String board = player.getMessageFromServer();
                            System.out.println(board);
                            updateBoard(board);
                            player.setYourTurn(false);
                            Receiver receiver = new Receiver(player, this);
                            Thread t = new Thread(receiver);
                            t.start();
                        }
                        else{
                            System.out.println("chuj");
                        }
                    }

                });
                group.getChildren().add(stone);
                board[i][j] = stone;
            }
        }
        return group;
    }

    /*public void placeStone(int x, int y) {
        if(board[x][y].getColor() != Color.TRANSPARENT) {
            board[x][y].changeState(player.getStoneColor());
        }
    }*/
    public StoneGUI getStone(){
        return move;
    }
    public void setStone(StoneGUI stone){
        move = stone;
    }



    public StoneGUI[][] getBoard() {
        return board;
    }

    public void updateBoard(String boardstr){
        int index=0;
        for(int x = 0; x < gameGui.getBoardSize(); x++) {
            for (int y = 0; y < gameGui.getBoardSize(); y++) {
                switch (boardstr.charAt(index)) {
                    case 'B':
                        System.out.println(x+ " " + y);
                        board[x][y].setFill(Color.BLACK);
                        //board[x][y].
                        System.out.println(board[x][y].getFill());
                        break;
                    case 'W':
                        board[x][y].setFill(Color.WHITE);
                        break;
                    case '0':
                        board[x][y].setFill(Color.TRANSPARENT);
                        break;
                }
                index +=1 ;

            }
        }
    }
}