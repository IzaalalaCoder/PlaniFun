package univ.rouen.planifun.app.editor.view.list.task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import univ.rouen.planifun.app.editor.controller.list.ControlCreateSubTask;
import univ.rouen.planifun.app.editor.controller.task.ControlChangeDescription;
import univ.rouen.planifun.app.editor.controller.task.ControlChoicePriority;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.list.ListSubTask;

public class DisplayComplexTask extends JPanel {
    
    // ATTRIBUTES

    private JTextField description;
    private JProgressBar progress;
    private JButton changePriority;
    private JLabel dateCompletion;
    private JButton addTask;
    private JSplitPane mainSplit;
    private JSplitPane taskSplit;
    private ComplexTask model;
    private JLabel associate;
    private InformationTask informationTask;
    private ListSubTask listSubTask;
    private Calendar calendar;

    // CONSTRUCTORS

    public DisplayComplexTask(EditorMain main, ComplexTask model, JLabel label, Calendar defaultDate) {
        this.associate = label;
        this.calendar = defaultDate;
        this.createModel(model);
        this.createComponents(main);
        this.placeComponents();
        this.createController();
    }

    // REQUESTS

    public ComplexTask getModel() {
        return this.model;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    // COMMANDS 

    public void setSubTask(Task task, JLabel label, Calendar c) {
        this.listSubTask.changeBackgroundColorAboutTask(task);
        this.informationTask.setModel(task, label, c);
    }
    
    // UTILS

    private void createModel(ComplexTask model) {
        this.model = model;
    }

    private void createComponents(EditorMain main) {
        this.informationTask = new InformationTask(main);
        this.listSubTask = new ListSubTask(this, this.calendar);
        this.description = new JTextField(13);
        this.description.setText(this.model.getDescription());
        this.changePriority = new JButton(this.model.getPriority().name());
        this.createProgressBarForTaskCompletion();
        this.dateCompletion = new JLabel();
        this.dateCompletion.setText(Integer.toString(this.model.getCompletionDate()));
        this.addTask = new JButton("Ajouter une tâche");
    }

    private void createProgressBarForTaskCompletion() {
        this.progress = new JProgressBar();
        this.progress.setMinimum(0);
        this.progress.setMaximum(100);
        this.progress.setStringPainted(true);
        this.progress.setValue(this.model.getProgressStatus().intValue());
    }

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
        q.add(this.dateCompletion);
        p.add(q, BorderLayout.NORTH);

        q = new JPanel(new FlowLayout(FlowLayout.LEFT));
        q.add(new JLabel("Progression : "));
        q.add(this.progress);

        p.add(q);
        p.add(this.addTask, BorderLayout.SOUTH);


        this.taskSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.listSubTask, this.informationTask);
        this.mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p, this.taskSplit );
        this.add(mainSplit);
    }

    private void createController() {
        this.addTask.addActionListener(new ControlCreateSubTask(this));
        this.description.addKeyListener(new ControlChangeDescription(associate, model));
        this.changePriority.addActionListener(new ControlChoicePriority(model));
    }
}
