package univ.rouen.planifun.app.editor.view.task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import univ.rouen.planifun.app.editor.controller.task.ControlChangeDescription;
import univ.rouen.planifun.app.editor.controller.task.ControlDateCompletion;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

public class DisplayNormalTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JProgressBar progress;
    private JButton changePriority;
    private JSpinner choiceCompletion;

    private NormalTask model;
    private JLabel associate;

    // CONSTRUCTORS

    public DisplayNormalTask(NormalTask model, JLabel label) {
        this.associate = label;
        this.createModel(model);
        this.createComponents();
        this.placeComponents();
        this.createController();
    }
    
    // UTILS

    private void createModel(NormalTask model) {
        this.model = model;
    }

    private void createComponents() {
        this.description = new JTextField();
        this.description.setText(this.model.getDescription());

        this.changePriority = new JButton(this.model.getPriority().name());
        this.createProgressBar();
        this.createSpinner();

    }

    private void createProgressBar() {
        this.progress = new JProgressBar(JProgressBar.HORIZONTAL);
        this.progress.setStringPainted(true);
        this.progress.setMaximum(100);
        this.progress.setMinimum(0);
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
    
        JPanel p = new JPanel();
        p.add(this.changePriority);
        p.add(new JLabel("Description : "));
        p.add(this.description);
        this.add(p, BorderLayout.NORTH);
    
        p = new JPanel(new BorderLayout());
        JPanel q = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        q.add(new JLabel("Complétion : "));
        q.add(this.choiceCompletion);

        p.add(q, BorderLayout.NORTH);

        q = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        q.add(new JLabel("Progression : "));
        q.add(this.progress);
        p.add(q);

        this.add(p);
    }

    private void createController() {
        this.choiceCompletion.addChangeListener(new ControlDateCompletion(this.model));
        this.description.addKeyListener(new ControlChangeDescription(associate, model));
    }
}
