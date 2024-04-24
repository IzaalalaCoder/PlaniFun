package univ.rouen.planifun.app.editor.model.task.basic;

import java.util.Calendar;

import univ.rouen.planifun.app.editor.model.task.Task;

public interface BasicTask extends Task {
    
    // COMMANDS 
    
    public void setCompletionDate(int completionDate);

    public void setDefaultCalendar(Calendar calendar);
}
