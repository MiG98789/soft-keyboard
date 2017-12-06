package soft;

import soft.keyboard.KeyboardFrame;

/**
 * <h1>Soft Keyboard</h1>
 * <p>Soft Keyboard is a virtual keyboard with special
 * mathematics-related predictions designed to aid people 
 * afflicted with mucopolysaccharidoses (MPS) or related 
 * diseases that impairs hand and arm motor movements.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @author  Jin Young Park
 * @version 2.0
 * @since   1.1
 */
public class SoftKeyboard {
    KeyboardFrame keyboardFrame;
    
    /**
     * Constructor of SoftKeyboard.
     * Instatiates the keyboard frame.
     */
    public SoftKeyboard() {
        keyboardFrame = KeyboardFrame.getInstance();
    }
    
    /**
     * Main function.
     * @param args any arguments passed.
     */
    public static void main(String[] args) {
        new SoftKeyboard();
    }
}