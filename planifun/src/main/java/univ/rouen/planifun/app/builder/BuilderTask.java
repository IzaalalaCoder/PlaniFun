package univ.rouen.planifun.app.builder;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Is a todolist and task constructor.
 */
public interface BuilderTask {

    // REQUESTS

    /**
     * createSetTask : create todolist
     * @return SetTask
     */
    SetTask createSetTask();

    /**
     * createNormalTask : create normal task
     * @param c : default time
     * @return Task
     */
    Task createNormalTask(Calendar c);

    /**
     * createBooleanTask : create boolean task
     * @param c : default time
     * @return Task
     */
    Task createBooleanTask(Calendar c);

    /**
     * createComplexTask : create complex task
     * @param c : default time
     * @return Task
     */
    Task createComplexTask(Calendar c);
}