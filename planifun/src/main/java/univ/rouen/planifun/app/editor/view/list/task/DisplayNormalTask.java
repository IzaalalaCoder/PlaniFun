package univ.rouen.planifun.app.editor.view.list.task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import univ.rouen.planifun.app.editor.controller.task.ControlChangeDateCompletion;
import univ.rouen.planifun.app.editor.controller.task.ControlChangeDescription;
import univ.rouen.planifun.app.editor.controller.task.ControlChoicePriority;
import univ.rouen.planifun.app.editor.controller.task.ControlNormalProgress;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

/**
 * Manage display normal task
 */
public class DisplayNormalTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JSpinner progress;
    private JButton changePriority;
    private JSpinner choiceCompletion;
    private NormalTask model;
    private final JLabel associate;

    // CONSTRUCTORS

    public DisplayNormalTask(NormalTask model, JLabel label) {
        this.associate = label;
        this.createModel(model);
        this.createComponents();
        this.placeComponents();
        this.createController();
    }
    
    // UTILS

    /**
     * createModel : change model
     * @param model : normal task
     */
    private void createModel(NormalTask model) {
        this.model = model;
    }

    /**
     * createComponents : create all components
     */
    private void createComponents() {
        this.description = new JTextField(13);
        this.description.setText(this.model.getDescription());
        this.changePriority = new JButton(this.model.getPriority().name());

        this.createSpinnerForDateCompletion();
        this.createSpinnerForProgressStatus();
    }

    /**
     * createSpinnerForProgressStatus : create components spinner for set progress status in interface
     */
    private void createSpinnerForProgressStatus() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(
            this.model.getProgressStatus().doubleValue(), 
            0.0, 
            100.0, 
            1
        );

        this.progress = new JSpinner(spinnerModel);
        this.progress.setBounds(100, 100, 50, 30);
    }

    /**
     * createSpinnerForDateCompletion : create components spinner for choice completion in interface
     */
    private void createSpinnerForDateCompletion() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(this.model.getCompletionDate(), 
            0, 
            Integer.MAX_VALUE, 
            1
        );

        this.choiceCompletion = new JSpinner(spinnerModel);
        this.choiceCompletion.setBounds(100, 100, 50, 30);
    }

    /**
     * placeComponents : place all components in interface
     */
    private void placeComponents() {
        this.setLayout(new BorderLayout());
    
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(this.changePriority);
        p.add(new JLabel("Description : "));
        p.add(this.description);
        this.add(p, BorderLayout.NORTH);
    
        p = new JPanel(new BorderLayout());
        JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT));
        q.add(new JLabel("Nombre de jours estimés : "));
        q.add(this.choiceCompletion);

        p.add(q, BorderLayout.NORTH);

        q = new JPanel(new FlowLayout(FlowLayout.LEFT));
        q.add(new JLabel("Progression : "));
        q.add(this.progress);
        p.add(q);

        this.add(p);
    }

    /**
     * createController : create all controller
     */
    private void createController() {
        this.choiceCompletion.addChangeListener(new ControlChangeDateCompletion(this.model));
        this.progress.addChangeListener(new ControlNormalProgress(this.model));
        this.description.addKeyListener(new ControlChangeDescription(associate, (Task) model));
        this.changePriority.addActionListener(new ControlChoicePriority((Task) model));
    }
}
