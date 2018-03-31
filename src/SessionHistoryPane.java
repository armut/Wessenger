import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class SessionHistoryPane extends JPanel {
    private JPanel jpnlSession;
    public SessionHistoryPane() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        jpnlSession = new JPanel();
        jpnlSession.setLayout(new BoxLayout(jpnlSession, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(jpnlSession);
        
        add(scrollPane);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session History"));
    }
    
    public JPanel getPane() { return jpnlSession; }
}
