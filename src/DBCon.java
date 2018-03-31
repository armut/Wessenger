import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * zamma on 3/30/18.
 */
public class DBCon {
    public static Connection conn = null;
    
    public static void connect() throws SQLException{
        String connStr = "jdbc:sqlite:squirrel.sqlite";
        conn = DriverManager.getConnection(connStr);
    }
    
    public static void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
