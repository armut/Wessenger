import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/31/18.
 */
public class MessageBox extends JPanel {
    public MessageBox(String nickName, String date, String message, boolean isItself) {
        JPanel jpnlNickDate = new JPanel(new BorderLayout());
        if (!isItself)
            jpnlNickDate.setBackground(Palette.middleYellowRed);
        else
            jpnlNickDate.setBackground(Palette.aztecGold);
        
        JLabel jlblNick = new JLabel(nickName);
        jpnlNickDate.add(jlblNick, BorderLayout.LINE_START);
        
        JLabel jlblDate = new JLabel(date);
        jpnlNickDate.add(jlblDate, BorderLayout.LINE_END);
        
        JPanel jpnlMessage = new JPanel();
        JLabel jlblMessage = new JLabel(message);
        jpnlMessage.add(jlblMessage);
        
        setLayout(new BorderLayout());
        add(jpnlNickDate, BorderLayout.PAGE_START);
        if (isItself)
            add(jpnlMessage, BorderLayout.LINE_START);
        else
            add(jpnlMessage, BorderLayout.LINE_END);
        // setBorder(BorderFactory.createLineBorder(Palette.vividCerulean, 2));
        setMaximumSize(new Dimension(Main.m.getSessionHistoryPane().getWidth(), getPreferredSize().height));
    }
}
