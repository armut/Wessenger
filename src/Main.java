import fenestra.Palette;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

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
    public static int currentListeningPort = -1;
    public static ServerSocket serverSocket;
    public static final int REMOTE_PORT = 9898;
    public static final String REMOTE_HOST = "127.0.0.1";
    
    public static void main(String[] args) {
        // Acquire a port and open a socket.
        for (int i = 9090; i < Math.pow(2, 16); i++) {
            try {
                serverSocket = new ServerSocket(i);
                currentListeningPort = i;
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        if (currentListeningPort == -1) {
            System.out.println("Could not acquire a port address.");
            System.exit(0);
        }
        
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
                                    "order by `date`");
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
        JPanel pane = m.getSessionHistoryPane().getPane();
        pane.removeAll();
        while (rs.next()) {
            pane.add(
                    new MessageBox(rs.getString("nick_name"), rs.getString("date"), rs.getString("message")));
            pane.revalidate();
            pane.repaint();
        }
    }
    
    public static void notifyRemote(int userId) throws IOException {
        // Notify remote side about my host and port.
        Socket socket = new Socket(REMOTE_HOST, REMOTE_PORT);
        DataOutputStream outgoing = new DataOutputStream(socket.getOutputStream());
        outgoing.writeBytes("info:" + String.valueOf(userId) + "," + InetAddress.getLocalHost() + "," + currentListeningPort);
        socket.close();
    }
    
    public static void sendMessage(String message) {
        boolean successful = false;
        try {
            // Try to send the message over TCP.
            Socket socket = new Socket(REMOTE_HOST, REMOTE_PORT);
            DataOutputStream outgoing = new DataOutputStream(socket.getOutputStream());
            outgoing.writeBytes(message);
            socket.close();
            successful = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Save the message that is sent. Append it to the conversation panel.
        if (successful) {
            Date currentDate = new Date((Calendar.getInstance().getTime()).getTime());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Statement statement = DBCon.conn.createStatement();
                        statement.executeUpdate(
                                "insert into session_history (`date`, message, user_id, session_id) " +
                                        "values(DATETIME('now')" + ",\"" + message + "\"," +
                                        String.valueOf(currentUserId) + "," + String.valueOf(currentSessionId) + ")");
                        m.getSessionHistoryPane().getPane().add(new MessageBox(currentUserName, String.valueOf(currentDate), message));
                        m.getSessionHistoryPane().getPane().revalidate();
                        m.getSessionHistoryPane().getPane().repaint();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
    }
}
