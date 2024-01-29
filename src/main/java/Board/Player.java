package Board;

import GUI.Receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Player implements Runnable {
    private PrintWriter out;
    private BufferedReader in;

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }

    private boolean isYourTurn;

    private StoneColor stoneColor;
    private int score = 0;

    public Player(StoneColor stoneColor, PrintWriter out, BufferedReader in) {
        this.stoneColor = stoneColor;
        this.out = out;
        this.in = in;
        if(stoneColor == StoneColor.BLACK){
            isYourTurn = true;
        }else{
            isYourTurn = false;
        }
    }
    public Player(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public void setStoneColor(String color){
        if(color.equals("B")){
            stoneColor = StoneColor.BLACK;
        }else{
            stoneColor = StoneColor.WHITE;
        }
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
    public void giveUp(){
        out.println("l");
    }

    @Override
    public void run() {
        try {
            String info  = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}