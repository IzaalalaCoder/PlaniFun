package univ.rouen.planifun.app.editor.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.list.ListTask;
import univ.rouen.planifun.app.editor.view.list.task.InformationTask;
import univ.rouen.planifun.app.editor.view.menu.TaskMenu;

public class EditorMain extends JFrame {

    // CONSTANTS 

    private final String TITLE = "Planifun";
    
    // ATTRIBUTES

    private ListTask leftComponent;
    private InformationTask mainComponent;
    private SetTask model;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;

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

    public void setTask(Task task, JLabel label) {
        Calendar c = new GregorianCalendar();
        c.setTime(this.model.getCreationDate());
        this.mainComponent.setModel(task, label, c);
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
        this.leftComponent = new ListTask(this);
        this.mainComponent = new InformationTask();

        this.scrollPane = new JScrollPane(this.leftComponent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, mainComponent);
        splitPane.setOneTouchExpandable(true);

    }

    private void placeComponent() {
        this.add(this.splitPane);
    }
    
}
