import com.sun.istack.internal.Nullable;
import fenestra.Palette;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * zamma on 3/29/18.
 */
public class Main {
    public static MessagingFrame m;
    public static OpenSessionDialog os;
    public static DeleteSessionDialog ds;
    public static Login l;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static int currentUserId = -1;
    public static int currentSessionId = -1;
    public static String currentUserName = "";
    public static int currentListeningPort = -1;
    public static ServerSocket serverSocket;
    public static final int REMOTE_PORT = 9898;
    public static final String REMOTE_HOST = "127.0.0.1"; //TODO:dynamic.
    public static String LOCAL_HOST = "";
    public static Thread listenerThread;
    
    public static void main(String[] args) {
        // Instantiate the windows:
        os = new OpenSessionDialog(
                Main.m, Palette.deepTaupe, Palette.paynesGrey, Palette.middleRedPurple, "New Session", 200, 125);
        
        ds = new DeleteSessionDialog(
                Main.m, Palette.deepTaupe, Palette.paynesGrey, Palette.middleRedPurple, "Delete Session", 250, 200);
        
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
        } else {
            // Start waiting for requests.
            listenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(serverSocket != null) {
                        try {
                            acceptMessages();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    listenerThread.start();
                }
            });
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                l = new Login(Palette.deepTaupe, Palette.paynesGrey,
                        Palette.middleRedPurple, "Log In", WIDTH/4, HEIGHT/4);
            }
        });
    }
    
    private static void acceptMessages() throws IOException {
        System.out.println("Listening for messages on port " + String.valueOf(currentListeningPort));
        Socket socket = serverSocket.accept();
        BufferedReader incoming = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        String receipt = incoming.readLine();
        String sender = getNickNameById(receipt.split(":")[0]);
        String messageBody = receipt.split(":")[1];
        m.getSessionHistoryPane().getPane().add(
                new MessageBox(sender, String.valueOf(Calendar.getInstance().getTime().getTime()), messageBody, false));
        m.getSessionHistoryPane().getPane().revalidate();
        m.getSessionHistoryPane().getPane().repaint();
    }
    
    private static String getNickNameById(String id) {
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            ResultSet rs = statement.executeQuery("select nick_name from user where id=" + id);
            if (rs.next())
                return rs.getString("nick_name");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBCon.disconnect();
        }
        return "";
    }
    
    public static boolean login(String nickName, String localHost) {
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select id, nick_name from user where nick_name=" + "\"" + nickName + "\"");
            if (rs.next()) {
                Main.currentUserId = rs.getInt("id");
                Main.currentUserName = rs.getString("nick_name");
                Main.notifyRemote(rs.getInt("id"), localHost);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Is server running?");
            return false;
        } finally {
            DBCon.disconnect();
        }
    }
    
    public static void loadSessionHistory() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DBCon.connect();
                    Statement statement = DBCon.conn.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "select `date`, message, nick_name, user_id FROM " +
                                    "session_history join user on session_history.user_id=user.id WHERE " +
                                    "session_id=" + String.valueOf(currentSessionId) + " " +
                                    "order by `date`");
                    populateSessionHistory(rs);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } finally {
                    DBCon.disconnect();
                }
            }
        });
    }
    
    public static void loadSessions(SessionPaneBase sessionPane) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                sessionPane.getSessionTableModel().setRowCount(0);
                try {
                    DBCon.connect();
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
                } finally {
                    DBCon.disconnect();
                }
            }
        });
    }
        
    public static void populateSessionHistory(ResultSet rs) throws SQLException {
        JPanel pane = m.getSessionHistoryPane().getPane();
        clearPanel(pane);
        while (rs.next()) {
            if (rs.getInt("user_id") != currentUserId)
                pane.add(new MessageBox(rs.getString("nick_name"), rs.getString("date"), rs.getString("message"), false));
            else
                pane.add(new MessageBox(rs.getString("nick_name"), rs.getString("date"), rs.getString("message"), true));
            pane.revalidate();
            pane.repaint();
        }
    }
    
    public static void clearPanel(JPanel pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }
    
    public static void notifyRemote(int userId, String localHost) throws IOException {
        // Notify remote side about my host and port.
        Socket socket = new Socket(REMOTE_HOST, REMOTE_PORT);
        DataOutputStream outgoing = new DataOutputStream(socket.getOutputStream());
        outgoing.writeBytes("info:" + String.valueOf(userId) + "," + localHost + "," + currentListeningPort);
        socket.close();
    }
    
    @Nullable
    public static String getSessionRecipients() {
        String result = "";
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            ResultSet rs = statement.executeQuery("select user_id from session_user_lookup " +
                    "where session_id=" + currentSessionId + " and user_id<>" + currentUserId);
            while(rs.next()) {
                if (!result.equals(""))
                    result += ",";
                result += rs.getString("user_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBCon.disconnect();
        }
        return result;
    }
    
    public static void sendMessage(String message) {
        boolean successful = false;
        try {
            // Try to send the message over TCP.
            Socket socket = new Socket(REMOTE_HOST, REMOTE_PORT);
            DataOutputStream outgoing = new DataOutputStream(socket.getOutputStream());
            // Get the recipient list.
            String recipients = getSessionRecipients();
            if (recipients != null) {
                outgoing.writeBytes(
                        "recipients:" + recipients +
                        ":sender:" + String.valueOf(currentUserId) +
                        ":message:" + message);
                successful = true;
            }
            socket.close();
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
                        DBCon.connect();
                        Statement statement = DBCon.conn.createStatement();
                        statement.executeUpdate(
                                "insert into session_history (`date`, message, user_id, session_id) " +
                                        "values(DATETIME('now')" + ",\"" + message + "\"," +
                                        String.valueOf(currentUserId) + "," + String.valueOf(currentSessionId) + ")");
                        m.getSessionHistoryPane().getPane().add(
                                new MessageBox(currentUserName, String.valueOf(currentDate), message, true));
                        m.getSessionHistoryPane().getPane().revalidate();
                        m.getSessionHistoryPane().getPane().repaint();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        DBCon.disconnect();
                    }
                }
            });
        }
    }
    
    public static void deleteSession(int sessionId) {
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            statement.executeUpdate("delete from Session where id=" + String.valueOf(sessionId));
            // Other related entries will be deleted cascaded.
            // Refresh session pane on the messaging window:
            loadSessions(m.getSessionPane());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBCon.disconnect();
        }
    }
    
    public static void openSession(String sessionName, UserGroup group) {
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            statement.executeUpdate("insert into Session (`name`)" +
                    " values(\"" + sessionName +"\")");
            ResultSet rs = statement.executeQuery("select id from Session order by id desc limit 1");
            int id = -1;
            if (rs.next())
                id = rs.getInt("id");
            if (id != -1) {
                ArrayList<UserGroup.User> users = group.getUsers();
                for( UserGroup.User user : users ) {
                    statement.executeUpdate("insert into session_user_lookup (session_id, user_id) " +
                            "values(" + String.valueOf(id) + "," + user.getId() + ")");
                }
                
                // And one for the current user:
                statement.executeUpdate("insert into session_user_lookup (session_id, user_id)" +
                        " values(" + String.valueOf(id) + "," + currentUserId + ")");
            }
            loadSessions(m.getSessionPane());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBCon.disconnect();
        }
    }
    
    public static ArrayList<UserGroup> fetchUserGroups() {
        // Make an array of UserGroups.
        ArrayList<UserGroup> groups = new ArrayList<>();
        try {
            DBCon.connect();
            Statement statement = DBCon.conn.createStatement();
            ResultSet rs = statement.executeQuery("select id, name from user_group");
            while (rs.next()) {
                int currentGroupId = rs.getInt("id");
                UserGroup group = new UserGroup(rs.getString("name"), currentGroupId);
                Statement statementForGroupUsers = DBCon.conn.createStatement();
                ResultSet rs2 = statementForGroupUsers.executeQuery("select nick_name, user_id from user_group_lookup " +
                        "join user on user_group_lookup.user_id = user.id " +
                        "where user_group_id=" + String.valueOf(currentGroupId) + " and user_id<>" + currentUserId);
                while(rs2.next()) {
                    group.addUser(new UserGroup().new User(rs2.getString("nick_name"), rs2.getInt("user_id")));
                }
                groups.add(group);
            }
            
            // Add single users as a group of one element.
            Statement statementForUsers = DBCon.conn.createStatement();
            ResultSet rs3 = statementForUsers.executeQuery("select id, nick_name from user where id<>" + currentUserId);
            while (rs3.next()) {
                UserGroup group = new UserGroup();
                group.addUser(new UserGroup().new User(rs3.getString("nick_name"), rs3.getInt("id")));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBCon.disconnect();
        }
        return groups;
    }
}
