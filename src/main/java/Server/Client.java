package Server;

import Board.Player;
import Board.StoneColor;
import GUI.BoardSizeSelector;
import GUI.GameGUI;
import GUI.WaitForSecondPlayer;

import java.net.*;
import java.io.*;


public class Client {
    private int boardSize;
    private Socket socket;

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
    StoneColor color;

    private PrintWriter out;
    private BufferedReader in;
    public Client(int boardSize){
        this.boardSize = boardSize;
        try  {

            socket = new Socket("localhost", 4444);
            // Wysylanie do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(boardSize);

            // Pierwszy komunikat od serwera (czy znaleziono drugiego gracza)
            String first_message = in.readLine();
            System.out.println(first_message);

            if("Oczekiwanie na drugiego gracza".equals(first_message)) {
                color = StoneColor.BLACK;
                System.out.println(in.readLine());
                //WaitForSecondPlayer wait = new WaitForSecondPlayer();
            }
            else{
                color = StoneColor.WHITE;
            }
            System.out.println(in.readLine());

        } catch (UnknownHostException ex) {
            System.out.println("Server.Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    public void start() {
        GameGUI gameGUI = new GameGUI(boardSize, new Player(color, out, in));
        gameGUI.initGame();
        //Thread t = new Thread(gameGUI);
        //t.start();
    }
}