package univ.rouen.planifun.app.editor.view.menu;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import univ.rouen.planifun.app.editor.controller.menu.ControlCreateTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlLoadSetTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlCreateSetTask;
import univ.rouen.planifun.app.editor.controller.menu.ControlQuit;
import univ.rouen.planifun.app.editor.controller.menu.ControlSaveSetTask;
import univ.rouen.planifun.app.editor.view.EditorMain;

public enum Item {

    // CONSTANTS

    //private final String PATH_ASSET = "planifun/src/main/resources/assets/";

    // VALUES
    
    CREATE("Créer une nouvelle liste de tâche",
        new ImageIcon(TaskMenu.PATH_ASSET + "add_set_task.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlCreateSetTask(main);
        }
    },
    ADD("Ajouter une tâche",
        new ImageIcon(TaskMenu.PATH_ASSET + "add_task.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlCreateTask(main);
        }
    },
    SEP_ONE(null, null) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    LOAD("Charger une liste",
        new ImageIcon(TaskMenu.PATH_ASSET + "load.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlLoadSetTask(main);
        }
    },
    SAVE("Sauvegarder une liste",
        new ImageIcon(TaskMenu.PATH_ASSET + "save.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlSaveSetTask(main);
        }
    },
    SEP_TWO(null, null) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    QUIT("Quitter l'application",
        new ImageIcon(TaskMenu.PATH_ASSET + "quit.png")) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new ControlQuit(main);
        }
    };    
    
    private String title;
    private ImageIcon img;

    private Item(String t, ImageIcon img) {
        this.title = t;
        this.img = img;
    }

    public abstract ActionListener getEvent(EditorMain main);

    public String getTitle() {
        return this.title;
    }

    public ImageIcon getIcon() {
        return this.img;
    }
}
