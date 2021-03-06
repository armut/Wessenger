import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * zamma on 3/30/18.
 */
public class DBCon {
    public static Connection conn = null;
    
    public static void connect() throws SQLException{
        if (conn == null) {
            String connStr = "jdbc:sqlite:squirrel.sqlite";
            conn = DriverManager.getConnection(connStr);
            Statement fkOpen = conn.createStatement();
            fkOpen.executeUpdate("pragma foreign_keys=on");
        }
    }
    
    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            conn = null;
        }
    }
}
