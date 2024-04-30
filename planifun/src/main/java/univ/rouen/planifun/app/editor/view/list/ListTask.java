package univ.rouen.planifun.app.editor.view.list;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import univ.rouen.planifun.app.editor.controller.list.ControlRemoveTask;
import univ.rouen.planifun.app.editor.controller.list.ControlTitleTaskInItem;
import univ.rouen.planifun.app.editor.model.SetTask;
import univ.rouen.planifun.app.editor.model.task.Task;
import univ.rouen.planifun.app.editor.view.EditorMain;
import univ.rouen.planifun.app.editor.view.util.ManageImage;

/**
 * Extends AbstractListTask for display tasks of todolist
 */
public class ListTask extends AbstractListTask {

    // ATTRIBUTES

    private SetTask model;
    private final EditorMain parent;

    // CONSTRUCTORS

    public ListTask(EditorMain parent) {
        this.parent = parent;
        this.panels = new HashMap<>();
        this.removeButton = new HashMap<>();
        this.subPanel = new HashMap<>();
        this.descTask = new HashMap<>();
        this.model = null;
        this.initializeComponent();
    }

    // COMMANDS

    /**
     * setModel : change model for display this list task
     * @param model : model
     */
    public void setModel(SetTask model) {
        this.setBackground(COLOR_NOT_NULL);
        if (this.model != null) {
            this.removeAllControllers();
            this.removeAllComponents();
        }
        
        this.model = model;

        this.createController();
        this.createComponents();
    }

    // REQUESTS

    @Override
    protected JLabel createLabelForDescription(Task task) {
        JLabel description = new JLabel(task.getDescription());
        description.setOpaque(false);
        this.descTask.put(task, description);

        description.addMouseListener(new ControlTitleTaskInItem(parent, task));
        return description;
    }

    @Override
    protected JButton createRemoveButton(Task t) {
        JButton remove = new JButton(ManageImage.getIcon(
                new ImageIcon(ManageImage.PATH_ASSET + "remove_task.png"))
        );
    
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        remove.addActionListener(new ControlRemoveTask(this.model, t));
        remove.addMouseListener(new ControlRemoveTask(remove));
        return remove;
    }

    @Override
    protected int calculateTotalHeight() {
        return this.model.getAllTask().size() * 60;
    }

    // UTILS

    /**
     * createController : create controller on model
     */
    private void createController() {
        this.model.addPropertyChangeListener(SetTask.PROP_ADD_TASKS, evt -> updateListAboutTask(true,
                (Task) evt.getNewValue()
        ));

        this.model.addPropertyChangeListener(SetTask.PROP_REMOVE_TASKS, evt -> updateListAboutTask(false,
                (Task) evt.getNewValue()
        ));
    }

    /**
     * removeAllComponents : remove all sub components
     */
    private void removeAllComponents() {
        Set<Task> taskSet = new HashSet<>(this.panels.keySet());
        super.removeAllComponents(taskSet);
    }

    /**
     * refresh : refresh interface
     */
    private void refresh() {
        for (Task t : this.model.getAllTask()) {
            this.changeBackgroundColorAboutTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }

    /**
     * createComponents : create all sub components
     */
    private void createComponents() {
        for (Task t : this.model.getAllTask()) {
            this.addPanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.refresh();
        this.revalidate();
        this.repaint();
    }

    /**
     * removeAllControllers : remove all controller on this model
     */
    private void removeAllControllers() {
        for (PropertyChangeListener pcl : this.model.getPropertyChangeListeners(SetTask.PROP_ADD_TASKS)) {
            this.model.removePropertyChangeListener(SetTask.PROP_ADD_TASKS, pcl);
        }

        for (PropertyChangeListener pcl : this.model.getPropertyChangeListeners(SetTask.PROP_REMOVE_TASKS)) {
            this.model.removePropertyChangeListener(SetTask.PROP_REMOVE_TASKS, pcl);
        }
    }
}