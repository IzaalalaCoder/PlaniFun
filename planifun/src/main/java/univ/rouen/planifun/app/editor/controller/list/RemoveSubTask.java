package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;

public class RemoveSubTask extends MouseAdapter implements ActionListener {

    // ATTRIBUTES

    private ComplexTask complexTask;
    private Task task;
    private JButton button;

    // CONSTRUCTORS

    public RemoveSubTask(ComplexTask setTask, Task task) {
        this.complexTask = setTask;
        this.task = task;
    }

    public RemoveSubTask(JButton btn) {
        this.button = btn;
    }

    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.complexTask.removeTask(task);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.button.setCursor(Cursor.getDefaultCursor());
    }

}
