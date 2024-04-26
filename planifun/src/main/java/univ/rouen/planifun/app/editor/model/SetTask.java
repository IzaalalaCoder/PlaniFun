package univ.rouen.planifun.app.editor.model;

import univ.rouen.planifun.app.editor.model.task.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SetTask {

    // ATTRIBUTES

    private List<Task> tasks;
    private String name;
    private Calendar creationDate;

    // CONSTRUCTORS

    public SetTask() {
        this.tasks = new ArrayList<>();
        this.creationDate = new GregorianCalendar();
        this.name = "";
    }

    public SetTask(String name, Calendar calendar) {
        this.tasks = new ArrayList<>();
        this.creationDate = calendar;
        this.name = name;
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

    // COMMANDS

    public void addTaskInList(Task task) {
        this.tasks.add(task);
    }

    public void removeTaskAtIndexInList(int index) {
        this.tasks.remove(index);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalendar(Calendar calendar) {
        this.creationDate = calendar;
    }
}
