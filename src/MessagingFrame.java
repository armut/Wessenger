import fenestra.Fenestra;
import fenestra.Palette;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * zamma on 3/29/18.
 */
public class MessagingFrame extends Fenestra {
    private SessionPane sessionPane;
    private SessionHistoryPane sessionHistoryPane;
    
    public MessagingFrame(Color bgColor, Color captionColor, Color titleColor,
                          String title, int width, int height) {
        super(bgColor, captionColor, titleColor, title, width, height);
        
        sessionPane = new SessionPane(Palette.mistyRose, 200, height);
        sessionHistoryPane = new SessionHistoryPane();
        
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        jpnlLoom.setBackground(bgColor);
        jpnlLoom.add(new MainMenu(), BorderLayout.PAGE_START);
        jpnlLoom.add(sessionPane, BorderLayout.LINE_START);
        
        JPanel jpnlInnerLoom = new JPanel(new BorderLayout());
        jpnlInnerLoom.add(new InputPane(), BorderLayout.PAGE_START);
        jpnlInnerLoom.add(sessionHistoryPane, BorderLayout.CENTER);
        
        jpnlLoom.add(jpnlInnerLoom, BorderLayout.CENTER);
        
        add(jpnlLoom, BorderLayout.CENTER);
        
        loadSessions();
    }
    
    private void loadSessions() {
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
    
}
