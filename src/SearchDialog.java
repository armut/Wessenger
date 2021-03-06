import fenestra.Floris;

import javax.swing.*;
import java.awt.*;

/**
 * zamma on 4/4/18.
 */
public class SearchDialog extends Floris {
    private SessionHistoryPane sessionHistoryPane;
    
    public SearchDialog(Window parent, Color bgColor, Color captionColor, Color titleColor, String title, int width, int height) {
        super(parent, bgColor, captionColor, titleColor, title, width, height);
        setModal(true);
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        InputPane inputPane = new InputPane("Keyword", "Search");
        inputPane.setInputListener(new SearchListener(inputPane.getTextField()));
        jpnlLoom.add(inputPane, BorderLayout.PAGE_START);
        
        sessionHistoryPane = new SessionHistoryPane("Search Results");
        jpnlLoom.add(sessionHistoryPane, BorderLayout.CENTER);
        jpnlLoom.setBorder(BorderFactory.createLineBorder(bgColor, 5));
        
        add(jpnlLoom, BorderLayout.CENTER);
    }
    
    public SessionHistoryPane getSessionHistoryPane() { return sessionHistoryPane; }
    
}
