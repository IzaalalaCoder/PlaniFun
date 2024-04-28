package univ.rouen.planifun.app.editor.model.factory;

import java.util.Calendar;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;

public class FactoryBooleanTask implements FactoryTask {

    // REQUESTS

    @Override
    public Task createTask(Calendar c) {
        return new BooleanTask(c);
    }
}
