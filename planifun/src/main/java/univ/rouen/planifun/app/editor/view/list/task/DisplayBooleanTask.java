package univ.rouen.planifun.app.editor.view.list.task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import univ.rouen.planifun.app.editor.controller.task.ControlBooleanProgress;
import univ.rouen.planifun.app.editor.controller.task.ControlChangeDescription;
import univ.rouen.planifun.app.editor.controller.task.ControlChoicePriority;
import univ.rouen.planifun.app.editor.controller.task.ControlDateCompletion;
import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;
import univ.rouen.planifun.app.editor.view.EditorMain;

public class DisplayBooleanTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JCheckBox checkTask;
    private JButton changePriority;
    private JSpinner choiceCompletion;

    private BooleanTask model;
    private JLabel associate;

    // CONSTRUCTORS

    public DisplayBooleanTask(EditorMain main, BooleanTask model, JLabel label) {
        this.associate = label;
        this.createModel(model);
        this.createComponents();
        this.placeComponents();
        this.createController(main);
    }

    // UTILS

    private void createModel(BooleanTask model) {
        this.model = model;
    }

    private void createComponents() {
        this.description = new JTextField(13);
        this.description.setText(this.model.getDescription());
        this.changePriority = new JButton();
        this.changePriority.setText(this.model.getPriority().name());
        this.checkTask = new JCheckBox();
        this.checkTask.setSelected(this.model.getProgressStatus() == 100.0);
        this.createSpinner();

    }

    private void createSpinner() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(this.model.getCompletionDate(), 
            0, 
            Integer.MAX_VALUE, 
            1
        );

        this.choiceCompletion = new JSpinner(spinnerModel);
        this.choiceCompletion.setBounds(100, 100, 50, 30);
    }

    private void placeComponents() {
        this.setLayout(new BorderLayout());
    
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(this.changePriority);
        p.add(new JLabel("Description: "));
        p.add(this.description);
        this.add(p, BorderLayout.NORTH);
    
        p = new JPanel(new BorderLayout());
        JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT));
        q.add(new JLabel("Nombre de jours estimés : "));
        q.add(this.choiceCompletion);

        p.add(q, BorderLayout.NORTH);

        q = new JPanel(new FlowLayout(FlowLayout.LEFT));
        q.add(new JLabel("Validé: "));
        q.add(this.checkTask);
        p.add(q);

        this.add(p);
    }

    private void createController(EditorMain main) {
        this.checkTask.addActionListener(new ControlBooleanProgress(this.model));
        this.choiceCompletion.addChangeListener(new ControlDateCompletion(this.model));
        this.description.addKeyListener(new ControlChangeDescription(associate, model));
        this.changePriority.addActionListener(new ControlChoicePriority(model));
    }
}
