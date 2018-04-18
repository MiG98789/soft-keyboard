package soft.helper;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * <h1>Helper</h1>
 * <p>Helper class contains helper function methods.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @since   2.0
 */
public class Helper {
    public static final Robot robot = createRobot();
    
    /**
     * Initialise the static Robot object.
     * @return  the Robot object.
     */
    private static Robot createRobot() {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return r;
    }
    
    /**
     * Convert from keymap to Equation Editor.
     * @param key   the symbol to type.
     */
    public static void typeEquationEditor(String symbol, boolean withBracket) {
        if (withBracket) {
            typeKey(KeyEvent.VK_BACK_SLASH);
        }
        for (char letter : symbol.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
            typeKey(keyCode);
        }
        typeKey(KeyEvent.VK_SPACE);
        if (withBracket) {
            typeBrackets();
        }
    }
    
    /**
     * Type a matching pair of parenthesses, then bring cursor to the middle.
     */
    public static void typeBrackets() {
        Helper.shiftKey(KeyEvent.VK_9); // (
        Helper.shiftKey(KeyEvent.VK_0); // )
        Helper.typeKey(KeyEvent.VK_LEFT); // Go between ( and )
    }
    
    /**
     * Type a key once.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_<CHARACTER> to be typed.
     * @see             java.awt.event.KeyEvent
     */
    public static void typeKey(int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    /**
     * Type a key once that requires the shift key to be pressed.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_<CHARACTER> to be typed.
     * @see             java.awt.event.KeyEvent
     */
    public static void shiftKey(int keyCode) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        typeKey(keyCode);
        robot.keyRelease(keyCode);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
    
    /**
     * Scales an image to the desired width and height.
     * @param srcImg    the source image.
     * @param w         the new width.
     * @param h         the new height.
     * @return          the scaled image.
     */
    public static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    /**
     * Clones a JButton.
     * @param b the button to clone.
     */
    public static JButton cloneJButton(JButton b) {
        JButton result = new JButton(b.getName());
        
        // Get all action listeners
        ActionListener[] actionListeners = b.getActionListeners();
        for (ActionListener l : actionListeners) {
            result.addActionListener(l);
        }
        
        // Get all mouse adapters
        MouseListener[] mouseListeners = b.getMouseListeners();
        for (MouseListener l : mouseListeners) {
            result.addMouseListener(l);
        }
        
        result.setBounds(b.getBounds());
        result.setFont(b.getFont());
        
        return result;
    }
}