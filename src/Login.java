import fenestra.Fenestra;
import fenestra.Palette;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * zamma on 3/29/18.
 */
public class Login extends Fenestra {
    private JButton jbLogin;
    private JTextField jtfLogin, jtfHostName;
    public Login(Color bgColor, Color captionColor, Color titleColor,
                 String title, int width, int height) {
        super(bgColor, captionColor, titleColor, title, width, height);
        
        JPanel jpnlLoom = new JPanel();
        jpnlLoom.setLayout(new BoxLayout(jpnlLoom, BoxLayout.Y_AXIS));
        jpnlLoom.setBorder(BorderFactory.createLineBorder(bgColor, 10));
        
        jtfLogin = new JTextField("default_user");
        jtfLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        jtfHostName = new JTextField("127.0.0.1");
        jtfHostName.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        jbLogin = new JButton("Log in");
        jbLogin.addActionListener(new LoginListener());
        jbLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        jpnlLoom.add(jtfLogin);
        jpnlLoom.add(Box.createRigidArea(new Dimension(0, 5)));
        jpnlLoom.add(jtfHostName);
        jpnlLoom.add(Box.createRigidArea(new Dimension(0, 5)));
        jpnlLoom.add(jbLogin);
        jpnlLoom.setBackground(bgColor);
        add(jpnlLoom);
    }
    
    private class LoginListener implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (Main.login(jtfLogin.getText(), jtfHostName.getText())) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.m = new MessagingFrame(Palette.deepTaupe, Palette.paynesGrey,
                                Palette.middleRedPurple, "Wessenger - " + Main.currentUserName, Main.WIDTH, Main.HEIGHT);
                        Login.this.dispose();
                    }
                });
            }
                
        }
    }
}
