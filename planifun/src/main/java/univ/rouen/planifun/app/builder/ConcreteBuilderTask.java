package univ.rouen.planifun.app.builder;

import java.util.Calendar;

import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryBooleanTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryComplexTask;
import univ.rouen.planifun.app.editor.model.factory.FactoryNormalTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ConcreteBuilderTask implements BuilderTask {
    
    // REQUESTS

    @Override
    public SetTask createTask() {
        return new SetTask();
    }

    @Override
    public Task createNormalTask(Calendar c) {
        return new FactoryNormalTask().createTask(c);
    }

    @Override
    public Task createBooleanTask(Calendar c) {
        return new FactoryBooleanTask().createTask(c);
    }

    @Override
    public Task createComplexTask(Calendar c) {
        return new FactoryComplexTask().createTask(c);
    }
    
}
