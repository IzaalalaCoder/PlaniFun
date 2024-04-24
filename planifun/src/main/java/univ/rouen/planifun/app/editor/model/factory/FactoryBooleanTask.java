package univ.rouen.planifun.app.editor.model.factory;

import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;

public class FactoryBooleanTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new BooleanTask();
    }
}
