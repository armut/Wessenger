import fenestra.Palette;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * zamma on 3/29/18.
 */
public class Main {
    public static MessagingFrame m;
    public static Login l;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static int currentUserId = -1;
    public static int currentSessionId = -1;
    public static String currentUserName = "";
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                l = new Login(Palette.deepTaupe, Palette.paynesGrey,
                        Palette.middleRedPurple, "Log In", WIDTH/4, HEIGHT/4);
            }
        });
    }
    
    public static void loadSessionHistory() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Statement statement = DBCon.conn.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "select `date`, message, nick_name FROM " +
                                    "session_history join user on session_history.user_id=user.id WHERE " +
                                    "session_id=" + String.valueOf(currentSessionId) + " " +
                                    "order by `date` desc");
                    populateSessionHistory(rs);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    
    public static void loadSessions(SessionPane sessionPane) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Statement statement = DBCon.conn.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "select session_id, name from " +
                                    "session_user_lookup join Session on " +
                                    "session_user_lookup.session_id = Session.id " +
                                    "where user_id=" + String.valueOf(Main.currentUserId));
                    while (rs.next()) {
                        sessionPane.getSessionTableModel().addSessionRow(new Session(rs.getString("name"), rs.getInt("session_id")));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    
    public static void populateSessionHistory(ResultSet rs) throws SQLException {
        SessionHistoryPane pane = m.getSessionHistoryPane();
        pane.removeAll();
        while (rs.next()) {
            pane.add(
                    new MessageBox(rs.getString("nick_name"), rs.getString("date"), rs.getString("message")));
            pane.revalidate();
            pane.repaint();
        }
    }
}
