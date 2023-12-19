package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardGUI {

    private final int[][] board;
    private final int cellSize;
    private int currentPlayer;

    public BoardGUI(int size, int cellSize) {
        this.board = new int[size][size];
        this.cellSize = cellSize;
        this.currentPlayer = 1;
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }
    public void drawBoard(GraphicsContext gc) {
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, board.length * cellSize, board.length * cellSize);

        gc.setStroke(Color.BLACK);
        for (int k = 0; k < board.length; k++) {
            double xLine = k * cellSize + cellSize / 2.0;
            double yLine = k * cellSize + cellSize / 2.0;

            gc.strokeLine(xLine, 0, xLine, (board[0].length - 1) * cellSize + cellSize);
            gc.strokeLine(0, yLine, (board.length - 1) * cellSize + cellSize, yLine);
        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, board.length * cellSize, board.length * cellSize);
    }

    public void drawStones(GraphicsContext gc) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int stone = board[i][j];

                if (stone != 0) {
                    gc.setFill(stone == 1 ? Color.BLACK : Color.WHITE);
                    double x = i * cellSize + cellSize / 4.0;
                    double y = j * cellSize + cellSize / 4.0;

                    gc.fillOval(x, y, cellSize / 2.0, cellSize / 2.0);
                }
            }
        }
    }

    public void placeStone(int x, int y) {
        // TODO: implementacja logiki gry, uwzględniająca gracza (1 lub 2)
        if(board[x][y] == 0) {
            board[x][y] = currentPlayer;
            currentPlayer = 3 - currentPlayer;
        }
    }
}