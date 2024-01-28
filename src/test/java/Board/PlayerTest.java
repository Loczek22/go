package Board;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testGetScore() {
        Player player = new Player(StoneColor.BLACK, null, null);
        assertEquals(0, player.getScore());
    }

    @Test
    public void testGetColor() {
        Player player = new Player(StoneColor.BLACK, null, null);
        assertEquals(StoneColor.BLACK, player.getStoneColor());
    }

    @Test
    public void testGetMessageFromServer() {
        String message = "Test get message from server";
        BufferedReader in = new BufferedReader(new StringReader(message));
        Player player = new Player(null, in);
        assertEquals(message, player.getMessageFromServer());
    }

    @Test
    public void testSendMove() {
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Player player = new Player(printWriter, null);
        player.sendMove(3, 4);
        assertEquals("m 3 4\n", out.toString());
    }

    @Test
    public void testSendPass() {
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Player player = new Player(printWriter, null);
        player.sendPass();
        assertEquals("p\n", out.toString());
    }
}
