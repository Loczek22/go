package Board;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StonesGroupTest {

    @Test
    public void testAddStone() {
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        group.addStone(stone);
        assertTrue(group.getStones().contains(stone));
    }

    @Test
    public void testMergeGroups() {
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        StonesGroup group2 = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        SingleStone stone2 = new SingleStone(StoneColor.BLACK, 6, 5);
        group.addStone(stone);
        group2.addStone(stone2);
        group.mergeGroups(group2);
        assertTrue(group.getStones().contains(stone));
        assertTrue(group.getStones().contains(stone2));
    }

    @Test
    public void testKillGroup() {
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        group.addStone(stone);
        group.killGroup();
        assertTrue(group.getStones().isEmpty());
    }

    @Test
    public void testRemoveOneBreath() {
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        group.setBreaths(2);
        group.removeOneBreath();
        assertTrue(group.getBreaths() == 1);
    }

    @Test
    public void testPrintGroup() {
        StonesGroup group = new StonesGroup(StoneColor.BLACK);
        SingleStone stone = new SingleStone(StoneColor.BLACK, 5, 5);
        SingleStone stone2 = new SingleStone(StoneColor.BLACK, 8, 5);
        group.addStone(stone);
        group.addStone(stone2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        group.printGroup();

        String expectedOutput  = "5 5\n8 5\n";
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }
}
