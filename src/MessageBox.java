import fenestra.Palette;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * zamma on 3/31/18.
 */
public class MessageBox extends JPanel {
    public MessageBox(String nickName, String date, String message) {
        JLabel jlblNick = new JLabel(nickName);
        JLabel jlblDate = new JLabel(date);
        JLabel jlblMessage = new JLabel(message);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(jlblNick, BorderLayout.PAGE_START);
        add(jlblMessage, BorderLayout.CENTER);
        add(jlblDate, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createLineBorder(Palette.vividCerulean, 2));
    }
}
