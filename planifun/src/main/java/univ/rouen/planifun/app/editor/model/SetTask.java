package univ.rouen.planifun.app.editor.model;

import univ.rouen.planifun.app.editor.model.task.Task;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SetTask {

    // CONSTANTS 

    public static String PROP_ADD_TASKS = "addTaskInList";
    public static String PROP_REMOVE_TASKS = "removeTaskInList";

    // ATTRIBUTES

    private List<Task> tasks;
    private String name;
    private Calendar creationDate;
    private PropertyChangeSupport pcs;

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

    public List<Task> getAllTask() {
        return this.tasks;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Date getCreationDate() {
        return this.creationDate.getTime();
    }

    public PropertyChangeListener[] getPropertyChangeListeners(String pName) {
        if (pName == null) {
            throw new AssertionError();
        }
        return pcs.getPropertyChangeListeners(pName);
    }

    // COMMANDS

    public void addPropertyChangeListener(String pName, PropertyChangeListener pcl) {
        if (pName == null) {
            throw new AssertionError();
        }
        this.pcs.addPropertyChangeListener(pName, pcl);
    }

    public void removePropertyChangeListener(String pName, PropertyChangeListener pcl) {
        if (pName == null) {
            throw new AssertionError();
        }
        this.pcs.removePropertyChangeListener(pName, pcl);
    }

    public void addTaskInList(Task task) {
        this.tasks.add(task);
        this.pcs.firePropertyChange(SetTask.PROP_ADD_TASKS, null, task);
    }

    public void removeTaskInList(Task task) {
        this.tasks.remove(task);
        this.pcs.firePropertyChange(SetTask.PROP_REMOVE_TASKS, null, task);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalendar(Calendar calendar) {
        this.creationDate = calendar;
    }
}
