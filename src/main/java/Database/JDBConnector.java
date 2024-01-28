package Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import org.hsqldb.jdbc.JDBCUtil;
public class JDBConnector {

    // Metoda do połączenia z bazą danych
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driverClass = properties.getProperty("jdbc.driverClass");

        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection conn) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.out.println("Nie można zamknąć ResultSet");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Nie można zamknąć Statement");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Nie można zamknąć Connection");
            }
        }
    }
}