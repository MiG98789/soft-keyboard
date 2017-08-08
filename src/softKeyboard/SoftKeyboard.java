package softKeyboard;

/**
 * <h1>Soft Keyboard</h1>
 * <p>Soft Keyboard is a virtual keyboard designed specifically to
 * aid people afflicted with mucopolysaccharidoses (MPS) or
 * related diseases that impairs hand and arm motor movements.</p>
 * 
 * @author Gian Miguel Sero Del Mundo, Jin Young Park
 * @version 1.1
 * @since 19/07/2017
 */
public class SoftKeyboard {    
    public SoftKeyboard() {
        new Keyboard("Soft Keyboard");
    }
    
    public static void main(String[] args) {
        new SoftKeyboard();
    }
}
