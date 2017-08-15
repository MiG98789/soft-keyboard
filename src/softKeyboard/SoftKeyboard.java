package softKeyboard;

/**
 * <h1>Soft Keyboard</h1>
 * <p>Soft Keyboard is a virtual keyboard with special
 * mathematics-related predictions designed to aid people 
 * afflicted with mucopolysaccharidoses (MPS) or related 
 * diseases that impairs hand and arm motor movements.</p>
 * 
 * @author Gian Miguel Sero Del Mundo, Jin Young Park
 * @version 1.2
 * @since 19/07/2017
 */
public class SoftKeyboard {    
    public SoftKeyboard() {
        new KeyboardFrame("Soft Keyboard");
    }
    
    public static void main(String[] args) {
        new SoftKeyboard();
    }
}