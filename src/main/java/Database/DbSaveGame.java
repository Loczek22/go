package Database;

import GUI.BoardSizeSelector;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSaveGame {
    private static final String MAX_ID = "SELECT MAX(game_id) FROM boards";
    private static final String INSERT_GAME = "INSERT INTO games (game_id, move_id, x, y, color, move_type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String MAX_MOVE = "SELECT MAX(move_id) FROM games WHERE game_id = ?";
    private static final String UPDATE_WINNER = "UPDATE boards SET winner = ? WHERE game_id = ?";
    private static final String UPDATE_BOARD_SIZE = "UPDATE boards SET boardSize = ? WHERE game_id = ?";
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
                pstmt.setString(6, move.getMoveType());
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

    public void setWinner(Color color, int idGame) {
        try {
            conn = JDBConnector.getConnection();
            String winner = (color == Color.BLACK) ? "BLACK" : "WHITE";
            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_WINNER)) {
                pstmt.setString(1, winner);
                pstmt.setInt(2, idGame);

                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
    }

    public void setBoardSize(int boardSize, int idGame) {
        try {
            conn = JDBConnector.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOARD_SIZE)) {
                boardSize = BoardSizeSelector.getSelectedBoardSize();
                pstmt.setInt(1, boardSize);
                pstmt.setInt(2, idGame);

                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBConnector.release(null, null, conn);
        }
    }
}
