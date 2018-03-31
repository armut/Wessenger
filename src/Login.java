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
    private JTextField jtfLogin;
    public Login(Color bgColor, Color captionColor, Color titleColor,
                 String title, int width, int height) {
        super(bgColor, captionColor, titleColor, title, width, height);
        
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        jtfLogin = new JTextField("default_user");
        
        jbLogin = new JButton("Log in");
        jbLogin.addActionListener(new LoginListener());
        
        jpnlLoom.add(jtfLogin, BorderLayout.PAGE_START);
        jpnlLoom.add(jbLogin, BorderLayout.PAGE_END);
        jpnlLoom.setBackground(bgColor);
        add(jpnlLoom);
        
        connectToDB();
    }
    
    private void connectToDB() {
        try {
            DBCon.connect();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private boolean login() {
        try {
            Statement statement = DBCon.conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select id, nick_name from user where nick_name=" + "\"" + jtfLogin.getText() + "\"");
            if (rs.next()) {
                Main.currentUserId = rs.getInt("id");
                Main.currentUserName = rs.getString("nick_name");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private class LoginListener implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (login()) {
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
