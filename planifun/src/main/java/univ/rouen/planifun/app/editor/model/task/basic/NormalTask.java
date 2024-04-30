package univ.rouen.planifun.app.editor.model.task.basic;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import univ.rouen.planifun.app.editor.model.task.Priority;

/**
 * Implements BasicTask for manage normal task
 */
public class NormalTask implements BasicTask {

    // ATTRIBUTES

    private String description;
    private Priority priority;
    private int completionDate;
    private Double progressStatus;
    private final Calendar defaultCalendar;

    // CONSTRUCTORS

    public NormalTask(Calendar c) {
        this.defaultCalendar = (Calendar) c.clone();
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
        Calendar c = (Calendar) this.defaultCalendar.clone();
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

    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        String date = dateFormat.format(this.getExpiryDate());

        return this.description + " | " + this.priority.name() 
            + " | complété à " + this.progressStatus + "%"
            + " | " + date;
    }

    // COMMANDS

    @Override
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new NullPointerException();
        }
        this.priority = priority;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.description = description;
    }

    @Override
    public void setCompletionDate(int completionDate) {
        if (completionDate < 0) {
            throw new IllegalArgumentException();
        }
        this.completionDate = completionDate;
    }

    /**
     * setProgressStatus : modify task progress
     * @param progressStatus : task progress
     */
    public void setProgressStatus(Double progressStatus) {
        if (progressStatus < 0.0 || progressStatus > 100.0) {
            throw new IllegalArgumentException();
        }
        this.progressStatus = progressStatus;
    }
}