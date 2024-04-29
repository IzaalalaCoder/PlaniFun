package univ.rouen.planifun.app.editor.model.task.basic;

import univ.rouen.planifun.app.editor.model.task.Task;

public interface BasicTask extends Task {
    
    // COMMANDS 
    
    public void setCompletionDate(int completionDate);
}
