import fenestra.Palette;

import javax.swing.*;

/**
 * zamma on 3/29/18.
 */
public class SessionHistoryPane extends JPanel {
    private JPanel jpnlSession;
    public SessionHistoryPane(String caption) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        jpnlSession = new JPanel();
        jpnlSession.setLayout(new BoxLayout(jpnlSession, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(jpnlSession);
        
        add(scrollPane);
        setBackground(Palette.desertSand);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), caption));
    }
    
    public JPanel getPane() { return jpnlSession; }
}
