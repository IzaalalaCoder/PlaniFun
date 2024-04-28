package univ.rouen.planifun.app.editor.view.menu;

public enum Menu {

    // VALUES

    FILE(new Item[] {Item.LOAD, Item.SAVE, Item.SEP, Item.QUIT}),
    TASK(new Item[] {Item.CREATE, Item.ADD}),
    HELP(new Item[] {Item.HELP});

    // ATTRIBUTES

    private Item[] items;

    // CONSTRUCTORS

    private Menu(Item[] items) {
        this.items = items;
    }

    // REQUESTS

    public Item[] getItems() {
        return this.items;
    }
}
