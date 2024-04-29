package univ.rouen.planifun.app.editor.model.task.basic;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import univ.rouen.planifun.app.editor.model.task.Priority;

public class BooleanTask implements BasicTask {

    // ATTRIBUTES

    private String description;
    private Priority priority;
    private int completionDate;
    private boolean done;
    private Calendar defaultCalendar;

    // CONSTRUCTORS

    public BooleanTask(Calendar c) {
        this.defaultCalendar = (Calendar) c.clone();
        this.completionDate = 5;
        this.description = "";
        this.done = false;
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
        return this.done ? 100.0 : 0.0;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        String date = dateFormat.format(this.getExpiryDate());

        return this.description + " | " + this.priority.name() 
            + " | " + date;
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

    public void setIsDone(boolean done) {
        this.done = done;
    }
}
