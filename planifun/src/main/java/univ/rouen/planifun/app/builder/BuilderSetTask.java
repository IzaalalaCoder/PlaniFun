package univ.rouen.planifun.app.builder;

import java.util.Calendar;

import univ.rouen.planifun.app.editor.model.task.Priority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

/**
 * Is a todolist interface's constructor.
 */
public interface BuilderSetTask {

    // COMMANDS

    void startSetTask();

    void setName(String name);

    void setCalendar(Calendar calendar);

    void addTaskInSetTask(Task task);

    void addTaskInComplexTask(ComplexTask complexTask, Task task);

    void setDescription(String description);

    void createNormalTask(Calendar c);

    void createComplexTask(Calendar c);

    void createBooleanTask(Calendar c);

    void setProgress(double progress);

    void setIsDone(boolean isDone);

    void setCompletion(int completion);

    void setPriority(Priority priority);
}