package univ.rouen.planifun.app.editor.view.menu;

public enum Menu {
    FILE(new Item[] {Item.LOAD, Item.SAVE, Item.SEP, Item.QUIT}),
    TASK(new Item[] {Item.CREATE, Item.ADD}),
    HELP(new Item[] {Item.HELP});

    private Item[] items;

    private Menu(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return this.items;
    }
}
