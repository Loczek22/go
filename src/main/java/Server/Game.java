package Server;

import Board.*;
import Database.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.*;
import java.util.Arrays;




public class Game extends Thread {
    private Socket socket1, socket2;
    private boolean gameIsOver = false;
    private Board board;
    private ServerPlayer PlayerB;
    private ServerPlayer PlayerW;
    private ServerPlayer currentPlayer;
    private ServerPlayer otherPlayer;
    private int boardSize;
    private static int gameId;

    public Game(Socket socket1, Socket socket2, int boardSize) {
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.board = new Board(boardSize);
        this.boardSize = boardSize;

        DbSaveGame dbSaveGame = new DbSaveGame();
        gameId = dbSaveGame.getIDGame();
    }

    public void run() {

        try {
            //Odbieranie od socketa1
            InputStream input1 = socket1.getInputStream();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(input1));

            //Odbieranie od socketa2
            InputStream input2 = socket2.getInputStream();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(input2));

            //Wysylanie do socketa1
            OutputStream output1 = socket1.getOutputStream();
            PrintWriter out1 = new PrintWriter(output1, true);

            //Wysylanie do socketa2
            OutputStream output2 = socket2.getOutputStream();
            PrintWriter out2 = new PrintWriter(output2, true);

            sendMessageToPlayers("Rozpoczynanie gry", out1, out2);

            PlayerB = new ServerPlayer(StoneColor.BLACK, out1, in1);
            PlayerW = new ServerPlayer(StoneColor.WHITE, out2, in2);

            currentPlayer = PlayerB;
            otherPlayer = PlayerW;
            boolean previousTurnPass = false;
            do {
                System.out.println("chuj");
                String[] move = currentPlayer.receiveInfo().split(" ");
                System.out.println("chuj2");
                if(move[0].equals("p")){
                    passMoveToDatabase(gameId, Integer.parseInt(move[1]), Integer.parseInt(move[2]));
                    if(previousTurnPass){
                        String info = board.endGame();
                        sendMessageToPlayers(info, out1, out2);
                    }
                    previousTurnPass = true;
                    changeCurrentPlayer();
                    sendMessageToPlayers(boardToString(), out1, out2);
                } else if (move[0].equals("m")) {
                    System.out.println("move");
                    SingleStone stone = new SingleStone(currentPlayer.getStoneColor(), Integer.parseInt(move[1]), Integer.parseInt(move[2]));
                    if(board.placeStone(stone)){
                        board.updateGroups(stone);
                        board.updateBreaths();
                        System.out.println("moveinfo");
                        currentPlayer.sendInfo("ok");
                        System.out.println("moveok");
                        addMoveToDatabase(gameId, Integer.parseInt(move[1]), Integer.parseInt(move[2]));
                        previousTurnPass = false;
                        sendMessageToPlayers(boardToString(), out1, out2);
                        changeCurrentPlayer();
                    }else{
                        currentPlayer.sendInfo("Incorrect move");
                        sendMessageToPlayers(boardToString(), out1, out2);
                    }
                } else if (move[0].equals("l")) {

                    gameIsOver = true;
                    String info;
                    if(otherPlayer.getStoneColor() == StoneColor.BLACK){
                        info = "BLACK WINS";
                    }else{
                        info = "WHITE WINS";
                    }
                    sendMessageToPlayers(info, out1, out2);
                }


            } while (!gameIsOver);



            socket1.close();
            socket2.close();
        } catch (IOException ex) {
            System.out.println("Server.Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

   public void addMoveToDatabase(int gameId, int x, int y) {
        DbSaveGame dbSaveGame = new DbSaveGame();
        Color playerColor = currentPlayer.getStoneColor() == StoneColor.BLACK ? Color.BLACK : Color.WHITE;
        Move move = new Move.Builder(gameId, dbSaveGame.getMoveId(gameId))
                .x(x)
                .y(y)
                .color((playerColor == Color.BLACK) ? "BLACK" : "WHITE")
                .moveType("add")
                .build();

        AddMoveHandle addMoveHandle = new AddMoveHandle(dbSaveGame);
        addMoveHandle.handle(move);
    }

    public void passMoveToDatabase(int gameId, int x, int y) {
        DbSaveGame dbSaveGame = new DbSaveGame();
        Color playerColor = currentPlayer.getStoneColor() == StoneColor.BLACK ? Color.BLACK : Color.WHITE;
        Move move = new Move.Builder(gameId, dbSaveGame.getMoveId(gameId))
                .x(x)
                .y(y)
                .color((playerColor == Color.BLACK) ? "BLACK" : "WHITE")
                .moveType("pass")
                .build();

        AddMoveHandle addMoveHandle = new AddMoveHandle(dbSaveGame);
        addMoveHandle.handle(move);
    }
    private void sendMessageToPlayers(String message, PrintWriter out1, PrintWriter out2){
        out1.println(message);
        out2.println(message);
    }
    private void changeCurrentPlayer(){
        if(currentPlayer == PlayerB){
            currentPlayer = PlayerW;
            otherPlayer = PlayerB;
        }
        else{
            currentPlayer = PlayerB;
            otherPlayer = PlayerW;
        }
    }
    public String boardToString(){
        String boardstr="";
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                if(board.getStones()[x][y] != null) {
                    boardstr += board.getStones()[x][y].getColor() == StoneColor.BLACK ? "B" : "W";
                }else{
                    boardstr +="0";
                }
            }
        }
        return boardstr;
    }
}