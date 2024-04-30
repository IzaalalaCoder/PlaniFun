package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

/**
 * Implements ActionListener to create new task
 */
public class ControlCreateTask implements ActionListener {

    // ATTRIBUTES
    
    private final EditorMain main;
    
    // CONSTRUCTORS
    
    public ControlCreateTask(EditorMain main) {
        this.main = main;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.main.getModel() == null) {
            ErrorPopUp.preventCreate("Vous deviez créer une liste de tâches avant de créer une tâche");
            return;
        }
        Calendar c = new GregorianCalendar();
        c.setTime(this.main.getModel().getCreationDate());

        Task t = QuestionPopUp.inputTypeTask(c);
        if (t == null) {
            ErrorPopUp.preventUnknownType();
            return;
        }

        String description = QuestionPopUp.inputString("La description de la tâche",
            "Tâche " + (this.main.getModel().getSize() + 1));
        if (description == null || description.isEmpty()) {
            t = null;
            return;
        }

        t.setDescription(description);
        this.main.getModel().addTaskInList(t);
        
    }
}