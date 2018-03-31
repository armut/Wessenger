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
        
        Main.loadSessions(sessionPane);
    }
    
    public SessionHistoryPane getSessionHistoryPane() { return sessionHistoryPane; }
}
