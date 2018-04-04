import fenestra.Floris;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 4/4/18.
 */
public class DeleteSessionDialog extends Floris {
    private SessionPaneBase sessionPane;
    
    public DeleteSessionDialog(Window parent, Color bgColor, Color captionColor,
                               Color titleColor, String title, int width, int height) {
        super(parent, bgColor, captionColor, titleColor, title, width, height);
    
        JPanel jpnlLoom = new JPanel();
        jpnlLoom.setLayout(new BoxLayout(jpnlLoom, BoxLayout.Y_AXIS));
        jpnlLoom.setBorder(BorderFactory.createLineBorder(bgColor, 10));
        jpnlLoom.setBackground(bgColor);
        
        sessionPane = new DeleteSessionPane(bgColor, width, 100);
        jpnlLoom.add(sessionPane);
        
        add(jpnlLoom);
    }
    
    public SessionPaneBase getSessionPane() { return sessionPane; }
}
