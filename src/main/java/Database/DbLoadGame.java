package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DbLoadGame {
    Connection conn = null;
    private static final String SELECT_MOVE = "SELECT x, y, color FROM games WHERE game_id = ? AND move_id = ?";

    public Move getMove(int gameId, int moveId) {
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(SELECT_MOVE)) {
                pstmt.setInt(1, gameId);
                pstmt.setInt(2, moveId);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    Move move = new Move.Builder(gameId, moveId)
                            .x(Integer.parseInt(resultSet.getString(2)))
                            .y(Integer.parseInt(resultSet.getString(3)))
                            .color(resultSet.getString(4))
                            .build();
                    return move;
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return null;
    }
}
