package univ.rouen.planifun.app.builder;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public interface BuilderTask {
    public SetTask createTask();

    public Task createNormalTask();

    public Task creatBooleanTask();
    
    public Task createComplexTask();
}
