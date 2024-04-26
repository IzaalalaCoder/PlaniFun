package univ.rouen.planifun.app.editor.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Dimension;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.view.menu.Item;

public class EditorMain extends JFrame {

    // CONSTANTS 

    private final String TITLE = "Planifun";
    
    // ATTRIBUTES

    private ListTask leftComponent;
    private InformationTask mainComponent;
    private SetTask model;

    // CONSTRUCTORS

    public EditorMain() {
        this.model = null;
        this.createMenu();
        this.initializeWindow();
        this.createComponent();
        this.placeComponent();
        //this.createController();
    }

    // REQUESTS

    public SetTask getModel() {
        return this.model;
    }

    // COMMANDS

    public void setModel(SetTask model) {
        this.model = model;

        this.leftComponent.setModel(model);
        this.mainComponent.unsetModel();
        this.setTitle(TITLE + " | " + this.model.getName());
    }

    public void refreshModel() {
        this.leftComponent.refresh();
    }

    // UTILS

    private void createMenu() {
        JMenu menu = new JMenu("Gestion");
        for (Item it : Item.values()) {
            if (it.getTitle() == null) {
                menu.addSeparator();
            } else {
                JMenuItem item = new JMenuItem(it.getTitle());
                item.addActionListener(it.getEvent(this));
                menu.add(item);
            }
        }

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
    }

    private void initializeWindow() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(600, 500));
        this.setTitle(TITLE);
    }

    private void createComponent() {
        this.leftComponent = new ListTask();
        this.mainComponent = new InformationTask();
    }

    private void placeComponent() {
        this.add(leftComponent, BorderLayout.WEST);
        this.add(this.mainComponent, BorderLayout.CENTER);
    }
    
}
