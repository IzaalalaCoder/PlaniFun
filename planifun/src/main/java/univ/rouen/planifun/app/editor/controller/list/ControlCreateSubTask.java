package univ.rouen.planifun.app.editor.controller.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.list.task.DisplayComplexTask;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

/**
 * Implements ActionListener and manage create new subtask
 */
public class ControlCreateSubTask implements ActionListener {

    // ATTRIBUTES

    private final DisplayComplexTask parent;

    // CONSTRUCTORS

    public ControlCreateSubTask(DisplayComplexTask parent) {
        this.parent = parent;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.parent.getModel() == null) {
            ErrorPopUp.preventCreate("Vous deviez créer une liste de tâches avant de créer une tâche");
            return;
        }
        Calendar c = (Calendar) this.parent.getCalendar().clone();

        Task t = QuestionPopUp.inputTypeTask(c);
        if (t == null) {
            ErrorPopUp.preventUnknownType();
            return;
        }

        String description = QuestionPopUp.inputString("La description de la sous-tâche",
                "Sous-tâche " + (this.parent.getModel().getAllSubTasks().size() + 1));
        if (description == null || description.isEmpty()) {
            t = null;
            return;
        }

        t.setDescription(description);
        this.parent.getModel().addTask(t);
    }
}