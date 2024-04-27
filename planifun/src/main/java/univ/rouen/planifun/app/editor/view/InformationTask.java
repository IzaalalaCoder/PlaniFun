package univ.rouen.planifun.app.editor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.task.DisplayBooleanTask;
import univ.rouen.planifun.app.editor.view.task.DisplayNormalTask;

public class InformationTask extends JPanel {
    public InformationTask() {
        this.setLayout(new BorderLayout());
        this.model = null;
        this.setBackground(Color.darkGray);
    }

    private Task model;

    public void setModel(Task task) {
        unsetModel();
        this.model = task;
        
        if (task instanceof NormalTask) {
            this.displayNormalTask();
        } else if (task instanceof BooleanTask) {
            this.displayBooleanTask();
        } else if (task instanceof ComplexTask) {
            this.displayComplexTask();
        }

        this.revalidate();
        this.repaint();
    }

    private void displayBooleanTask() {
        this.add(new DisplayBooleanTask((BooleanTask) this.model));

    }

    private void displayNormalTask() {
        this.add(new DisplayNormalTask((NormalTask) this.model));
    }

    private void displayComplexTask() {

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
