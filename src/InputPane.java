import fenestra.Palette;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class InputPane extends JPanel {
    private JTextField jtfMessage;
    private JButton jbSend;
    public InputPane() {
        setLayout(new BorderLayout());
        
        jtfMessage = new JTextField("Your message here");
        jbSend = new JButton("Send");
        
        add(jtfMessage, BorderLayout.LINE_START);
        add(jbSend, BorderLayout.LINE_END);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Message"));
    }
}
