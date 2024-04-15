package univ.rouen.planifun.app.editor.model.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexTask implements Task {

    // ATTRIBUTES

    private List<Task> subTasks;
    private String description;
    private Date expiryDate;
    private Priority priority;
    private int completionDate;
    private int progressStatus;

    // CONSTRUCTORS

    public ComplexTask() {
        this.subTasks = new ArrayList<>();
        this.description = "";
        this.priority = Priority.NORMAL;
        this.completionDate = 5;
        this.progressStatus = 0;
    }

     // REQUESTS

     public String getDescription() {
        return description;
     }

     public Date getExpiryDate() {
        return expiryDate;
     }
 
     public Priority getPriority() {
        return priority;
     }
 
     public int getCompletionDate() {
        return completionDate;
     }
 
     public int getProgressStatus() {
        return progressStatus;
     }
 
     // COMMANDS
 
     public void setPriority(Priority p) {
        this.priority = p;
     }
 
     public void setExpiryDate(Date d) {
        this.expiryDate = d;
     }
 
     public void setDescription(String s) {
        this.description = s;
     }
 
     public void setCompletionDate(int i) {
        this.completionDate = i;
     }
}
