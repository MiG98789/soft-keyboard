package softKeyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.*;

/**
 * <h1>Prediction Frame</h1>
 * <p> The <b>Prediction Frame</b> class handles the corresponding
 * predictions for mathematical symbols and functions.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @author  Jin Young Park
 * @since   1.1
 */
@SuppressWarnings("serial")
public class PredictionFrame extends JFrame {
    private static PredictionFrame predictionFrame = null;
    private KeyboardFrame keyboardFrame;
    
    // Frame variables
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 210;
    private final int NUM_OF_PREDICTIONS = 5;
    private final float FONT_SIZE = 22.0f;
    private JPanel panel;
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    private JScrollPane scrollPane;

    // Prediction variables
    private Vector<String> mathSymbolsFunctions = new Vector<String>();
    private boolean predictionState = false;

    /**
     * Private constructor to restrict to one instantiation.
     */
    private PredictionFrame() {
        super("Prediction List");
        loadFrame();
        loadSymbolsFunctions();
    }
    
    /**
     * Instantiates Prediction Frame singleton when called for the first time.
     * @return  Prediction Frame singleton.
     */
    public static PredictionFrame getInstance() {
        if (predictionFrame == null) {
            predictionFrame = new PredictionFrame();
        }
        return predictionFrame;
    }
    
    /**
     * Sets a Keyboard Frame reference.
     * @param keyboardFrame the Keyboard Frame to reference to.
     */
    public void setKeyboardFrame(KeyboardFrame keyboardFrame) {
        this.keyboardFrame = keyboardFrame;
    }
    
    /**
     * Returns predictionState.
     * @return true if Prediction Frame is trying to predict; false otherwise.
     */
    public boolean getPredictionState() {
        return predictionState;
    }
    
    /**
     * Type a key once.
     * @param keyCode   the key code of the corresponding KeyEvent.VK_[CHARACTER] to be typed.
     * @see             java.awt.event.KeyEvent
     */
    private void typeKey(int keyCode) {
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
    private void shiftKey(int keyCode) {
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
     * Predicts all possible math symbols or functions.
     * @param input the substring to be used for prediction.
     */
    public void mathPredict(String input) {
        System.out.println("Prediction input: " + input);

        // Clear all items
        dlm.clear();
        predictionState = false;
        if (input.isEmpty()) {
            return;
        }

        // Loop through each symbol, and add matching predictions to list
        for(String item : mathSymbolsFunctions) {
            if(input.length() <= item.length()) {
                if(input.equals(item.substring(0,  input.length()))) {
                    dlm.addElement(item);
                    System.out.println("Predicted: " + item);
                }
            }
        }    

        // Update list
        final JList<String> predictionList = new JList<String>(dlm);
        predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        predictionList.setSelectedIndex(0);
        predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
        predictionList.setFont(predictionList.getFont().deriveFont(FONT_SIZE));
        predictionList.addMouseListener(new MouseAdapter() { // Automatically type out selected item
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
                    if (predictionList.getSelectedIndex() != -1) {
                        int index = predictionList.locationToIndex(evt.getPoint());
                        String selection = (String)predictionList.getModel().getElementAt(index);
                        boolean isFunction = Character.isAlphabetic(selection.charAt(0));
                        String tempInput = input;
                        
                        if(selection.equals("acos") || selection.equals("asin") || selection.equals("atan")) {
                            for(int i = 0; i < input.length(); i++) {
                                typeKey(KeyEvent.VK_BACK_SPACE);
                                tempInput = "";
                            }
                            if (selection.equals("acos")) {
                                selection = "cos^-1";
                            } else if (selection.equals("asin")) {
                                selection = "sin^-1";
                            } else if (selection.equals("atan")) {
                                selection = "tan^-1";
                            }
                        }
                        System.out.println("You selected: " + selection);

                        try {
                            Robot robot = new Robot();
                            // Type out selection                        
                            for (int i = tempInput.length(); i < selection.length(); i++) {
                                char temp = selection.charAt(i);

                                if(temp == '^') {
                                    shiftKey(KeyEvent.VK_6);
                                } else {
                                    if (Character.isUpperCase(temp)) {
                                        robot.keyPress(KeyEvent.VK_SHIFT);
                                    }
                                    int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)temp);
                                    typeKey(keyCode);
                                    if (Character.isUpperCase(temp)) {
                                        robot.keyRelease(KeyEvent.VK_SHIFT);
                                    }
                                }
                            }
                            if(isFunction) {
                                shiftKey(KeyEvent.VK_9); // (
                                shiftKey(KeyEvent.VK_0); // )
                                typeKey(KeyEvent.VK_LEFT); // Go between ( and )
                            } else {
                                robot.keyPress(KeyEvent.VK_SPACE);
                            }

                            dlm.clear();
                            predictionState = true;
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(predictionList);

        // After an item is selected, clear the frame
        panel.removeAll();
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
        this.revalidate();
        this.repaint();
    }

    /**
     * Loads all the UI elements of Prediction Frame.
     */
    private void loadFrame() {
        // Frame
        this.pack();
        this.setVisible(true);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setFocusableWindowState(false);
            }
        });

        // Panel
        panel = new JPanel();

        // List
        final JList<String> predictionList = new JList<String>(dlm);
        predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        predictionList.setSelectedIndex(0);
        predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
        predictionList.setFont(predictionList.getFont().deriveFont(FONT_SIZE));

        // Scroll pane
        scrollPane = new JScrollPane(predictionList);

        // Display the GUI
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Loads special math symbols and functions from text files,
     * and stores them in <code>mathSymbolsFunctions</code>.
     */
    private void loadSymbolsFunctions() {
        try {
            InputStream is = getClass().getResourceAsStream("/symbols.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                mathSymbolsFunctions.add(line); 
            }

            is = getClass().getResourceAsStream("/mathFunctions.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            while ((line = bufferedReader.readLine()) != null) {
                mathSymbolsFunctions.add(line); 
            }

            System.out.println("Contents of file:");
            for (int i = 0; i < mathSymbolsFunctions.size(); i++) {
                System.out.println(mathSymbolsFunctions.elementAt(i).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}