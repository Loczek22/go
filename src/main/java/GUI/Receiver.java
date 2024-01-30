package GUI;

import Board.Player;
import javafx.application.Platform;

public class Receiver implements Runnable {
    private final Player player;
    private final BoardGUI boardGui;
    private final GameGUI gameGUI;
    public Receiver(Player player, BoardGUI boardGui, GameGUI gameGUI){
        this.player = player;
        this.boardGui = boardGui;
        this.gameGUI = gameGUI;
    }
    @Override
    public void run() {

        String info = player.getMessageFromServer();
        synchronized (boardGui){
            String[] end = info.split(" ");
            if(end.length>1 && (end[2].equals("b") || end[2].equals("w") || end[2].equals("d"))){
                gameGUI.decideOnWinner(end[0], end[1]);
            }else{
                player.setYourTurn(true);
                boardGui.updateBoard(info);
            }
        }
    }
}
