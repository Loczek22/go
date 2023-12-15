public class Board {
    private int BoardSize;
    private Stone [][] stones;

    public Board(int boardSize) {
        BoardSize = boardSize;
        this.stones = new Stone[BoardSize][BoardSize];
    }

    // czy ruch jest legalny
    public boolean isValidMove(int x, int y) {
        return true;
    }

    public void stoneMove(StoneColor color, int x, int y) {
        stones[x][y] = new Stone(color, x, y);
    }
}
