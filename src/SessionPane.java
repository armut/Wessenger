import fenestra.Palette;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * zamma on 3/29/18.
 */
public class SessionPane extends JPanel {
    private JTable sessionTable;
    private JButton newSession;
    
    public SessionPane(Color bgColor, int width, int height) {
        setLayout(new BorderLayout());
        
        sessionTable = new JTable();
        sessionTable.setFillsViewportHeight(true);
        sessionTable.setShowGrid(true);
        sessionTable.setTableHeader(null);
        JScrollPane scrollPane = new JScrollPane(sessionTable);
        
        JPanel jpnlButton = new JPanel();
        jpnlButton.setBorder(BorderFactory.createLineBorder(Palette.vividCerulean));
        newSession = new JButton("+");
        jpnlButton.add(newSession);
        
        add(jpnlButton, BorderLayout.PAGE_END);
        add(scrollPane, BorderLayout.PAGE_START);
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session List"));
        setBackground(bgColor);
        setPreferredSize(new Dimension(width, height));
    }
}
