package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.util.Calendar;
import javax.swing.JLabel;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.list.task.DisplayComplexTask;

public class ControlTitleSubTaskInItem extends MouseAdapter {
   
    // ATTRIBUTES

    private Task task;
    private Calendar calendar;
    private DisplayComplexTask parent;

    // CONSTRUCTORS

    public ControlTitleSubTaskInItem(DisplayComplexTask parent, Task task, Calendar c) {
        this.parent = parent;
        this.task = task;
        this.calendar = c;
    }

    // COMMANDS

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        this.parent.setSubTask(this.task, (JLabel) e.getSource(), calendar);
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        src.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        src.setCursor(Cursor.getDefaultCursor());
    }

}
