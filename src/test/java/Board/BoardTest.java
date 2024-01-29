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

    @Test
    public void testUpdateBreaths() {
        Board board = new Board(19);
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        SingleStone stone2 = new SingleStone(StoneColor.BLACK, 6, 5);
        board.placeStone(stone);
        group.addStone(stone);
        board.updateBreaths();
        assertEquals(4, group.getBreaths());
        board.placeStone(stone2);
        group.addStone(stone2);
        board.updateBreaths();
        assertEquals(6, group.getBreaths());
    }

    @Test
    public void testCalculateBreaths() {
        Board board = new Board(19);
        SingleStone stone =new SingleStone(StoneColor.BLACK, 1, 1);
        board.placeStone(stone);
        board.updateGroups(stone);
        board.updateBreaths();

        int breaths = board.calculateBreaths(stone.getGroup());
        assertEquals(4, breaths);

        SingleStone stone2 = new SingleStone(StoneColor.BLACK, 1, 2);
        board.placeStone(stone2);
        board.updateGroups(stone2);
        board.updateBreaths();
        int breaths2 = board.calculateBreaths(stone2.getGroup());

        assertEquals(6, breaths2);
    }

    @Test
    public void testUpdateGroups() {
        Board board = new Board(19);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        board.placeStone(stone);
        board.updateGroups(stone);
        assertEquals(stone.getGroup(), board.stones[5][5].getGroup());
    }

    @Test
    public void testRemoveGroup() {
        Board board = new Board(19);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        board.placeStone(stone);
        board.updateGroups(stone);
        board.removeGroup(stone.getGroup());
        assertNull(board.stones[5][5]);
    }

/*    @Test
    public void testWillKill() {
        Board board = new Board(19);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        board.placeStone(stone);
        board.updateGroups(stone);

        SingleStone stone2 = new SingleStone(StoneColor.WHITE, 5, 6);
        SingleStone stone3 = new SingleStone(StoneColor.WHITE, 6, 5);
        SingleStone stone4 = new SingleStone(StoneColor.WHITE, 4, 5);
        SingleStone stone5 = new SingleStone(StoneColor.WHITE, 5, 4);

        board.placeStone(stone2);
        board.updateGroups(stone2);
        board.placeStone(stone3);
        board.updateGroups(stone3);
        board.placeStone(stone4);
        board.updateGroups(stone4);
        board.placeStone(stone5);
        board.updateGroups(stone5);

        board.updateBreaths();

        assertTrue(board.willDie(5,5));
    }*/
}