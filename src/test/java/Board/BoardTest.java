package Board;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BoardTest {

    @Test
    public void testResetAll() {
        Board board = new Board(19);
        board.stones[0][0] = new SingleStone(StoneColor.BLACK, 0, 0);
        board.resetAll();
        assertNull(board.stones[0][0]);
    }

    @Test
    public void testCalculateBreaths() {
        Board board = new Board(19);
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        group.addStone(new SingleStone(StoneColor.BLACK, 1, 1));

        int breaths = board.calculateBreaths(group);
        assertEquals(4, breaths);
    }

    @Test
    void testCheckKo() {
        Board board = new Board(9);
        board.stones[1][1] = new SingleStone(StoneColor.BLACK, 1, 1);
        board.stones[0][1] = new SingleStone(StoneColor.WHITE, 0, 1);
        board.stones[2][1] = new SingleStone(StoneColor.WHITE, 2, 1);
        board.stones[1][0] = new SingleStone(StoneColor.WHITE, 1, 0);
        board.stones[1][2] = new SingleStone(StoneColor.WHITE, 1, 2);

        assertTrue(board.checkKo(1, 1, 1, 1));
    }

    @Test
    public void testPlaceStone() {
        Board board = new Board(19);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        board.placeStone(stone);
        assertEquals(stone, board.stones[5][5]);
    }
    @Test
    void testIsEmpty() {
        Board board = new Board(9);
        board.stones[0][0] = new SingleStone(StoneColor.BLACK, 0, 0);

        assertFalse(board.isEmpty(0, 0));
        assertTrue(board.isEmpty(1, 1));
    }

    @Test
    void testPrintBoard() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Board board = new Board(9);
        board.stones[1][1] = new SingleStone(StoneColor.BLACK, 1, 1);
        board.printBoard();

        String printedBoard = outputStream.toString().trim();

        assertTrue(printedBoard.contains("B"));
        assertFalse(printedBoard.contains("W"));
    }
    @Test
    public void testCountPointsOfGroup() {
        Board board = new Board(19);
        assertEquals(1, board.countPointsOfGroup(List.of(new int[]{0, 0})));
    }

   /* @Test
    public void testShowGameBoard() {


        Board board = new Board(19);
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        board.placeStone(stone);
        group.addStone(stone);
        stone = new SingleStone(StoneColor.BLACK, 6, 5);
        board.placeStone(stone);
        group.addStone(stone);
        stone = new SingleStone(StoneColor.BLACK, 6, 6);
        board.placeStone(stone);
        group.addStone(stone);
        System.out.println(group.getBreaths());

        assertEquals(8, board.checkBreathsForNewStone(5, 6));

    }*/
}
