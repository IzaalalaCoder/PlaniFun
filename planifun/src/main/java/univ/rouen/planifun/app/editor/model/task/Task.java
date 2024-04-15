package univ.rouen.planifun.app.editor.model.task;

import java.util.Date;

public interface Task {

    // REQUESTS

    public String getDescription();

    public Date getExpiryDate();

    public Priority getPriority();

    public int getCompletionDate();

    public int getProgressStatus();

    // COMMANDS

    public void setPriority(Priority p);

    public void setExpiryDate(Date d);

    public void setDescription(String s);

    public void setCompletionDate(int i);
}
