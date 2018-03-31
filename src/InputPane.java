import fenestra.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * zamma on 3/29/18.
 */
public class InputPane extends JPanel {
    private JTextField jtfMessage;
    private JButton jbSend;
    public InputPane() {
        setLayout(new BorderLayout());
        
        JPanel jpnlMessage = new JPanel();
        jpnlMessage.setBorder(BorderFactory.createLineBorder(Palette.vividCerulean));
        jtfMessage = new JTextField("");
        jpnlMessage.add(jtfMessage);
        
        jbSend = new JButton("Send");
        jbSend.addActionListener(new SendListener());
        
        add(jtfMessage, BorderLayout.CENTER);
        add(jbSend, BorderLayout.LINE_END);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Message"));
    }
    
    private class SendListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Main.sendMessage(jtfMessage.getText());
        }
    }
}
