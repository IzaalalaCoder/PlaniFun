package univ.rouen.planifun.app.editor.model.factory;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.task.Task;

public interface FactoryTask {

    // REQUESTS

    public Task createTask(Calendar c);
}
