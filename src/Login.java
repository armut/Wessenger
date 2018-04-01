import fenestra.Fenestra;
import fenestra.Palette;

import javax.swing.*;
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
        
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        jtfLogin = new JTextField("default_user");
        jtfHostName = new JTextField("127.0.0.1");
        
        jbLogin = new JButton("Log in");
        jbLogin.addActionListener(new LoginListener());
        
        jpnlLoom.add(jtfLogin, BorderLayout.PAGE_START);
        jpnlLoom.add(jtfHostName, BorderLayout.CENTER);
        jpnlLoom.add(jbLogin, BorderLayout.PAGE_END);
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
                                Palette.middleRedPurple, "MessagingFrame Application - " + Main.currentUserName, Main.WIDTH, Main.HEIGHT);
                        Login.this.dispose();
                    }
                });
            }
                
        }
    }
}
