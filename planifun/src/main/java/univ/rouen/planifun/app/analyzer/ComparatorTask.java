package univ.rouen.planifun.app.analyzer;

import java.util.Comparator;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Implements Comparator interface to compare task on expiry date.
 */
public class ComparatorTask implements Comparator<Task> {

    // REQUESTS

    @Override
    public int compare(Task arg0, Task arg1) {
        return arg0.getExpiryDate().compareTo(arg1.getExpiryDate()) ;
    }
}