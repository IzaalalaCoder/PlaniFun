package univ.rouen.planifun.app.editor.view.menu;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import univ.rouen.planifun.app.editor.controller.menu.ControlCreateTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlHelpView;
import univ.rouen.planifun.app.editor.controller.menu.ControlLoadSetTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlCreateSetTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlQuit;
import univ.rouen.planifun.app.editor.controller.menu.ControlSaveSetTask;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Represent all items found in the application
 */
public enum Item {

    // VALUES

    /**
     * Item for create todolist
     */
    CREATE("Créer une nouvelle liste de tâche",
        new ImageIcon(ManageImage.PATH_ASSET + "add_set_task.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlCreateSetTask(main);
        }
    },
    /**
     * Item for add new task
     */
    ADD("Ajouter une tâche",
        new ImageIcon(ManageImage.PATH_ASSET + "add_task.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlCreateTask(main);
        }
    },
    /**
     * Separator
     */
    SEP(null, null) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    /**
     * Item for load save todolist
     */
    LOAD("Charger une liste",
        new ImageIcon(ManageImage.PATH_ASSET + "load.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlLoadSetTask(main);
        }
    },
    /**
     * Item for save todolist
     */
    SAVE("Sauvegarder une liste",
        new ImageIcon(ManageImage.PATH_ASSET + "save.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlSaveSetTask(main);
        }
    },
    /**
     * Item for leave application
     */
    QUIT("Quitter l'application",
        new ImageIcon(ManageImage.PATH_ASSET + "quit.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlQuit(main);
        }
    },
    /**
     * Item for display help
     */
    HELP("Aide",
        new ImageIcon(ManageImage.PATH_ASSET + "question.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlHelpView();
        }
    };    
    
    // ATTRIBUTES

    private final String title;
    private final ImageIcon img;

    // CONSTRUCTORS

    private Item(String t, ImageIcon img) {
        this.title = t;
        this.img = img;
    }

    // REQUESTS

    /**
     * getEvent : return event on item
     * @param main : window
     * @return ActionListener
     */
    public abstract ActionListener getEvent(EditorMain main);

    /**
     * getTitle : return title's item
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getIcon : return icon's item
     * @return ImageIcon
     */
    public ImageIcon getIcon() {
        return this.img;
    }
}