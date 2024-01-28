package Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Player {
    private PrintWriter out;
    private BufferedReader in;

    private StoneColor stoneColor;
    private int score = 0;

    public Player(StoneColor stoneColor, PrintWriter out, BufferedReader in) {
        this.stoneColor = stoneColor;
        this.out = out;
        this.in = in;
    }
    public Player(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public int getScore() {
        return score;
    }
    public StoneColor getStoneColor() {
        return stoneColor;
    }
    public String getMessageFromServer(){
        try{
            return in.readLine();
        }catch (IOException ex){
            return "Brak połączenia z serwerem";
        }
    }
    public void sendMove(int x, int y){
        out.println("m "+x+" "+y);
    }
    public void sendPass(){
        out.println("p");
    }

}