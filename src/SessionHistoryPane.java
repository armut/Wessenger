import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class SessionHistoryPane extends JPanel {
    private JPanel jpnlConversation;
    public SessionHistoryPane() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session History"));
    }
}
