package univ.rouen.planifun.app.editor.view.menu;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuTask extends JMenuBar {
    
    public MenuTask() {
        for (Item it : Item.values()) {
            JMenuItem item = new JMenuItem(it.getTitle());
            this.add(item);
        }
    }
}
