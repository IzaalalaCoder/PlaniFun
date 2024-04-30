package univ.rouen.planifun.app.editor.model.task.basic;

import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Manage no complex task
 */
public interface BasicTask extends Task {
    
    // COMMANDS 

    /**
     * setCompletionDate : modify completion date
     * @param completionDate : delay of task
     */
    void setCompletionDate(int completionDate);
}