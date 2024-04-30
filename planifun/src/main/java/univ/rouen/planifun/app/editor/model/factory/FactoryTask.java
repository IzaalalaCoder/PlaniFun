package univ.rouen.planifun.app.editor.model.factory;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Manage create task
 */
public interface FactoryTask {

    // REQUESTS

    /**
     * createTask : create new task
     * @param c : default time
     * @return Task
     */
    Task createTask(Calendar c);
}