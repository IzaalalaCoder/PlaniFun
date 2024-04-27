package univ.rouen.planifun.app.editor.view.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import univ.rouen.planifun.app.editor.model.task.basic.NormalTask;

public class DisplayNormalTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JProgressBar progress;
    private JButton changePriority;
    private JSpinner choiceCompletion;

    private NormalTask model;

    // CONSTRUCTORS

    public DisplayNormalTask(NormalTask model) {
        this.setBackground(Color.GREEN);
        this.initializePanel();
        this.createModel(model);
        this.createComponents();
        this.placeComponents();
        this.createController();
    }
    // REQUESTS
    // COMMANDS
    // UTILS

    private void initializePanel() {
    }

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
        this.progress.setMaximum(100);
        this.progress.setMinimum(0);
    }

    private void createSpinner() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(this.model.getCompletionDate(), 
            5, 
            Integer.MAX_VALUE, 
            1
        );

        this.choiceCompletion = new JSpinner(spinnerModel);
        this.choiceCompletion.setBounds(100, 100, 50, 30);
    }

    private void placeComponents() {
        this.setLayout(new BorderLayout());
    
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Description: "));
        topPanel.add(this.description);
        this.add(topPanel, BorderLayout.NORTH);
    
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        JPanel innerPanel = new JPanel(new GridLayout(1, 2));
        innerPanel.add(new JLabel("Complétion: "));
        innerPanel.add(this.choiceCompletion);
        centerPanel.add(innerPanel);
        innerPanel = new JPanel(new GridLayout(1, 2));
        innerPanel.add(new JLabel("Progression : "));
        innerPanel.add(this.progress);
        centerPanel.add(innerPanel);
        this.add(centerPanel, BorderLayout.CENTER);
    
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(this.changePriority);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    private void createController() {

    }
}
