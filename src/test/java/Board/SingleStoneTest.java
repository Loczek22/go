package Board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleStoneTest {

    @Test
    public void testSingleStone() {
        StoneColor color = StoneColor.BLACK;
        int x = 1;
        int y = 7;
        SingleStone singleStone = new SingleStone(color, x, y);
        assertEquals(color, singleStone.getColor());
        assertEquals(x, singleStone.getX());
        assertEquals(y, singleStone.getY());
    }
}
