package univ.rouen.planifun.app.analyzer;

import java.text.DateFormat;
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
    private boolean allCompleted;

    // CONSTRUCTORS

    public Analyze(SetTask setTask) { 
        this.todo = setTask;
        this.calendar = new GregorianCalendar();
        this.tasks = new ArrayList<Task>();
        this.allCompleted = false;
    }

    // REQUESTS

    public void getAllTheMostUrgentTask() {
        if (this.todo == null) {
            System.out.println("Aucune tâche n'a été trouvé");
            return;
        }

        if (this.tasks.size() == 0) {
            if (allCompleted) {
                System.out.println("Toutes les tâches sont complétées");
            } else {
                System.out.println("Certaines tâches sont en retard");
            }
            return;
        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        String date = dateFormat.format(this.calendar.getTime());
        System.out.println("Pour " + date);

        int index = 1;
        for (Task t : this.tasks) {
            System.out.println("- Tache numéro : " + index);
            System.out.println(t.toString());
            System.out.println();

            index++;
        }
    }

    // COMMANDS

    public void analyze() {
        if (this.todo == null) {
            return;
        }

        int countCompleted = 0;
        for (Task task : this.todo.getAllTask()) {
            Calendar c = new GregorianCalendar();
            c.setTime(task.getExpiryDate());
            if (task.getProgressStatus() != MAX_PROGRESS) {
                if (c.after(this.calendar)) {
                    this.tasks.add(task);
                }
            } else {
                countCompleted++;
            }
        }

        this.allCompleted = countCompleted == this.todo.getAllTask().size();
        this.sortByDate();
        this.cleanListTask();
    }
    
    // UTILS

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
