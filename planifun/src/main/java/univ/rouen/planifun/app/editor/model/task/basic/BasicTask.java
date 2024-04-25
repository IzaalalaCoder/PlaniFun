package univ.rouen.planifun.app.editor.model.task.basic;

import java.util.Calendar;

public interface BasicTask {
    
    // COMMANDS 
    
    public void setCompletionDate(int completionDate);

    public void setDefaultCalendar(Calendar calendar);
}
