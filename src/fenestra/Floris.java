package fenestra;
import javax.swing.*;
import java.awt.*;

/**
 * zamma on 18.03.2017.
 */
public class Floris extends JDialog {
    public Floris(Window parent, Color bgColor, Color captionColor, Color titleColor, String title, int width, int height) {
        super(parent);
        setTitle(title);
        setSize(width, height);
        setResizable(true);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        getContentPane().setBackground(bgColor);
        add(new Caption(this, captionColor, titleColor, title), BorderLayout.PAGE_START);
    }
}
