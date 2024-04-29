package univ.rouen.planifun.app.editor.controller.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.list.task.DisplayComplexTask;
import univ.rouen.planifun.app.editor.view.popup.ErrorPopUp;
import univ.rouen.planifun.app.editor.view.popup.QuestionPopUp;

public class ControlCreateSubTask implements ActionListener {

    // ATTRIBUTES
    
    private DisplayComplexTask parent;
    
    // CONSTRUCTORS
    
    public ControlCreateSubTask(DisplayComplexTask parent) {
        this.parent = parent;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        Task t = null;
        Calendar c = this.parent.getCalendar();

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
            String description = QuestionPopUp.inputString("La description de la sous-tâche", 
                "Sous-tâche " + (this.parent.getModel().getAllSubTasks().size() + 1));
            
            if (description == null || description.length() == 0) {
                t = null;
                return;
            }

            t.setDescription(description);
            this.parent.getModel().addTask(t);
        }
        
    }
    
}
