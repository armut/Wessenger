import fenestra.Floris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * zamma on 4/1/18.
 */
public class OpenSessionDialog extends Floris {
    private JTextField jtfSessionName;
    private JComboBox<UserGroup> jcUsers;
    private JButton jbOpen;
    public OpenSessionDialog(Window parent, Color bgColor, Color captionColor,
                             Color titleColor, String title, int width, int height) {
        super(parent, bgColor, captionColor, titleColor, title, width, height);
        JPanel jpnlLoom = new JPanel();
        jpnlLoom.setLayout(new BoxLayout(jpnlLoom, BoxLayout.Y_AXIS));
        jpnlLoom.setBorder(BorderFactory.createLineBorder(bgColor, 10));
        jpnlLoom.setBackground(bgColor);
        
        jtfSessionName = new JTextField();
        jtfSessionName.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        jcUsers = new JComboBox<UserGroup>();
        jcUsers.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        ArrayList<UserGroup> groups = Main.fetchUserGroups();
        if (groups != null) {
            for (UserGroup ug : groups) {
                jcUsers.addItem(ug);
            }
        }
        
        jbOpen = new JButton("Open");
        jbOpen.addActionListener(new OpenListener());
        jbOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        jpnlLoom.add(jtfSessionName);
        jpnlLoom.add(Box.createRigidArea(new Dimension(0, 5)));
        jpnlLoom.add(jcUsers);
        jpnlLoom.add(Box.createRigidArea(new Dimension(0, 5)));
        jpnlLoom.add(jbOpen);
        add(jpnlLoom);
    }
    
    private class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Main.openSession(jtfSessionName.getText(), (UserGroup)jcUsers.getSelectedItem());
            OpenSessionDialog.this.dispose();
            Main.loadSessions(Main.m.getSessionPane());
        }
    }
}
