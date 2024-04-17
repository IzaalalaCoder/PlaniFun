package univ.rouen.planifun.app.editor.model.task.factory;

import univ.rouen.planifun.app.editor.model.task.NormalTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class FactoryNormalTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new NormalTask();
    }
}
