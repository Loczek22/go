package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DbLoadGame {
    Connection conn = null;
    private static final String SELECT_MOVE = "SELECT x, y, color, move_type FROM games WHERE game_id = ? AND move_id = ?";
    private static final String SELECT_WINNER = "SELECT winner FROM boards WHERE game_id = ?";
    private static final String SELECT_BOARD_SIZE = "SELECT boardSize FROM boards WHERE game_id = ?";

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
                            .moveType(resultSet.getString(5))
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

    public String getWinner(int idGame) {
        String winner;
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(SELECT_WINNER)) {
                pstmt.setInt(1, idGame);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    winner = resultSet.getString(1);
                    return winner;
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return null;
    }

    public int getBoardSize(int idGame) {
        int boardSize;
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOARD_SIZE)) {
                pstmt.setInt(1, idGame);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    boardSize = resultSet.getInt(1);
                    return boardSize;
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
        return 0;
    }
}
