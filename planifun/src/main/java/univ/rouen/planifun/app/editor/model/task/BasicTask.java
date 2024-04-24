package univ.rouen.planifun.app.editor.model.task;

import java.util.Calendar;

public interface BasicTask extends Task {
    
    // COMMANDS 
    
    public void setCompletionDate(int completionDate);

    public void setDefaultCalendar(Calendar calendar);
}
