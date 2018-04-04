import fenestra.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * zamma on 3/29/18.
 */
public class InputPane extends JPanel {
    private JTextField jtfMessage;
    private JButton jbSend;
    public InputPane(String caption, String buttonText) {
        setLayout(new BorderLayout());
        
        JPanel jpnlMessage = new JPanel();
        jpnlMessage.setBorder(BorderFactory.createLineBorder(Palette.vividCerulean));
        jtfMessage = new JTextField("");
        jpnlMessage.add(jtfMessage);
        
        jbSend = new JButton(buttonText);
        
        add(jtfMessage, BorderLayout.CENTER);
        add(jbSend, BorderLayout.LINE_END);
        
        setBackground(Palette.desertSand);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), caption));
    }
    
    public void setInputListener(ActionListener listener) {
        jbSend.addActionListener(listener);
    }
    
    public JTextField getTextField() { return jtfMessage; }
}
