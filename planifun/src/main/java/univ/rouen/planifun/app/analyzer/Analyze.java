package univ.rouen.planifun.app.analyzer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class Analyze {

    // CONSTANTS 

    private final Double MAX_PROGRESS = 100.0;
    
    // ATTRIBUTES

    private SetTask todo;
    private List<Task> tasks;
    private Calendar calendar;

    // CONSTRUCTORS

    public Analyze(SetTask setTask) {
        this.calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        this.todo = setTask;
        this.tasks = new ArrayList<Task>();
        this.analyze();
    }

    // REQUESTS

    public void getAllTheMostUrgentTask() {
        if (this.tasks.size() == 0) {
            System.out.println("Toutes les tâches sont complétées");
            return;
        }

        int index = 1;
        for (Task t : this.tasks) {
            System.out.println("- Tache numéro : " + index);
            System.out.println(t.toString());
            System.out.println();

            index++;
        }
    }

    // UTILS

    private void analyze() {
        for (Task task : this.todo.getAllTask()) {
            Calendar c = Calendar.getInstance();
            c.setTime(task.getExpiryDate());
            if (task.getProgressStatus() != MAX_PROGRESS && c.before(this.calendar)) {
                this.tasks.add(task);
            }
        }

        this.sortByDate();
        this.cleanListTask();
    }

    private void sortByDate() {
        Collections.sort(this.tasks, new ComparatorTask());
    }

    private void cleanListTask() {
        if (this.tasks.size() <= 5) {
            return;
        }

        while (this.tasks.size() != 5) {
            this.tasks.remove(this.tasks.size() - 1);
        }
    }
}
