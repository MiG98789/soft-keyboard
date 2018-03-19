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
    /**
     * Keymap to Unicode
     */
    
    /**
     * Keymap to Equation Editor
     */
    
    /**
     * Unescapes string loaded from file.
     * @param s string to unescape.
     */
    public static String unicodeUnescape(String s) {
        s = s.split(" ")[0].replace("\\","");
        String[] arr = s.split("u");
        String result = "";
        for(int i = 1; i < arr.length; i++){
            int hexVal = Integer.parseInt(arr[i], 16);
            result += (char)hexVal;
        }
        return result;
    }
    
    /**
     * Type a key once.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_<CHARACTER> to be typed.
     * @see             java.awt.event.KeyEvent
     */
    public static void typeKey(int keyCode) {
        try {
            Robot robot = new Robot();
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Type a key once that requires the shift key to be pressed.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_<CHARACTER> to be typed.
     * @see             java.awt.event.KeyEvent
     */
    public static void shiftKey(int keyCode) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SHIFT);
            typeKey(keyCode);
            robot.keyRelease(keyCode);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (AWTException e) {
            e.printStackTrace();
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
     * Type a Unicode character once.
     * @param unicode   Unicode value.
     */
    public static void typeUnicode(String unicode) {
        try {
            Robot robot = new Robot();
            
            typeKey(KeyEvent.VK_SPACE);
            for (int i = 2; i < unicode.length(); i++) {
                typeKey(KeyEvent.getExtendedKeyCodeForChar(unicode.charAt(i)));
            }
            robot.keyPress(KeyEvent.VK_ALT);
            typeKey(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_ALT);
            typeKey(KeyEvent.VK_LEFT);
            typeKey(KeyEvent.VK_BACK_SPACE);
            typeKey(KeyEvent.VK_RIGHT);
        } catch (AWTException e) {
            e.printStackTrace();
        }
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