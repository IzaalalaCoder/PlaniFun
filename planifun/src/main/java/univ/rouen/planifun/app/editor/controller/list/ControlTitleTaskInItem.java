package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.EditorMain;

/**
 * Implements MouseListener to manage display task
 */
public class ControlTitleTaskInItem extends MouseAdapter {
   
    // ATTRIBUTES

    private final Task task;
    private final EditorMain parent;

    // CONSTRUCTORS

    public ControlTitleTaskInItem(EditorMain parent, Task task) {
        this.parent = parent;
        this.task = task;
    }

    // COMMANDS

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        this.parent.setTask(this.task, (JLabel) e.getSource());
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