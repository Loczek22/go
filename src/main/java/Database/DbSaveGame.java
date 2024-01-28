package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSaveGame {
    private static final String MAX_ID = "SELECT MAX(game_id) FROM games";
    private static final String INSERT_GAME = "INSERT INTO games (game_id, move_id, x, y, color) VALUES (?, ?, ?, ?, ?)";
    private static final String MAX_MOVE = "SELECT MAX(move_id) FROM games WHERE game_id = ?";
    Connection conn = null;

    public int getIDGame() {
        int Id = 0;
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(MAX_ID)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Id = rs.getInt(1);
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return Id;
    }

    public void addMove(Move move) {
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_GAME)) {
                pstmt.setInt(1, move.getGameId());
                pstmt.setInt(2, move.getMoveId());
                pstmt.setInt(3, move.getX());
                pstmt.setInt(4, move.getY());
                pstmt.setString(5, move.getColor());
                pstmt.executeUpdate();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            JDBConnector.release(null, null, conn);
        }
    }
    public int getMoveId(int gameId) {
        int Id = 0;
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(MAX_MOVE)) {
                pstmt.setInt(1, gameId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return Id + 1;
    }


}
