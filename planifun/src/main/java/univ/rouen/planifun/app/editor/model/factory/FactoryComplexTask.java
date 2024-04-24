package univ.rouen.planifun.app.editor.model.factory;

import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

public class FactoryComplexTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new ComplexTask();
    }
}
