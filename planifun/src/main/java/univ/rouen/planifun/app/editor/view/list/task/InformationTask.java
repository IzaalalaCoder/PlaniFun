package univ.rouen.planifun.app.editor.view.list.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.EditorMain;

/**
 * Manage display information's task
 */
public class InformationTask extends JPanel {

    // ATTRIBUTES

    private Task model;
    private JPanel panel;
    private final EditorMain parent;

    // CONSTRUCTORS

    public InformationTask(EditorMain main) {
        this.parent = main;
        this.setLayout(new BorderLayout());
        this.model = null;
        this.panel = null;
        this.setBackground(Color.darkGray);
    }
    
    // UTILS

    /**
     * setModel : display information's task
     * @param task : selected task
     * @param label : associate label
     * @param c : default calendar
     */
    public void setModel(Task task, JLabel label, Calendar c) {
        unsetModel();
        this.model = task;
        
        if (task instanceof NormalTask) {
            this.displayNormalTask(label);
        } else if (task instanceof BooleanTask) {
            this.displayBooleanTask(label);
        } else if (task instanceof ComplexTask) {
            this.displayComplexTask(label, c);
        }

        this.add(this.panel);
        this.revalidate();
        this.repaint();
    }

    /**
     * displayBooleanTask : display information of boolean task
     * @param l : label selected
     */
    private void displayBooleanTask(JLabel l) {
        this.panel = new DisplayBooleanTask((BooleanTask) this.model, l);
    }

    /**
     * displayNormalTask : display information of normal task
     * @param l : label selected
     */
    private void displayNormalTask(JLabel l) {
        this.panel = new DisplayNormalTask((NormalTask) this.model, l);
    }

    /**
     * displayComplexTask : display information of complex task
     * @param l : label selected
     * @param c : default calendar
     */
    private void displayComplexTask(JLabel l, Calendar c) {
        this.panel = new DisplayComplexTask(this.parent, (ComplexTask) this.model, l, c);
    }

    /**
     * unsetModel : remove model and this information
     */
    public void unsetModel() {
        this.setBackground(Color.white);
        if (model != null) {
            this.removeAllComponents(this);
            this.model = null;
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * removeAllComponents : remove all components
     * @param container : current container
     */
    private void removeAllComponents(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                removeAllComponents((Container) component);
            }
            container.remove(component);
        }
    }
}
