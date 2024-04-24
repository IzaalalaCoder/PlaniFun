package univ.rouen.planifun.app.editor.model.task;

import java.util.Date;

public interface Task {

    // REQUESTS

    public String getDescription();

    public Date getExpiryDate();

    public Priority getPriority();

    public int getCompletionDate();

    public Double getProgressStatus();

    // COMMANDS

    public void setPriority(Priority priority);

    public void setExpiryDate(Date expiryDate);

    public void setDescription(String description);

    public void setCompletionDate(int completionDate);
}
