package univ.rouen.planifun.app.editor.view.menu;

import java.awt.event.ActionListener;

import univ.rouen.planifun.app.editor.controller.menu.CreateSetTask;
import univ.rouen.planifun.app.editor.controller.menu.Quit;
import univ.rouen.planifun.app.editor.view.EditorMain;

public enum Item {
    CREATE("Créer une nouvelle liste de tâche") {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new CreateSetTask(main);
        }
    },
    ADD("Ajouter une tâche") {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    SEP_ONE(null) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    LOAD("Charger une liste") {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    SAVE("Sauvegarder une liste") {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    SEP_TWO(null) {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return null;
        }
    },
    QUIT("Quitter l'application") {
        @Override
        public ActionListener getEvent(EditorMain main) {
            return new Quit(main);
        }
    };    
    
    private String title;

    private Item(String t) {
        this.title = t;
    }

    public abstract ActionListener getEvent(EditorMain main);

    public String getTitle() {
        return this.title;
    }
}
