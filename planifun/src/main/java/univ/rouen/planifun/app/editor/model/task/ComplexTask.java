package univ.rouen.planifun.app.editor.model.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexTask implements Task {

    // ATTRIBUTES

    private List<Task> subTasks;
    private String description;
    private Date expiryDate;
    private Priority priority;
    private int completionDate;
    private int progressStatus;

    // CONSTRUCTORS

    public ComplexTask() {
        this.subTasks = new ArrayList<>();
        this.description = "";
        this.priority = Priority.NORMAL;
        this.completionDate = 5;
        this.progressStatus = 0;
    }

     // REQUESTS

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Date getExpiryDate() {
        return expiryDate;
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
    public int getProgressStatus() {
        return progressStatus;
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

    public void updateProgressStatus() {

    }

    public void addTask(Task task) {
        this.subTasks.add(task);
    }

    public void removeTask(Task task) {
        this.subTasks.remove(task);
    }
}