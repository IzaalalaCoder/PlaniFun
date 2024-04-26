package univ.rouen.planifun.app.editor.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuTask extends JMenuBar {
    
    public MenuTask() {
        JMenu menu = new JMenu("Gestion");
        for (Item it : Item.values()) {
            if (it.getTitle() == null) {
                menu.addSeparator();
            } else {
                JMenuItem item = new JMenuItem(it.getTitle());
                menu.add(item);
            }
        }
        this.add(menu);
    }
}
