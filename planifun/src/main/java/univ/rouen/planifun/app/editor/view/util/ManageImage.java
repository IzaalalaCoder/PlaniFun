package univ.rouen.planifun.app.editor.view.util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Manage images
 */
public class ManageImage {

    // STATIC ATTRIBUTES
    
    public static final String PATH_ASSET = "src/main/resources/assets/";
    
    // STATIC REQUESTS

    /**
     * getIcon : transform image and return this
     * @param imageIcon : origin image
     * @return ImageIcon
     */
    public static ImageIcon getIcon(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return null;
        }
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}