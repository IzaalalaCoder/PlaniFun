package univ.rouen.planifun.app.editor.model;

import univ.rouen.planifun.app.editor.model.task.Task;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Manage todolist
 */
public class SetTask {

    // CONSTANTS 

    public static String PROP_ADD_TASKS = "addTaskInList";
    public static String PROP_REMOVE_TASKS = "removeTaskInList";

    // ATTRIBUTES

    private final List<Task> tasks;
    private String name;
    private Calendar creationDate;
    private final PropertyChangeSupport pcs;

    // CONSTRUCTORS

    public SetTask() {
        this.tasks = new ArrayList<>();
        this.creationDate = new GregorianCalendar();
        this.name = "";
        this.pcs = new PropertyChangeSupport(this);
    }

    public SetTask(String name, Calendar calendar) {
        this.tasks = new ArrayList<>();
        this.creationDate = calendar;
        this.name = name;
        this.pcs = new PropertyChangeSupport(this);
    }

    // REQUESTS

    /**
     * getAllTask : return all task found in todolist
     * @return Task[]
     */
    public List<Task> getAllTask() {
        return this.tasks;
    }

    /**
     * getName : return todolist name
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * getSize : return number of task in todolist
     * @return int
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * getCreationDate : return date of creation of this todolist
     * @return Date
     */
    public Date getCreationDate() {
        return this.creationDate.getTime();
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

    /**
     * addTaskInList : add task if not already added in todolist
     * @param task : task
     */
    public void addTaskInList(Task task) {
        if (task == null || this.tasks.contains(task)) {
            throw new IllegalArgumentException();
        }
        this.tasks.add(task);
        this.pcs.firePropertyChange(SetTask.PROP_ADD_TASKS, null, task);
    }

    /**
     * removeTaskInList : remove task if not already removed from todolist
     * @param task : task
     */
    public void removeTaskInList(Task task) {
        if (task == null || !this.tasks.contains(task)) {
            throw new IllegalArgumentException();
        }
        this.tasks.remove(task);
        this.pcs.firePropertyChange(SetTask.PROP_REMOVE_TASKS, null, task);
    }

    /**
     * setName : change name of todolist
     * @param name : name of this todolist
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * setCalendar : change calendar of todolist
     * @param calendar : default calendar of this todolist
     */
    public void setCalendar(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException();
        }
        this.creationDate = calendar;
    }
}