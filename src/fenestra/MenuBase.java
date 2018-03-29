package fenestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class MenuBase extends JPanel implements ActionListener {

    private final Color MENU_PANEL_BACKCOLOR = Palette.middleYellowRed;
    private final Color MENU_FORECOLOR = Palette.middleRedPurple;
    private final Color MENU_BACKCOLOR = Palette.aztecGold;
    private final Font MENU_FONT = new Font("Times", Font.BOLD, 10);
    private JMenuBar jmbMenuBar;

    public MenuBase() {
        setBackground(MENU_PANEL_BACKCOLOR);
        setLayout(new BorderLayout());

        jmbMenuBar = new JMenuBar();
        jmbMenuBar.setBackground(MENU_PANEL_BACKCOLOR);
        jmbMenuBar.setBorderPainted(false);
    }

    protected JMenu generateMenu(String title, ArrayList<JMenuItem> menuItems) {
        JMenu menu = new JMenu(title);
        menu.setBorderPainted(false);
        menu.getPopupMenu().setBorder(null);
        menu.setFont(MENU_FONT);
        menu.setForeground(MENU_FORECOLOR);
        for(int i = 0; i < menuItems.size(); i++)
            menu.add(menuItems.get(i));
        return menu;
    }

    protected JMenuItem generateMenuItem(String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setBorderPainted(false); // or create a line border.
        menuItem.setBackground(MENU_BACKCOLOR);
        menuItem.setFont(MENU_FONT);
        menuItem.setForeground(MENU_FORECOLOR);
        return menuItem;
    }

    protected JMenuBar getMenuBar() {
        return jmbMenuBar;
    }
}
