package univ.rouen.planifun.app.editor.model.task;

public class FactoryBooleanTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new BooleanTask();
    }
}
