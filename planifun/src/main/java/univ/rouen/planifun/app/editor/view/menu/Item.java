package univ.rouen.planifun.app.editor.view.menu;

public enum Item {
    LOAD("Charger une liste"),
    SAVE("Sauvegarder une liste"),
    SEP(null),
    QUIT("Quitter l'application");    
    
    private String title;

    private Item(String t) {
        this.title = t;
    }

    public String getTitle() {
        return this.title;
    }
}
