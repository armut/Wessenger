package fenestra;
import javax.swing.*;
import java.awt.*;

public class Fenestra extends JFrame {
    public Fenestra() {
        this(Palette.mistyRose, Palette.paynesGrey, Palette.mistyRose,
                "Fen", 550, 400);
    }

    public Fenestra(Color bgColor, Color captionColor, Color titleColor, String title, int width, int height) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(width, height);
        setResizable(true);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(bgColor);
        add(new Caption(this, captionColor, titleColor, title), BorderLayout.PAGE_START);
        setVisible(true);
    }
}
