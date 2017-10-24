package soft.helper;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * <h1>Helper</h1>
 * <p>Helper class contains helper function methods.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @since   2.0
 */
public class Helper {
    /**
     * Type a key once.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_[CHARACTER] to be typed.
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
     * @param keyCode   the key code of the corresponding KeyEvent.VK_[CHARACTER] to be typed.
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
}
