package univ.rouen.planifun.app.editor.model.task;

import java.util.Date;

public interface Task {

    // REQUESTS

    public String getDescription();

    public Priority getPriority();

    public int getCompletionDate();
    
    public Date getExpiryDate();

    public Double getProgressStatus();

    // COMMANDS

    public void setPriority(Priority priority);

    public void setDescription(String description);
}
