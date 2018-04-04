import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SessionListHandler implements ListSelectionListener {
    JTable sessionTable;
    DefaultTableModel tableModel;
    
    public SessionListHandler(JTable table, DefaultTableModel model) {
        this.sessionTable = table;
        this.tableModel = model;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting() && sessionTable.getModel().getRowCount() > 0) {
            Main.currentSessionId = (int)tableModel.getValueAt(
                    sessionTable.convertRowIndexToModel(sessionTable.getSelectedRow()),
                    sessionTable.getSelectedColumn() + 1
            );
            Main.loadSessionHistory();
        }
    }
}