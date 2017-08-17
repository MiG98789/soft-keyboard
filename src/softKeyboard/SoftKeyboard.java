package softKeyboard;

/**
 * <h1>Soft Keyboard</h1>
 * <p>Soft Keyboard is a virtual keyboard with special
 * mathematics-related predictions designed to aid people 
 * afflicted with mucopolysaccharidoses (MPS) or related 
 * diseases that impairs hand and arm motor movements.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @author  Jin Young Park
 * @version 1.3.4
 * @since   1.1
 */
public class SoftKeyboard {
    KeyboardFrame keyboardFrame;
    PredictionFrame predictionFrame;
    
    public SoftKeyboard() {
        keyboardFrame = KeyboardFrame.getInstance();
        predictionFrame = PredictionFrame.getInstance();
        keyboardFrame.setPredictionFrame(predictionFrame);
        predictionFrame.setKeyboardFrame(keyboardFrame);
        predictionFrame.setLocation(predictionFrame.getX() + predictionFrame.getWidth(), predictionFrame.getY());
    }
    
    public static void main(String[] args) {
        new SoftKeyboard();
    }
}