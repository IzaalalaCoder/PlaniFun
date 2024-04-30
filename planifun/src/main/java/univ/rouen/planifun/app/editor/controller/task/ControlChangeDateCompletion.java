package univ.rouen.planifun.app.editor.controller.task;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import univ.rouen.planifun.app.editor.model.task.basic.BasicTask;

/**
 * Implements ChangeListener to manage change date completion of basic task
 */
public class ControlChangeDateCompletion implements ChangeListener {

    // ATTRIBUTES

    private final BasicTask model;

    // CONSTRUCTORS

    public ControlChangeDateCompletion(BasicTask model) {
        this.model = model;
    }
    
    // COMMANDS

    @Override
    public void stateChanged(ChangeEvent e) {
        this.model.setCompletionDate((Integer) ((JSpinner) e.getSource()).getValue()); 
    }
}