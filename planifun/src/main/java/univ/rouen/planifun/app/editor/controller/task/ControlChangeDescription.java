package univ.rouen.planifun.app.editor.controller.task;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import univ.rouen.planifun.app.editor.model.task.Task;

public class ControlChangeDescription implements KeyListener {

    // ATTRIBUTES

    private Task model;
    private JLabel label;

    // CONSTRUCTORS

    public ControlChangeDescription(JLabel label, Task model) {
        this.model = model;
        this.label = label;
    }

    // COMMANDS
    
    @Override
    public void keyTyped(KeyEvent e) {
        JTextField field = (JTextField) e.getSource();
        this.model.setDescription(field.getText());
        this.label.setText(field.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField field = (JTextField) e.getSource();
        this.model.setDescription(field.getText());
        this.label.setText(field.getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField field = (JTextField) e.getSource();
        this.model.setDescription(field.getText());
        this.label.setText(field.getText());
    }

    

    
}
