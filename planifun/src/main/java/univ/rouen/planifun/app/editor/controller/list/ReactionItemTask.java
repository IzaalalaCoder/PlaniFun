package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReactionItemTask implements MouseListener {

    // CONSTANTS

    private Color ENTERED_COLOR = Color.LIGHT_GRAY;
    private Color EXITED_COLOR = Color.WHITE;

    // ATTRIBUTES

    private JPanel panel;

    // CONSTRUCTORS

    public ReactionItemTask(JPanel p) {
        this.panel = p;
    }

    // REQUESTS
    // COMMANDS

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackground(ENTERED_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(EXITED_COLOR);

    }
    
    // UTILS
}
