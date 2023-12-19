public class Board {
    private int boardSize; //19, 13, 9
    private SingleStone [][] stones;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.stones = new SingleStone[boardSize][boardSize];
        resetAll();
    }

    public int getBoardSize() {
        return boardSize;
    }

    // resetowanie planszy
    public void resetAll() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                stones[i][j] = null;
            }
        }
    }

    public SingleStone getStoneAt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return stones[x][y];
        }
        return null;
    }

    public void placeStone(SingleStone stone) {
        int x = stone.getX();
        int y = stone.getY();

        if (isValidCoordinate(x, y)) {
            stones[x][y] = stone;
        }
    }
    public void removeStone(int x, int y) {
        if (isValidCoordinate(x, y)) {
            stones[x][y] = null;
        }
    }

    // obsługa wyjścia poza planszę
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    // czy ruch jest legalny
    public boolean isValidMove(int x, int y) {
        // TODO: logika poprawnośći ruchu
        return true;
    }

    public boolean isEmpty(int x, int y) {
        return isValidCoordinate(x, y) && stones[x][y] == null;
    }
}