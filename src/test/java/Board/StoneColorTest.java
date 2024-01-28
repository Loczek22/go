package Board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoneColorTest {

    @Test
    public void testStoneColor() {
        StoneColor stoneColor = StoneColor.BLACK;
        assertEquals('B', stoneColor.getSymbol());
    }

    @Test
    public void testStoneColor2() {
        StoneColor stoneColor = StoneColor.WHITE;
        assertEquals('W', stoneColor.getSymbol());
    }
}
