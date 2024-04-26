package univ.rouen.planifun.app.editor.view.menu;

public enum Item {
    ADD("Ajouter une tâche"),
    SEP_ONE(null),
    LOAD("Charger une liste"),
    SAVE("Sauvegarder une liste"),
    SEP_TWO(null),
    QUIT("Quitter l'application");    
    
    private String title;

    private Item(String t) {
        this.title = t;
    }

    public String getTitle() {
        return this.title;
    }
}
