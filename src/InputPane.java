import com.sun.org.apache.xpath.internal.SourceTree;
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
        
        setBackground(Palette.desertSand);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Message"));
    }
    
    private class SendListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!jtfMessage.getText().equals("") && Main.currentSessionId != -1)
                Main.sendMessage(jtfMessage.getText());
            else
                System.out.println("Are you sure you have written something" +
                        " and selected a session to send the message to?");
        }
    }
}
