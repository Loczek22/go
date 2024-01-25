package Server;

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
                    }else{
                        sendMessageGameStarts(out, out9, 9);
                        new Game(socket9, socket).start();
                        socket9 = null;
                    }
                    break;
                case 13:
                    if(socket13 == null){
                        socket13 = socket;
                        out13 = out;
                        out.println("Oczekiwanie na drugiego gracza");
                    }else{
                        sendMessageGameStarts(out, out13, 13);
                        new Game(socket13, socket).start();
                        socket13 = null;
                    }
                    break;
                case 19:
                    if(socket19 == null){
                        socket19 = socket;
                        out19 = out;
                        out.println("Oczekiwanie na drugiego gracza");
                    }else{
                        new Game(socket19, socket).start();
                        sendMessageGameStarts(out, out19, 19);
                        socket19 = null;
                    }
                    break;
            }
        }catch (IOException ex) {
            System.out.println("Server.Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    public void sendMessageGameStarts(PrintWriter out1, PrintWriter out2, int boardSize){
        out1.println("Znaleziono grę");
        out2.println("Znaleziono grę");
        System.out.println("Nowa gra " + boardSize + " startuje");
    }
}
