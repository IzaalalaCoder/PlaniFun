package univ.rouen.planifun.app.builder;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryBooleanTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryComplexTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryNormalTask;
import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BasicTask;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

/**
 * Implements BuilderTask and
 */
public class ConcreteBuilderSetTask implements BuilderSetTask {

    // ATTRIBUTES

    private SetTask setTask;
    private Task lastTask;

    // CONSTRUCTORS

    public ConcreteBuilderSetTask() {
        setTask = null;
        lastTask = null;
    }

    // REQUESTS

    public SetTask getSetTask() {
        return setTask;
    }

    public Task getLastTask() {
        return lastTask;
    }

    // COMMANDS

    @Override
    public void startSetTask() {
        setTask = new SetTask();
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.setTask.setName(name);
    }

    @Override
    public void setCalendar(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("Calendar cannot be null");
        }
        this.setTask.setCalendar(calendar);
    }

    @Override
    public void addTaskInSetTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        this.setTask.addTaskInList(task);
    }

    @Override
    public void addTaskInComplexTask(ComplexTask complexTask, Task task) {
        if (complexTask == null) {
            throw new IllegalArgumentException("ComplexTask cannot be null");
        }
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        complexTask.addTask(task);
    }

    @Override
    public void createNormalTask(Calendar c) {
        if (c == null) {
            throw new IllegalArgumentException("Calendar cannot be null");
        }
        this.lastTask = new FactoryNormalTask().createTask(c);
    }

    @Override
    public void createBooleanTask(Calendar c) {
        if (c == null) {
            throw new IllegalArgumentException("Calendar cannot be null");
        }
        this.lastTask = new FactoryBooleanTask().createTask(c);
    }

    @Override
    public void createComplexTask(Calendar c) {
        if (c == null) {
            throw new IllegalArgumentException("Calendar cannot be null");
        }
        this.lastTask = new FactoryComplexTask().createTask(c);
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.lastTask.setDescription(description);
    }

    @Override
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        this.lastTask.setPriority(priority);
    }

    @Override
    public void setProgress(double progress) {
        if (progress < 0 || progress > 100.0) {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        }
        ((NormalTask) lastTask).setProgressStatus(progress);
    }

    @Override
    public void setIsDone(boolean isDone) {
        ((BooleanTask) lastTask).setIsDone(isDone);
    }

    @Override
    public void setCompletion(int completion) {
        if (completion < 0) {
            throw new IllegalArgumentException("Completion cannot be negative");
        }
        ((BasicTask) lastTask).setCompletionDate(completion);
    }
}