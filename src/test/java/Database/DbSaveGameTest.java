package Database;
import static org.junit.Assert.*;
import org.junit.*;
import java.sql.*;

public class DbSaveGameTest {
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
    public void testGetIDGame() {
        DbSaveGame dbSaveGame = new DbSaveGame();
        int idGame = dbSaveGame.getIDGame();
        assertTrue(idGame >= 0);
    }

/*    @Test
    public void testAddMove() {
        DbSaveGame dbSaveGame = new DbSaveGame();
        int gameId = 7;
        int x = 2;
        int y = 1;
        Move move = new Move.Builder(gameId, dbSaveGame.getMoveId(gameId))
                .x(x)
                .y(y)
                .color("WHITE")
                .moveType("add")
                .build();
        AddMoveHandle addMoveHandle = new AddMoveHandle(dbSaveGame);
        addMoveHandle.handle(move);
    }*/

    @Test
    public void testGetMoveId() {
        DbSaveGame dbSaveGame = new DbSaveGame();
        int moveId = dbSaveGame.getMoveId(1);
        assertTrue(moveId >= 0);
    }
}
