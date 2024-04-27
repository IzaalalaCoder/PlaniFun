package univ.rouen.planifun.app.editor.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.view.menu.TaskMenu;

public class EditorMain extends JFrame {

    // CONSTANTS 

    private final String TITLE = "Planifun";
    
    // ATTRIBUTES

    private ListTask leftComponent;
    private InformationTask mainComponent;
    private SetTask model;
    private JScrollPane scrollPane;

    // CONSTRUCTORS

    public EditorMain() {
        this.model = null;
        this.createMenu();
        this.initializeWindow();
        this.createComponent();
        this.placeComponent();
    }

    // REQUESTS

    public SetTask getModel() {
        return this.model;
    }

    // COMMANDS

    public void setModel(SetTask model) {
        this.model = model;

        this.leftComponent.setModel(this.model);
        this.mainComponent.unsetModel();
        this.setTitle(TITLE + " | " + this.model.getName());
    }

    // UTILS

    private void createMenu() {
        this.setJMenuBar(new TaskMenu(this));
    }

    private void initializeWindow() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(600, 500));
        this.setTitle(TITLE);
        this.setLocationRelativeTo(null);
    }

    private void createComponent() {
        this.leftComponent = new ListTask();
        this.mainComponent = new InformationTask();

        this.scrollPane = new JScrollPane(this.leftComponent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    private void placeComponent() {
        this.add(this.scrollPane, BorderLayout.WEST);
        this.add(this.mainComponent, BorderLayout.CENTER);
    }
    
}
