package univ.rouen.planifun.app.editor.model.task.complex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ComplexTask implements Task {

    // ATTRIBUTES

    private List<Task> subTasks;
    private String description;
    private Priority priority;
    private int completionDate;
    private Double progressStatus;

    // CONSTRUCTORS

    public ComplexTask() {
        this.subTasks = new ArrayList<>();
        this.description = "";
        this.priority = Priority.NORMAL;
        this.completionDate = 0;
        this.progressStatus = 100.0;
    }

    // REQUESTS

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Date getExpiryDate() {
        Calendar calendar =  new GregorianCalendar();
        calendar.set(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        for (Task task : this.subTasks) {
            Calendar c = Calendar.getInstance();
            c.setTime(task.getExpiryDate());

            if (c.after(calendar)) {
                calendar = c;
            }
        }

        return calendar.getTime();
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public int getCompletionDate() {
        return completionDate;
    }

    @Override
    public Double getProgressStatus() {
        this.updateProgressStatus();
        return progressStatus;
    }

    @Override
    public String toString() {
        String message = this.description + " -- " + this.priority.name();

        for (Task t : this.subTasks) {
            message += "\n\t" + t.toString();
        }

        return message;
    }

    public List<Task> getAllSubTasks() {
        return this.subTasks;
    }

    public Task getTaskAtIndex(int index) {
        return this.subTasks.get(index);
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

    public void addTask(Task task) {
        this.subTasks.add(task);
        this.updateCompletionDate();
    }

    public void removeTask(Task task) {
        this.subTasks.remove(task);
        this.updateCompletionDate();
    }

    // UTILS

    private void updateProgressStatus() {
        if (this.subTasks.size() == 0) {
            this.progressStatus = 100.0;
            return;
        }

        int countCompleted = 0;
        for (Task t : this.subTasks) {
            if (t.getProgressStatus() == 100.0) {
                countCompleted++;
            }
        }

        this.progressStatus = (countCompleted * 100.0) / this.subTasks.size();
    }

    private void updateCompletionDate() {
        int completion = 0;

        for (Task t : this.subTasks) {
            completion += t.getCompletionDate();
        }

        this.completionDate = completion;
    }
}
