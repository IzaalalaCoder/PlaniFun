package univ.rouen.planifun.app.builder;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public interface BuilderTask {

    // REQUESTS

    public SetTask createTask();

    public Task createNormalTask(Calendar c);

    public Task createBooleanTask(Calendar c);
    
    public Task createComplexTask();
}
