import fenestra.MenuBase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainMenu extends MenuBase {

    public MainMenu() {
        super();

        // JMenu instances:
        JMenu sessionsMenu = generateMenu("SESSIONS", generateSessionsMenu());

        // Adding JMenus to JMenuBar:
        getMenuBar().add(sessionsMenu);

        // Adding JMenuBar to the panel:
        add(getMenuBar(), BorderLayout.LINE_START);
    }

    private ArrayList<JMenuItem> generateSessionsMenu() {
        ArrayList<JMenuItem> sessionsMenuItems = new ArrayList<>();
        JMenuItem jmOpenSession = generateMenuItem("NEW SESSION");
        JMenuItem jmDeleteSession = generateMenuItem("DELETE SESSION");
        sessionsMenuItems.add(jmOpenSession);
        sessionsMenuItems.add(jmDeleteSession);
        for(JMenuItem item : sessionsMenuItems)
            item.addActionListener(this);
        return sessionsMenuItems;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEW SESSION")) {
            Main.os.setVisible(true);
        } else if (e.getActionCommand().equals("DELETE SESSION")) {
            Main.loadSessions(Main.ds.getSessionPane());
            Main.ds.setVisible(true);
        }
    }
}
