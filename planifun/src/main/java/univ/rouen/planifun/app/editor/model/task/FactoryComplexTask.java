package univ.rouen.planifun.app.editor.model.task;

public class FactoryComplexTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new ComplexTask();
    }
}
