package univ.rouen.planifun.app.editor.model.task.factory;

import univ.rouen.planifun.app.editor.model.task.BooleanTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class FactoryBooleanTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new BooleanTask();
    }
}
