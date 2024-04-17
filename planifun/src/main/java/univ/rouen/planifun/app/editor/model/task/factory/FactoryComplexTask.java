package univ.rouen.planifun.app.editor.model.task.factory;

import univ.rouen.planifun.app.editor.model.task.ComplexTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class FactoryComplexTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new ComplexTask();
    }
}
