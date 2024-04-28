package univ.rouen.planifun.app.editor.controller.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;

public class ControlBooleanProgress implements ActionListener {

    // ATTRIBUTES

    private BooleanTask model;

    // CONSTRUCTORS

    public ControlBooleanProgress(BooleanTask model) {
        this.model = model;
    }

    // REQUESTS
    // COMMANDS
    // UTILS

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.setIsDone(this.model.getProgressStatus() == 0.0);
    }
    
}
