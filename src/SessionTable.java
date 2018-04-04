import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * zamma on 4/4/18.
 */
public class SessionTable extends JTable {
    public SessionTable(DefaultTableModel tableModel) {
        tableModel.addColumn("Sessions");
        tableModel.addColumn("Session Id");
        setModel(tableModel);
    
        setShowGrid(false);
        setTableHeader(null);
        getColumnModel().getColumn(1).setMaxWidth(0);
        getColumnModel().getColumn(1).setMinWidth(0);
        getColumnModel().getColumn(1).setPreferredWidth(0);
    }
    
    public void setListener(ListSelectionListener selectionListener) {
        ListSelectionModel listSelectionModel = getSelectionModel();
        listSelectionModel.addListSelectionListener(selectionListener);
        setSelectionModel(listSelectionModel);
    }
}
