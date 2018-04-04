import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/31/18.
 */
public class MessageBox extends JPanel {
    public MessageBox(String nickName, String date, String message, boolean isItself) {
        JPanel jpnlNickDate = new JPanel(new BorderLayout());
        JLabel jlblNick = new JLabel(nickName);
        jpnlNickDate.add(jlblNick, BorderLayout.LINE_START);
        
        JLabel jlblDate = new JLabel(date);
        jpnlNickDate.add(jlblDate, BorderLayout.LINE_END);
        
        JPanel jpnlMessage = new JPanel();
        JLabel jlblMessage = new JLabel(message);
        jpnlMessage.add(jlblMessage);
    
        if (!isItself) {
            jpnlNickDate.setBackground(Palette.middleYellowRed);
        } else {
            jpnlNickDate.setBackground(Palette.aztecGold);
            setBackground(Palette.cameoPink);
            jpnlMessage.setBackground(Palette.cameoPink);
        }
        
        setLayout(new BorderLayout());
        add(jpnlNickDate, BorderLayout.PAGE_START);
        if (isItself)
            add(jpnlMessage, BorderLayout.LINE_START);
        else
            add(jpnlMessage, BorderLayout.LINE_END);
        setBorder(BorderFactory.createLineBorder(Palette.mistyRose, 5));
        setMaximumSize(new Dimension(Main.m.getSessionHistoryPane().getWidth(), getPreferredSize().height));
    }
}
