package univ.rouen.planifun.app.editor.view.menu;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import univ.rouen.planifun.app.editor.view.EditorMain;

public class TaskMenu extends JMenuBar {
    
    // CONSTANTS

    public static final String PATH_ASSET = "planifun/src/main/resources/assets/";

    // ATTRIBUTES

    private EditorMain parent;

    // CONSTRUCTORS

    public TaskMenu(EditorMain parent) {
        this.parent = parent;
        this.createMenu();
    }   

    // REQUESTS
    // COMMANDS
    // UTILS

    private void createMenu() {
        for (Menu menu : Menu.values()) {
            JMenu m = new JMenu(menu.name());
            for (Item it : menu.getItems()) {
                if (it.getTitle() == null) {
                    m.addSeparator();
                } else {
                    JMenuItem item = new JMenuItem(it.getTitle());
                    item.addActionListener(it.getEvent(parent));
                    item.setIcon(this.getIcon(it.getIcon()));
                    m.add(item);
                }
            }

            this.add(m);
        }
    }

    private ImageIcon getIcon(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return null;
        }
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

}
