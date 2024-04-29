package univ.rouen.planifun.app.editor.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

public class ControlCreateTask implements ActionListener {

    // ATTRIBUTES
    
    private EditorMain main;
    
    // CONSTRUCTORS
    
    public ControlCreateTask(EditorMain main) {
        this.main = main;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.main.getModel() == null) {
            WarningPopUp.preventCreate("Vous deviez créer une liste de tâches avant de créer une tâche");
            return;
        }

        Task t = null;

        Calendar c = new GregorianCalendar();
        c.setTime(this.main.getModel().getCreationDate());

        String type = QuestionPopUp.inputTypeTask();
        if (type == null) {
            return;
        }
        switch (type) {
            case "NORMAL":
                t = new NormalTask(c);
                break;
            case "BOOLEEN":
                t = new BooleanTask(c);
                break;
            case "COMPLEXE":
                t = new ComplexTask(c);
                break;
            default:
                ErrorPopUp.preventUnknowType();
                return;
        };

        if (t != null) {
            String description = QuestionPopUp.inputString("La description de la tâche", 
                "Tâche " + (this.main.getModel().getSize() + 1));

            if (description == null || description.length() == 0) {
                t = null;
                return;
            }
            
            t.setDescription(description);
            this.main.getModel().addTaskInList(t);
        }
        
    }
    
}
