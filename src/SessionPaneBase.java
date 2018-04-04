import fenestra.Palette;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * zamma on 3/29/18.
 */
public class SessionPaneBase extends JPanel {
    protected SessionTable sessionTable;
    protected SessionTableModel tableModel;
    
    public SessionPaneBase(Color bgColor, int width, int height) {
        setLayout(new BorderLayout());
        tableModel = new SessionTableModel();
        sessionTable = new SessionTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(sessionTable);
        
        add(scrollPane, BorderLayout.CENTER);
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session List"));
        setBackground(bgColor);
        setPreferredSize(new Dimension(width, height));
    }
    
    public SessionTableModel getSessionTableModel() {
        return tableModel;
    }
}
