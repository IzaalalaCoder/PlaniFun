package univ.rouen.planifun.app.editor.model.task;

import java.util.Date;

/**
 * Manage task
 */
public interface Task {

    // REQUESTS

    /**
     * getDescription : return description's task
     * @return String
     */
    String getDescription();

    /**
     * getPriority : return priority's task
     * @return Priority
     */
    Priority getPriority();

    /**
     * getCompletionDate : return delay's task
     * @return int
     */
    int getCompletionDate();

    /**
     * getExpiryDate : return expiry date's task
     * @return Date
     */
    Date getExpiryDate();

    /**
     * getProgressStatus : return progress status's task
     * @return Double
     */
    Double getProgressStatus();

    // COMMANDS

    /**
     * setPriority : change priority
     * @param priority : priority of task
     */
    void setPriority(Priority priority);

    /**
     * setDescription : change description
     * @param description : description of task
     */
    void setDescription(String description);
}