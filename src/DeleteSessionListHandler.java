import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * zamma on 4/4/18.
 */
public class DeleteSessionListHandler implements ListSelectionListener {
    JTable sessionTable;
    DefaultTableModel tableModel;
    
    public DeleteSessionListHandler(JTable table, DefaultTableModel model) {
        this.sessionTable = table;
        this.tableModel = model;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting() && sessionTable.getModel().getRowCount() > 0) {
            
        }
    }
}
