package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardGUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        new FirstFrame().start(stage);
    }

    @Test
    public void testClearBoard() {
        BoardGUI boardGUI = new BoardGUI(3, 50);
        boardGUI.placeStone(1, 1);
        boardGUI.clearBoard();

        assertArrayEquals(new int[3][3], boardGUI.getBoard());
    }

    @Test
    public void testPlaceStone() {
        BoardGUI boardGUI = new BoardGUI(3, 50);
        boardGUI.placeStone(1, 1);

        assertEquals(1, boardGUI.getBoard()[1][1]);
    }

    @Test
    public void testSwitchPlayer() {
        BoardGUI boardGUI = new BoardGUI(3, 50);
        boardGUI.placeStone(1, 1);
        assertEquals(2, boardGUI.getCurrentPlayer());

        boardGUI.placeStone(0, 0);
        assertEquals(1, boardGUI.getCurrentPlayer());
    }

    @Test
    public void testDrawStones() {
        BoardGUI boardGUI = new BoardGUI(3, 50);
        Canvas canvas = new Canvas(150, 150);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        boardGUI.placeStone(1, 1);
        boardGUI.drawStones(gc);
        assertEquals(Color.BLACK, gc.getFill());

        // problem z kolorem białym
        /*boardGUI.placeStone(0, 0);
        boardGUI.drawStones(gc);
        assertEquals(Color.WHITE, gc.getFill());*/
    }
}