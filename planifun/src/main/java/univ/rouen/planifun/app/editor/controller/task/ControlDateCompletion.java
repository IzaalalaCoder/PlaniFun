package univ.rouen.planifun.app.editor.controller.task;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import univ.rouen.planifun.app.editor.model.task.basic.BasicTask;

public class ControlDateCompletion implements ChangeListener {

    // ATTRIBUTES

    private BasicTask model;

    // CONSTRUCTORS

    public ControlDateCompletion(BasicTask model) {
        this.model = model;
    }
    
    // COMMANDS

    @Override
    public void stateChanged(ChangeEvent e) {
        this.model.setCompletionDate((Integer) ((JSpinner) e.getSource()).getValue()); 
    }
    
}
