package univ.rouen.planifun.app.builder;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.factory.FactoryBooleanTask;
import univ.rouen.planifun.app.editor.model.task.factory.FactoryComplexTask;
import univ.rouen.planifun.app.editor.model.task.factory.FactoryNormalTask;

public class ConcreteBuilderTask implements BuilderTask {


    @Override
    public SetTask createTask() {
        return new SetTask();
    }

    @Override
    public Task createNormalTask() {
        return new FactoryNormalTask().createTask();
    }

    @Override
    public Task creatBooleanTask() {
        return new FactoryBooleanTask().createTask();
    }

    @Override
    public Task createComplexTask() {
        return new FactoryComplexTask().createTask();
    }
    
}
