package univ.rouen.planifun.app.editor.view.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import univ.rouen.planifun.app.editor.model.task.basic.BooleanTask;

public class DisplayBooleanTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JCheckBox checkTask;
    private JButton changePriority;
    private JSpinner choiceCompletion;

    private BooleanTask model;

    // CONSTRUCTORS

    public DisplayBooleanTask(BooleanTask model) {
        this.setBackground(Color.RED);
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

    private void createModel(BooleanTask model) {
        this.model = model;
    }

    private void createComponents() {
        this.description = new JTextField();
        this.changePriority = new JButton();
        this.checkTask = new JCheckBox();
        this.createSpinner();

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
        innerPanel.add(new JLabel("Validé: "));
        innerPanel.add(this.checkTask);
        centerPanel.add(innerPanel);
        this.add(centerPanel, BorderLayout.CENTER);
    
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(this.changePriority);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createController() {

    }
}
