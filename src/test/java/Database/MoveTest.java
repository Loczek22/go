package Database;

import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testMoveBuilder() {
        int gameId = 6;
        int moveId = 7;
        int x = 3;
        int y = 4;
        String color = "WHITE";
        String moveType = "add"; // "delete" or "add"

        Move move = new Move.Builder(gameId, moveId)
                .x(x)
                .y(y)
                .color(color)
                .moveType(moveType)
                .build();

        assertNotNull(move);
        assertEquals(gameId, move.getGameId());
        assertEquals(moveId, move.getMoveId());
        assertEquals(x, move.getX());
        assertEquals(y, move.getY());
        assertEquals(color, move.getColor());
        assertEquals(moveType, move.getMoveType());
    }
}
