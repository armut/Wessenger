import fenestra.Fenestra;

import javax.swing.*;
import java.awt.*;

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
        jtfLogin = new JTextField();
        jbLogin = new JButton("Log in");
        jpnlLoom.add(jtfLogin, BorderLayout.PAGE_START);
        jpnlLoom.add(jbLogin, BorderLayout.PAGE_END);
        jpnlLoom.setBackground(bgColor);
        add(jpnlLoom);
        
    }
}
