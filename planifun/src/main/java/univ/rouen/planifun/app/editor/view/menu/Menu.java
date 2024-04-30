package univ.rouen.planifun.app.editor.view.menu;

/**
 * Represent all menu for this application
 */
public enum Menu {

    // VALUES

    /**
     * Menu for manage file and application
     */
    FILE(new Item[] {Item.LOAD, Item.SAVE, Item.SEP, Item.QUIT}),

    /**
     * Menu for manage todolist and task
     */
    TASK(new Item[] {Item.CREATE, Item.ADD}),

    /**
     * Menu for manage help and other information.
     */
    HELP(new Item[] {Item.HELP});

    // ATTRIBUTES

    private final Item[] items;

    // CONSTRUCTORS

    private Menu(Item[] items) {
        this.items = items;
    }

    // REQUESTS

    /**
     * getItems : return all item's menus
     * @return Item[]
     */
    public Item[] getItems() {
        return this.items;
    }
}
