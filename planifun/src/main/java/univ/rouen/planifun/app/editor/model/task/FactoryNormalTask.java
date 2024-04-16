package univ.rouen.planifun.app.editor.model.task;

public class FactoryNormalTask implements FactoryTask {

    @Override
    public Task createTask() {
        return new NormalTask();
    }
}
