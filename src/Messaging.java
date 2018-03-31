import fenestra.Fenestra;
import fenestra.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class Messaging extends Fenestra {
    public Messaging(Color bgColor, Color captionColor, Color titleColor,
                     String title, int width, int height) {
        super(bgColor, captionColor, titleColor, title, width, height);
        
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        jpnlLoom.setBackground(bgColor);
        jpnlLoom.add(new MainMenu(), BorderLayout.PAGE_START);
        jpnlLoom.add(new SessionPane(Palette.mistyRose, 200, height), BorderLayout.LINE_START);
        
        JPanel jpnlInnerLoom = new JPanel(new BorderLayout());
        jpnlInnerLoom.add(new InputPane(), BorderLayout.PAGE_START);
        jpnlInnerLoom.add(new SessionHistory(), BorderLayout.CENTER);
        
        jpnlLoom.add(jpnlInnerLoom, BorderLayout.CENTER);
        
        add(jpnlLoom, BorderLayout.CENTER);
    }
    
}
