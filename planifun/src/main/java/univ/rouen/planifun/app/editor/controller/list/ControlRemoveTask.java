package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.popup.WarningPopUp;

/**
 * Implements ActionListener and MouseListener and manage remove task
 */
public class ControlRemoveTask extends MouseAdapter implements ActionListener {

    // ATTRIBUTES

    private SetTask setTask;
    private Task task;
    private JButton button;

    // CONSTRUCTORS

    public ControlRemoveTask(SetTask setTask, Task task) {
        this.setTask = setTask;
        this.task = task;
    }

    public ControlRemoveTask(JButton btn) {
        this.button = btn;
    }

    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int result = WarningPopUp.preventRemoveTask(this.task.getDescription());
        if (result == JOptionPane.OK_OPTION) {
            this.setTask.removeTaskInList(task);
        }
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