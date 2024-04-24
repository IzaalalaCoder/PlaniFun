package univ.rouen.planifun.app.editor.model;

import univ.rouen.planifun.app.editor.model.task.ComplexTask;
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

    public SetTask(String name) {
        this.tasks = new ArrayList<>();
        this.creationDate = new GregorianCalendar();
        this.name = name;
    }

    public SetTask(String name, Calendar calendar) {
        this.tasks = new ArrayList<>();
        this.creationDate = calendar;
        this.name = name;
    }

    // REQUESTS

    public Task getTaskAtIndexInList(int index) {
        return this.tasks.get(index);
    }

    public Task getTaskAtIndexInComplexTask(ComplexTask complexTask, int index) {
        return complexTask.getTaskAtIndex(index);
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

    public void addTaskInComplexTask(ComplexTask complexTask, Task task) {
        complexTask.addTask(task);
    }

    public void removeTaskInComplexTask(ComplexTask complexTask, Task task) {
        complexTask.removeTask(task);
    }

    public void setName(String name) {
        this.name = name;
    }
}
