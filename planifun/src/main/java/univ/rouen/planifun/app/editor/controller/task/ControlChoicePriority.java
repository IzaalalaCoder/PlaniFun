package univ.rouen.planifun.app.editor.controller.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

public class ControlChoicePriority implements ActionListener {

    // ATTRIBUTES

    private Task model;

    // CONSTRUCTORS
    
    public ControlChoicePriority(Task model) {
        this.model = model;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        int choicePriority = QuestionPopUp.inputChoicePriority();

        if (choicePriority != -1) {
            this.model.setPriority(Priority.values()[choicePriority]);
            JButton btn = (JButton) e.getSource();
            btn.setText(this.model.getPriority().name());
        }
    }
    
}
