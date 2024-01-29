package Database;

import static org.junit.Assert.*;
import org.junit.*;
import java.sql.*;

public class DbAddGameTest {
    private Connection conn;

    @Before
    public void setUp() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/goGames", "goGameUser", "haslowdupewlazlo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIdGame() {
        DbAddGame dbAddGame = new DbAddGame();
        int gameId = dbAddGame.getIdGame();
        assertTrue(gameId >= 0);
    }
    @Test
    public void testAddGame() {
        DbAddGame dbAddGame = new DbAddGame();
        AddGameHandle addGameHandle = new AddGameHandle(dbAddGame);
        addGameHandle.handle();
    }
}
