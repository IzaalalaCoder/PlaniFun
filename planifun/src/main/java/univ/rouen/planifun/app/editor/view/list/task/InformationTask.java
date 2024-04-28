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

public class InformationTask extends JPanel {

    // ATTRIBUTES

    private Task model;
    private JPanel panel;
    private EditorMain parent;

    // CONSTRUCTORS

    public InformationTask(EditorMain main) {
        this.parent = main;
        this.setLayout(new BorderLayout());
        this.model = null;
        this.panel = null;
        this.setBackground(Color.darkGray);
    }
    
    // UTILS

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

    private void displayBooleanTask(JLabel l) {
        this.panel = new DisplayBooleanTask(this.parent, (BooleanTask) this.model, l);
    }

    private void displayNormalTask(JLabel l) {
        this.panel = new DisplayNormalTask(this.parent, (NormalTask) this.model, l);
    }

    private void displayComplexTask(JLabel l, Calendar c) {
        this.panel = new DisplayComplexTask(this.parent, (ComplexTask) this.model, l, c);
    }
 
    public void unsetModel() {
        this.setBackground(Color.white);
        if (model != null) {
            this.removeAllControllers();
            this.removeAllComponents();
            this.model = null;
        }
    }

    public void removeAllControllers() {
    }

    public void removeAllComponents() {
        this.removeAllComponentsRecursive(this);
    }

    private void removeAllComponentsRecursive(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                removeAllComponentsRecursive((Container) component);
            }
            container.remove(component);
        }
    }
}
