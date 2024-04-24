package univ.rouen.planifun.app.editor.model.task;

import java.util.Date;

public class BooleanTask implements Task {

    // ATTRIBUTES

    private String description;
    private Date expiryDate;
    private Priority priority;
    private int completionDate;
    private boolean done;

    // CONSTRUCTORS

    public BooleanTask() {
        this.completionDate = 5;
        this.description = "";
        this.done = false;
        this.expiryDate = null;
        this.priority = Priority.NORMAL;
    }

    // REQUESTS

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public int getCompletionDate() {
        return this.completionDate;
    }

    @Override
    public Double getProgressStatus() {
        return this.done ? 100.0 : 0.0;
    }

    // COMMANDS

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setCompletionDate(int completionDate) {
        this.completionDate = completionDate;
    }

    public void setIsDone(boolean done) {
        this.done = done;
    }
}
