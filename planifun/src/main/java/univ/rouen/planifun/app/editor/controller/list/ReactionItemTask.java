package univ.rouen.planifun.app.editor.controller.list;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ReactionItemTask extends MouseAdapter {

    // CONSTANTS

    private Color ENTERED_COLOR = Color.LIGHT_GRAY;
    private Color EXITED_COLOR = Color.WHITE;

    // ATTRIBUTES

    private JPanel panel;

    // CONSTRUCTORS

    public ReactionItemTask(JPanel p) {
        this.panel = p;
    }

    // COMMANDS

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackground(ENTERED_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(EXITED_COLOR);

    }
}
