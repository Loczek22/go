package Database;

import static org.junit.Assert.*;
import org.junit.*;
import java.sql.*;

public class JDBConnectorTest {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Before
    public void setUp() throws Exception {
        conn = JDBConnector.getConnection();
        stmt = conn.createStatement();
    }

    @After
    public void tearDown() throws Exception {
        JDBConnector.release(rs, stmt, conn);
    }

    @Test
    public void testConnection() {
        assertNotNull(conn);
    }

    @Test
    public void testStatement() {
        assertNotNull(stmt);
    }

    @Test
    public void testQuery() throws SQLException {
        rs = stmt.executeQuery("SELECT 1");
        assertTrue(rs.next());
    }
}