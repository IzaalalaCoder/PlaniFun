package univ.rouen.planifun.app.editor.view;

import java.awt.Color;
import javax.swing.JPanel;
import univ.rouen.planifun.app.editor.model.task.Task;

public class InformationTask extends JPanel {
    public InformationTask() {
        this.model = null;
        this.setBackground(Color.darkGray);
    }

    private Task model;

    public void unsetModel() {
        //this.removeAllControllers();
        //this.removeAllComponents();
        this.removeAllController();
        this.model = null;
        this.setBackground(Color.white);
        //this.createComponents();
    }

    public void removeAllController() {

    }
}
