package univ.rouen.planifun.app.editor.view.list;

import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import univ.rouen.planifun.app.editor.controller.list.ControlRemoveSubTask;
import univ.rouen.planifun.app.editor.controller.list.ControlTitleSubTaskInItem;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.model.task.complex.ComplexTask;
import univ.rouen.planifun.app.editor.view.list.task.DisplayComplexTask;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Extends AbstractListTask for display subtasks of complex task
 */
public class ListSubTask extends AbstractListTask {

    // ATTRIBUTES

    private ComplexTask model;
    private final DisplayComplexTask parent;
    private final Calendar calendar;

    // CONSTRUCTORS

    public ListSubTask(DisplayComplexTask parent, Calendar c) {
        this.parent = parent;
        this.calendar = c;
        this.panels = new HashMap<>();
        this.removeButton = new HashMap<>();
        this.subPanel = new HashMap<>();
        this.descTask = new HashMap<>();
        this.setModel(parent.getModel());
        this.initializeComponent();
    }

    // REQUESTS

    @Override
    protected int calculateTotalHeight() {
        return this.model.getAllSubTasks().size() * 60;
    }

    @Override
    protected JLabel createLabelForDescription(Task task) {
        JLabel description = new JLabel(task.getDescription());
        description.setOpaque(false);
        this.descTask.put(task, description);

        description.addMouseListener(new ControlTitleSubTaskInItem(parent, task, this.calendar));
        return description;
    }

    @Override
    protected JButton createRemoveButton(Task t) {
        JButton remove = new JButton(ManageImage.getIcon(
                new ImageIcon(ManageImage.PATH_ASSET + "remove_task.png"))
        );

        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        remove.addActionListener(new ControlRemoveSubTask(this.model, t));
        remove.addMouseListener(new ControlRemoveSubTask(remove));

        return remove;
    }

    // UTILS

    /**
     * setModel : change model for display this list subtask
     * @param model : model
     */
    private void setModel(ComplexTask model) {
        if (this.model != null) {
            this.removeAllControllers();
            this.removeAllComponents();
        }
        
        this.model = model;

        this.createController();
        this.createComponents();
    }

    /**
     * createController : create controller on model
     */
    private void createController() {
        this.model.addPropertyChangeListener(ComplexTask.PROP_ADD_SUB_TASKS,
                evt -> updateListAboutTask(true, (Task) evt.getNewValue()));

        this.model.addPropertyChangeListener(ComplexTask.PROP_REMOVE_SUB_TASKS,
                evt -> updateListAboutTask(false, (Task) evt.getNewValue()));
    }

    /**
     * refresh : refresh interface
     */
    private void refresh() {
        for (Task t : this.model.getAllSubTasks()) {
            this.changeBackgroundColorAboutTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }

    /**
     * removeAllControllers : remove all controller on model
     */
    private void removeAllControllers() {
        for (PropertyChangeListener pcl : this.model.getPropertyChangeListeners(ComplexTask.PROP_ADD_SUB_TASKS)) {
            this.model.removePropertyChangeListener(ComplexTask.PROP_ADD_SUB_TASKS, pcl);
        }

        for (PropertyChangeListener pcl : this.model.getPropertyChangeListeners(ComplexTask.PROP_REMOVE_SUB_TASKS)) {
            this.model.removePropertyChangeListener(ComplexTask.PROP_REMOVE_SUB_TASKS, pcl);
        }
    }

    private void removeAllComponents() {
        Set<Task> taskSet = new HashSet<>(this.panels.keySet());
        super.removeAllComponents(taskSet);
    }

    private void createComponents() {
        for (Task t : this.model.getAllSubTasks()) {
            this.addPanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.refresh();
        this.revalidate();
        this.repaint();
    }
}