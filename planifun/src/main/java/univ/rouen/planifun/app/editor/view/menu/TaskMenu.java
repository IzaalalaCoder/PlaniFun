package univ.rouen.planifun.app.editor.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Manage bar menu
 */
public class TaskMenu extends JMenuBar {

    // ATTRIBUTES

    private final EditorMain parent;

    // CONSTRUCTORS

    public TaskMenu(EditorMain parent) {
        this.parent = parent;
        this.createMenu();
    }   

    // UTILS

    /**
     * createMenu : create this menu
     */
    private void createMenu() {
        for (Menu menu : Menu.values()) {
            JMenu m = new JMenu(menu.name());
            for (Item it : menu.getItems()) {
                if (it.getTitle() == null) {
                    m.addSeparator();
                } else {
                    JMenuItem item = new JMenuItem(it.getTitle());
                    item.addActionListener(it.getEvent(parent));
                    item.setIcon(ManageImage.getIcon(it.getIcon()));
                    m.add(item);
                }
            }

            this.add(m);
        }
    }

}
