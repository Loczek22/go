package Board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test
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

    }
}
