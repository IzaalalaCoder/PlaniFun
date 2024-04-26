package univ.rouen.planifun.app.editor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ListTask extends JPanel {

    // CONSTANTS

    private final Color COLOR_NULL = Color.darkGray;
    private final Color COLOR_NOT_NULL = Color.white;

    // ATTRIBUTES

    private SetTask model;
    private Map<Task, JPanel> panels;

    // CONSTRUCTORS

    public ListTask() {
        this.panels = new HashMap<>();
        this.model = null;
        this.initializeComponent();        
    }

    // REQUESTS
    // COMMANDS

    public void setModel(SetTask model) {
        this.removeAllControllers();
        this.removeAllComponents();
        this.model = model;
        this.createComponents();
        this.setBackground(COLOR_NOT_NULL);
    }

    // UTILS

    private void initializeComponent() {
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(COLOR_NULL);

        this.setPreferredSize(new Dimension(200, 0));

        Border redBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        Border newBorder = BorderFactory.createCompoundBorder(redBorder, this.getBorder());
        this.setBorder(newBorder);
    }

    public void removeAllControllers() {
    }

    public void refresh() {
        for (Task t : this.model.getAllTask()) {
            if (!this.panels.containsKey(t)) {
                JPanel p = this.createTaskComponent(t);
                this.add(p);
                this.panels.put(t, p);
                this.setVisible(true);
                //this.pack();
            }
        }
    }

    private void removeAllComponents() {
        this.removeAll();
        this.panels.clear();
    }

    private void createComponents() {
        for (Task t : this.model.getAllTask()) {
            JPanel p = this.createTaskComponent(t);
            this.add(p);
            this.panels.put(t, p);
        }
    }

    private JPanel createTaskComponent(Task task) {
        JPanel panel = new JPanel();

        panel.add(new JLabel(task.getDescription()));
        panel.add(new JLabel(task.getPriority().name()));

        return panel;
    }
}
