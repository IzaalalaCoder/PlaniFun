package univ.rouen.planifun.app.builder;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryBooleanTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryComplexTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryNormalTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ConcreteBuilderTask implements BuilderTask {
    
    // COMMANDS

    @Override
    public SetTask createTask() {
        return new SetTask();
    }

    @Override
    public Task createNormalTask() {
        return new FactoryNormalTask().createTask();
    }

    @Override
    public Task createBooleanTask() {
        return new FactoryBooleanTask().createTask();
    }

    @Override
    public Task createComplexTask() {
        return new FactoryComplexTask().createTask();
    }
    
}
