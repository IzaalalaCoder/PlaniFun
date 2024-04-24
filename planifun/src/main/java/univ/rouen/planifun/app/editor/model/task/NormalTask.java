package univ.rouen.planifun.app.editor.model.task;

import java.util.Calendar;
import java.util.Date;

public class NormalTask implements BasicTask {

    // ATTRIBUTES

    private String description;
    private Priority priority;
    private int completionDate;
    private Double progressStatus;
    private Calendar defaultCalendar;

    // CONSTRUCTORS

    public NormalTask() {
        this.defaultCalendar = null;
        this.completionDate = 5;
        this.description = "";
        this.progressStatus = 0.0;
        this.priority = Priority.NORMAL;
    }

    // REQUESTS

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Date getExpiryDate() {
        Calendar c = this.defaultCalendar;
        c.add(Calendar.DAY_OF_MONTH, completionDate);
        return c.getTime();
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
        return this.progressStatus;
    }

    // COMMANDS

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setCompletionDate(int completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public void setDefaultCalendar(Calendar c) {
        this.defaultCalendar = c;
    }

    public void setProgressStatus(Double progressStatus) {
        this.progressStatus = progressStatus;
    }
}
