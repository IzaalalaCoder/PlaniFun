package univ.rouen.planifun.app.editor.model.factory;

import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

public class FactoryNormalTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new NormalTask();
    }
}
