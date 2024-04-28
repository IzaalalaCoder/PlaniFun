package univ.rouen.planifun.app.editor.controller.task;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

public class ControlNormalProgress implements ChangeListener {

    // ATTRIBUTES

    private NormalTask model;

    // CONSTRUCTORS

    public ControlNormalProgress(NormalTask model) {
        this.model = model;
    }
    
    // COMMANDS

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.model.setProgressStatus(Double.valueOf((double) spinner.getValue()));
    }
    
}