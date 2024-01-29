package GUI;

import Board.Player;

public class Receiver implements Runnable {
    private Player player;
    private BoardGUI boardGui;
    public Receiver(Player player, BoardGUI boardGui){
        this.player = player;
        this.boardGui = boardGui;
    }
    @Override
    public void run() {

        String info = player.getMessageFromServer();
        synchronized (boardGui){
            boardGui.updateBoard(info);
            player.setYourTurn(true);
        }
    }
}
