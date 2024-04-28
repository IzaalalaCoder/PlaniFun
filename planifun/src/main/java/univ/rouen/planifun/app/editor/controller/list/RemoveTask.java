package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;

public class RemoveTask extends MouseAdapter implements ActionListener {

    // ATTRIBUTES

    private SetTask setTask;
    private Task task;
    private JButton button;

    // CONSTRUCTORS

    public RemoveTask(SetTask setTask, Task task) {
        this.setTask = setTask;
        this.task = task;
    }

    public RemoveTask(JButton btn) {
        this.button = btn;
    }

    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setTask.removeTaskInList(task);
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
