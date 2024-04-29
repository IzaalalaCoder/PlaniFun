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
    private List<Task> late;
    private Calendar today;
    private boolean allCompleted;
    private int number;

    // CONSTRUCTORS

    public Analyze(SetTask setTask) { 
        this.todo = setTask;
        this.today = new GregorianCalendar();
        this.tasks = new ArrayList<Task>();
        this.late = new ArrayList<Task>();
        this.allCompleted = false;
        this.number = 5;
    }

    public Analyze(SetTask setTask, int number) { 
        this.todo = setTask;
        this.today = new GregorianCalendar();
        this.tasks = new ArrayList<Task>();
        this.late = new ArrayList<Task>();
        this.allCompleted = false;
        this.number = number;
    }

    // REQUESTS

    public void getAllTheMostUrgentTask() {
        if (this.todo == null) {
            System.out.println("Aucune tâche n'a été trouvé");
            return;
        }
        
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);

        if (this.tasks.size() == 0) {
            if (allCompleted) {
                System.out.println("Toutes les tâches sont complétées");
            } else if (this.late.size() > 0) {
                System.out.println("Les tâches en retard ");
                System.out.println("==> En retard depuis le " +  dateFormat.format(this.todo.getCreationDate().getTime()));
                for (Task t : this.late) {
                    System.out.println("---------- Tache numéro : " + (this.late.indexOf(t) + 1));
                    System.out.println("==> En retard depuis le " +  dateFormat.format(t.getExpiryDate().getTime()));
                    System.out.println(t.toString());
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

    public void analyze() {
        if (this.todo == null) {
            return;
        }

        int countCompleted = 0;
        for (Task task : this.todo.getAllTask()) {
            if (task.getExpiryDate() != null) {
                Calendar c = new GregorianCalendar();
                c.setTime(task.getExpiryDate());
                if (task.getProgressStatus() != MAX_PROGRESS) {
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

    private void sortByDate() {
        Collections.sort(this.tasks, new ComparatorTask());
        Collections.sort(this.late, new ComparatorTask());
    }

    private void cleanListTask() {
        if (this.tasks.size() <= this.number) {
            return;
        }

        while (this.tasks.size() != this.number) {
            this.tasks.remove(this.tasks.size() - 1);
        }
    }
}
