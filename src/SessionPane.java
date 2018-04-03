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
public class SessionPane extends JPanel {
    private JTable sessionTable;
    private JButton newSession;
    private SessionTableModel tableModel;
    private ListSelectionModel listSelectionModel;
    private OpenSessionDialog os;
    
    public SessionPane(Color bgColor, int width, int height) {
        setLayout(new BorderLayout());
        os = new OpenSessionDialog(
                Main.m, Palette.deepTaupe, Palette.paynesGrey, Palette.middleRedPurple, "New Session", 200, 100);
        tableModel = new SessionTableModel();
        tableModel.addColumn("Sessions");
        tableModel.addColumn("Session Id");
        sessionTable = new JTable(tableModel);
        //sessionTable.setFillsViewportHeight(true);
        sessionTable.setShowGrid(false);
        sessionTable.setTableHeader(null);
        sessionTable.getColumnModel().getColumn(1).setMaxWidth(0);
        sessionTable.getColumnModel().getColumn(1).setMinWidth(0);
        sessionTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        listSelectionModel = sessionTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SessionListHandler());
        sessionTable.setSelectionModel(listSelectionModel);
        JScrollPane scrollPane = new JScrollPane(sessionTable);
        
        
        JPanel jpnlButton = new JPanel();
        jpnlButton.setBorder(BorderFactory.createLineBorder(Palette.vividCerulean));
        newSession = new JButton("+");
        newSession.addActionListener(new ButtonListener());
        jpnlButton.add(newSession);
        
        add(jpnlButton, BorderLayout.PAGE_END);
        add(scrollPane, BorderLayout.PAGE_START);
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Palette.desertSand, 3), "Session List"));
        setBackground(bgColor);
        setPreferredSize(new Dimension(width, height));
    }
    
    public SessionTableModel getSessionTableModel() {
        return tableModel;
    }
    
    private class SessionListHandler implements ListSelectionListener {
    
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (!listSelectionEvent.getValueIsAdjusting()) {
                Main.currentSessionId = (int)tableModel.getValueAt(
                        sessionTable.convertRowIndexToModel(sessionTable.getSelectedRow()),
                        sessionTable.getSelectedColumn() + 1
                );
                Main.loadSessionHistory();
            }
            
        }
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            os.setVisible(true);
        }
    }
    
    public class SessionTableModel extends DefaultTableModel {
        public void addSessionRow(Session s) {
            Vector<Object> entity = new Vector<>(2);
            entity.addElement(s.getSessionName());
            entity.addElement(s.getSessionId());
            this.addRow(entity);
        }
        
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    }
}
