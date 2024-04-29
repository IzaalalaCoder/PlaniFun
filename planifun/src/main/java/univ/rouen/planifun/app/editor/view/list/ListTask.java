package univ.rouen.planifun.app.editor.view.list;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
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

public class ListTask extends AbstractListTask {

    // ATTRIBUTES

    private SetTask model;
    private EditorMain parent;

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
        ImageIcon img = new ImageIcon(PATH_ASSET + "remove_task.png");
        Image image = img.getImage();
        Image transform = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton remove = new JButton(new ImageIcon(transform));
    
        remove.setContentAreaFilled(false);
        remove.setBorder(null);
        remove.addActionListener(new ControlRemoveTask(this.model, t));
        remove.addMouseListener(new ControlRemoveTask(remove));

        return remove;
    }

    public void refresh() {
        for (Task t : this.model.getAllTask()) {
            this.changeBackgroundColorAboutProgress(this.panels.get(t), t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.revalidate();
        this.repaint();
    }
    
    public void removeAllControllers() {
        this.model.removePropertyChangeListener(SetTask.PROP_ADD_TASKS, null);
        this.model.removePropertyChangeListener(SetTask.PROP_REMOVE_TASKS, null);
    }

    @Override
    protected int calculateTotalHeight() {
        int totalHeight = this.model.getAllTask().size() * 60;
        return totalHeight;
    }

    // UTILS

    private void createController() {
        this.model.addPropertyChangeListener(SetTask.PROP_ADD_TASKS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(true, (Task) evt.getNewValue());
            }
        
        });

        this.model.addPropertyChangeListener(SetTask.PROP_REMOVE_TASKS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateListAboutTask(false, (Task) evt.getNewValue());
            }
        
        });
    } 
    
    private void removeAllComponents() {
        Set<Task> taskSet = new HashSet<>(this.panels.keySet());
        super.removeAllComponents(taskSet);
    }

    private void createComponents() {
        for (Task t : this.model.getAllTask()) {
            this.addPanelForTask(t);
        }
        setListTaskPreferredSize(calculateTotalHeight());
        this.refresh();
        this.revalidate();
        this.repaint();
    }
}
