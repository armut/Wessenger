import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendListener implements ActionListener {
    JTextField jtfMessage;
    public SendListener(JTextField jtf) {
        jtfMessage = jtf;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!jtfMessage.getText().equals("") && Main.currentSessionId != -1)
            Main.sendMessage(jtfMessage.getText());
        else
            System.out.println("Are you sure you have written something" +
                    " and selected a session to send the message to?");
    }
}