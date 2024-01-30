package Server;

import Database.AddGameHandle;
import Database.DbAddGame;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerToGameAdapter {
    public Socket socket9;
    protected PrintWriter out9;
    public Socket socket13;
    protected PrintWriter out13;
    public Socket socket19;
    protected PrintWriter out19;

    public PlayerToGameAdapter() {
        this.socket9 = null;
        this.socket13 = null;
        this.socket19 = null;
    }

    public void addNewPlayer(Socket socket, int targetBoardSize) {
        try{
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
            switch(targetBoardSize){
                case 9:
                    if(socket9 == null){
                        socket9 = socket;
                        out9 = out;
                        out.println("Oczekiwanie na drugiego gracza");
                        saveToDatabase();
                    }else{
                        sendMessageGameStarts(out, out9, 9);
                        new Game(socket9, socket, 9).start();
                        socket9 = null;
                    }
                    break;
                case 13:
                    if(socket13 == null){
                        socket13 = socket;
                        out13 = out;
                        out.println("Oczekiwanie na drugiego gracza");
                        saveToDatabase();
                    }else{
                        sendMessageGameStarts(out, out13, 13);
                        new Game(socket13, socket, 13).start();
                        socket13 = null;
                    }
                    break;
                case 19:
                    if(socket19 == null){
                        socket19 = socket;
                        out19 = out;
                        out.println("Oczekiwanie na drugiego gracza");
                        saveToDatabase();
                    }else{
                        new Game(socket19, socket, 19).start();
                        sendMessageGameStarts(out, out19, 19);
                        socket19 = null;
                    }
                    break;
            }
        }catch (IOException | InterruptedException ex) {
            System.out.println("Server.Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    public void sendMessageGameStarts(PrintWriter out1, PrintWriter out2, int boardSize){
        out1.println("Znaleziono grę");
        out2.println("Znaleziono grę");
        System.out.println("Nowa gra " + boardSize + " startuje");
    }

    private void saveToDatabase() throws InterruptedException {
        DbAddGame dbAddGame = new DbAddGame();
        AddGameHandle addGameHandle = new AddGameHandle(dbAddGame);
        addGameHandle.handle();
    }
}