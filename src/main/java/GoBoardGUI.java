import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GoBoardGUI {
    private final int[][] board;
    private final int cellSize;

    public GoBoardGUI(int size, int cellSize) {
        this.board = new int[size][size];
        this.cellSize = cellSize;
    }

    public void draw(GraphicsContext gc) {
        // rysowanie kamieni
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int stone = board[i][j];

                if (stone == 1) {
                    gc.setFill(Color.BLACK);
                    // obliczanie współrzędnych kamienia
                    double x = i * cellSize + cellSize / 4.0;
                    double y = j * cellSize + cellSize / 4.0;

                    //gc.setStroke(Color.BLACK);
                    gc.fillOval(x, y, cellSize / 2.0, cellSize / 2.0);
                    // TODO: rysowanie białych kamieni
                }
            }
        }
        // rysowanie linii
        gc.setStroke(Color.BLACK);
        for (int k = 0; k < board.length; k++) {
            double xLine = k * cellSize + cellSize / 2.0;
            double yLine= k * cellSize + cellSize / 2.0;

            gc.strokeLine( xLine, 0, xLine, (board[0].length - 1) * cellSize + cellSize);
            gc.strokeLine(0, yLine, (board.length - 1) * cellSize + cellSize, yLine);
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    public void placeStone(int x, int y) {
        // TODO: implementacja logiki gry
        board[x][y] = 1; // wstępnie umieszczenie czarnego kamienia
    }
}