import javax.swing.table.DefaultTableModel;
import java.util.Vector;

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