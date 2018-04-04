import fenestra.Floris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * zamma on 4/4/18.
 */
public class DeleteSessionDialog extends Floris {
    private SessionPaneBase sessionPane;
    private JButton jbDelete;
    
    public DeleteSessionDialog(Window parent, Color bgColor, Color captionColor,
                               Color titleColor, String title, int width, int height) {
        super(parent, bgColor, captionColor, titleColor, title, width, height);
        setModal(true);
        
        JPanel jpnlLoom = new JPanel();
        jpnlLoom.setLayout(new BoxLayout(jpnlLoom, BoxLayout.Y_AXIS));
        jpnlLoom.setBorder(BorderFactory.createLineBorder(bgColor, 10));
        jpnlLoom.setBackground(bgColor);
        
        sessionPane = new DeleteSessionPane(bgColor, width, 50);
        jbDelete = new JButton("Delete Selected");
        jbDelete.addActionListener(new DeleteListener());
        jbDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        jpnlLoom.add(sessionPane);
        jpnlLoom.add(Box.createRigidArea(new Dimension(0, 5)));
        jpnlLoom.add(jbDelete);
        
        add(jpnlLoom);
    }
    
    public SessionPaneBase getSessionPane() { return sessionPane; }
    
    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (sessionPane.getSessionTable().getRowCount() > 0) {
                int selectedSessionId = (int) sessionPane.getSessionTableModel().getValueAt(
                        sessionPane.getSessionTable().convertRowIndexToModel(
                                sessionPane.getSessionTable().getSelectedRow()),
                        sessionPane.getSessionTable().getSelectedColumn() + 1);
                Main.deleteSession(selectedSessionId);
                Main.loadSessions(sessionPane);
            }
        }
    }
    
}
