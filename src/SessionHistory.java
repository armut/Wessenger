import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class SessionHistory extends JPanel {
    private JPanel jpnlConversation;
    public SessionHistory() {
        setLayout(new BorderLayout());
        
        jpnlConversation = new JPanel();
        add(jpnlConversation);
    
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session History"));
    }
}
