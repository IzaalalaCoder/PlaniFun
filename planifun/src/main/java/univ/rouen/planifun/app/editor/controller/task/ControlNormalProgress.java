package univ.rouen.planifun.app.editor.controller.task;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

/**
 * Implements ChangeListener to manage change progress of normal task
 */
public class ControlNormalProgress implements ChangeListener {

    // ATTRIBUTES

    private final NormalTask model;

    // CONSTRUCTORS

    public ControlNormalProgress(NormalTask model) {
        this.model = model;
    }
    
    // COMMANDS

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.model.setProgressStatus((double) spinner.getValue());
    }
}