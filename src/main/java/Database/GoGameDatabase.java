package Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class GoGameDatabase {

    // Kwerendy
    private static final String INSERT_GAME = "INSERT INTO games (player1, player2, moves) VALUES (?, ?, ?)";
    private static final String SELECT_GAME = "SELECT * FROM games WHERE id = ?";

    // Metoda do połączenia z bazą danych
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = GoGameDatabase.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driverClass = properties.getProperty("jdbc.driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            // TODO: połączenie z grą

            // Zapis partii
            saveGame(conn, "Player1", "Player2", "A1 B2 C3 ...");

            int gameId = 1;
            String[] gameData = loadGame(conn, gameId);

            if (gameData != null) {
                System.out.println("Player 1: " + gameData[0]);
                System.out.println("Player 2: " + gameData[1]);
                System.out.println("Moves: " + gameData[2]);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda do zapisywania partii
    private static void saveGame(Connection conn, String player1, String player2, String moves) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_GAME)) {
            pstmt.setString(1, player1);
            pstmt.setString(2, player2);
            pstmt.setString(3, moves);
            pstmt.executeUpdate();
        }
    }

    // Metoda do odczytu partii
    private static String[] loadGame(Connection conn, int gameId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_GAME)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String player1 = rs.getString("player1");
                    String player2 = rs.getString("player2");
                    String moves = rs.getString("moves");
                    return new String[]{player1, player2, moves};
                }
            }
        }
        return null;
    }
}