package univ.rouen.planifun.app.editor.model.task.complex;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Implements Task for manage complex task
 */
public class ComplexTask implements Task {

    // STATIC CONSTANTS

    public final static String PROP_ADD_SUB_TASKS = "addTask";
    public final static String PROP_REMOVE_SUB_TASKS = "removeTask";

    // ATTRIBUTES

    private final List<Task> subTasks;
    private String description;
    private Priority priority;
    private int completionDate;
    private Double progressStatus;
    private final Calendar defaultCalendar;
    private final PropertyChangeSupport pcs;

    // CONSTRUCTORS

    public ComplexTask(Calendar c) {
        this.defaultCalendar = (Calendar) c.clone();
        this.pcs = new PropertyChangeSupport(this);
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
        if (subTasks.isEmpty()) {
            return null; 
        }

        Calendar c = (Calendar) this.defaultCalendar.clone();
        c.add(Calendar.DAY_OF_MONTH, this.maxCompletionDate());

        return c.getTime();
    }   

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public int getCompletionDate() {
        this.updateCompletionDate();
        return completionDate;
    }

    @Override
    public Double getProgressStatus() {
        this.updateProgressStatus();
        return progressStatus;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder(this.description
                + " | " + this.priority.name());

        for (Task t : this.subTasks) {
            message.append("\n\t").append(t.toString());
        }

        return message.toString();
    }

    /**
     * getAllSubTasks : return all sub tasks
     * @return Task[]
     */
    public List<Task> getAllSubTasks() {
        return this.subTasks;
    }

    /**
     * getPropertyChangeListeners : return all listener on this model and this property name
     * @param pName : property name
     * @return PropertyChangeListener[]
     */
    public PropertyChangeListener[] getPropertyChangeListeners(String pName) {
        return this.pcs.getPropertyChangeListeners(pName);
    }

    // COMMANDS

    /**
     * addPropertyChangeListener : add listener on property name
     * @param pName : property name
     * @param pcl : listener of property
     */
    public void addPropertyChangeListener(String pName, PropertyChangeListener pcl) {
        if (pName == null) {
            throw new AssertionError();
        }
        this.pcs.addPropertyChangeListener(pName, pcl);
    }

    /**
     * removePropertyChangeListener : remove listener on property name
     * @param pName : property name
     * @param pcl : listener of property
     */
    public void removePropertyChangeListener(String pName, PropertyChangeListener pcl) {
        if (pName == null) {
            throw new AssertionError();
        }
        this.pcs.removePropertyChangeListener(pName, pcl);
    }

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

    /**
     * addTask : add task if not already added in subtasks
     * @param task : task
     */
    public void addTask(Task task) {
        if (task == null || this.subTasks.contains(task)) {
            throw new IllegalArgumentException();
        }
        this.subTasks.add(task);
        this.pcs.firePropertyChange(PROP_ADD_SUB_TASKS, null, task);
    }

    /**
     * removeTask : remove task if not already removed from subtasks
     * @param task : task
     */
    public void removeTask(Task task) {
        if (task == null || !this.subTasks.contains(task)) {
            throw new IllegalArgumentException();
        }
        this.subTasks.remove(task);
        this.pcs.firePropertyChange(PROP_REMOVE_SUB_TASKS, null, task);
    }

    // UTILS

    /**
     * updateProgressStatus : update progress of this task about subtasks
     */
    private void updateProgressStatus() {
        if (this.subTasks.isEmpty()) {
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

    /**
     * updateCompletionDate : update delay of this task about subtasks
     */
    private void updateCompletionDate() {
        int completion = 0;

        for (Task t : this.subTasks) {
            completion += t.getCompletionDate();
        }

        this.completionDate = completion;
    }

    /**
     * maxCompletionDate : return maximum completion date of subtasks
     * @return int
     */
    private int maxCompletionDate() {
        int maxCompletionDate = 0;

        for (Task t : this.subTasks) {
            maxCompletionDate = Math.max(maxCompletionDate, t.getCompletionDate());
        }

        return maxCompletionDate;
    }
}