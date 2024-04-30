package univ.rouen.planifun.app.analyzer;

import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

/**
 * Analyze can analyze todolist
 */
public class Analyze {

    // ATTRIBUTES

    private final SetTask todo;
    private final List<Task> tasks;
    private final List<Task> late;
    private final Calendar today;
    private boolean allCompleted;
    private final int number;

    // CONSTRUCTORS

    public Analyze(SetTask setTask) { 
        this.todo = setTask;
        this.today = new GregorianCalendar();
        this.tasks = new ArrayList<>();
        this.late = new ArrayList<>();
        this.allCompleted = false;
        this.number = 5;
    }

    public Analyze(SetTask setTask, int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }
        this.todo = setTask;
        this.today = new GregorianCalendar();
        this.tasks = new ArrayList<>();
        this.late = new ArrayList<>();
        this.allCompleted = false;
        this.number = number;
    }

    // REQUESTS

    /**
     * getAllTheMostUrgentTask : display number most urgent task in console.
     */
    public void getAllTheMostUrgentTask() {
        if (this.todo == null) {
            System.out.println("Aucune tâche n'a été trouvé");
            return;
        }
        
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        if (this.tasks.isEmpty()) {
            if (allCompleted) {
                System.out.println("Toutes les tâches sont complétées");
            } else if (!this.late.isEmpty()) {
                System.out.println("Les tâches en retard ");
                System.out.println("==> En retard depuis le " +  dateFormat.format(
                        this.todo.getCreationDate().getTime()));
                for (Task t : this.late) {
                    System.out.println("---------- Tache numéro : " + (this.late.indexOf(t) + 1));
                    System.out.println("==> En retard depuis le " +  dateFormat.format(t.getExpiryDate().getTime()));
                    System.out.println(t);
                    System.out.println();
                }
            }
            return;
        }
        String date = dateFormat.format(this.today.getTime());
        System.out.println("Le " + date);

        int index = 1;
        for (Task t : this.tasks) {
            System.out.println("- Tache numéro : " + index);
            System.out.println(t.toString());
            System.out.println();
            index++;
        }
    }

    // COMMANDS

    /**
     * analyze : analyze all tasks in todolist.
     */
    public void analyze() {
        if (this.todo == null) {
            return;
        }

        int countCompleted = 0;
        for (Task task : this.todo.getAllTask()) {
            if (task.getExpiryDate() != null) {
                Calendar c = new GregorianCalendar();
                c.setTime(task.getExpiryDate());
                final Double MAX_PROGRESS = 100.0;
                if (!Objects.equals(task.getProgressStatus(), MAX_PROGRESS)) {
                    if (c.after(this.today)) {
                        this.tasks.add(task);
                    } else {
                        this.late.add(task);
                    }
                } else {
                    countCompleted++;
                }
            }
        }

        this.allCompleted = countCompleted == this.todo.getAllTask().size();
        this.sortByDate();
        this.cleanListTask();
    }
    
    // UTILS

    /**
     * sortByDate : sort task list by date.
     */
    private void sortByDate() {
        this.tasks.sort(new ComparatorTask());
        this.late.sort(new ComparatorTask());
    }

    /**
     * cleanListTask : cleans task list by removing excess tasks until the specified number.
     */
    private void cleanListTask() {
        if (this.tasks.size() <= this.number) {
            return;
        }

        while (this.tasks.size() != this.number) {
            this.tasks.removeLast();
        }
    }
}