package Database;

import java.sql.Connection;
import java.io.IOException;
import java.sql.*;

public class DbAddGame {
    Connection conn = null;
    private static final String NEXT_ID = "SELECT COALESCE(MAX(game_id), 0) FROM games;";
    private static final String INSERT_GAMEID = "INSERT INTO games (game_id) VALUES (?);";
    public int getIdGame() {
        int Id = 0;
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(NEXT_ID)) {
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    Id = resultSet.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return Id + 1;
    }

    public void addGame() {
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_GAMEID)) {
                pstmt.setInt(1, getIdGame());

                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
    }
}