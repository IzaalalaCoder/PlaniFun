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
        JMenu menu = new JMenu("Gérer");
        for (Item it : Item.values()) {
            if (it.getTitle() == null) {
                menu.addSeparator();
            } else {
                JMenuItem item = new JMenuItem(it.getTitle());
                item.addActionListener(it.getEvent(parent));
                item.setIcon(this.getIcon(it.getIcon()));
                menu.add(item);
            }
        }

        this.add(menu);
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
