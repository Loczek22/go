package Server;

import Board.StoneColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerPlayer {
    private PrintWriter out;
    private BufferedReader in;

    private StoneColor stoneColor;
    private int score = 0;

    public ServerPlayer(StoneColor stoneColor, PrintWriter out, BufferedReader in) {
        this.stoneColor = stoneColor;
        this.out = out;
        this.in = in;
    }
    public ServerPlayer(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public int getScore() {
        return score;
    }
    public StoneColor getStoneColor() {
        return stoneColor;
    }

    public String receiveInfo(){
        try{
            return in.readLine();
        }catch (IOException ex){
            return "Brak połączenia z klientem";
        }
    }
    public void sendInfo(String info){
        out.println(info);
    }
}
