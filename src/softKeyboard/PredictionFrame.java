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
 * <p> The Prediction Frame class handles the corresponding
 * predictions for mathematical symbols and functions.</p>
 */
@SuppressWarnings("serial")
public class PredictionFrame extends JFrame {
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 210;
    private final int NUM_OF_PREDICTIONS = 5;
    private int NUM_OF_SYMBOLS;
    private final float FONT_SIZE = 22.0f;
    private JPanel panel;
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    private JScrollPane scrollPane;
    
    private Vector<String> mathSymbols = new Vector<String>();
    
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
     * Loads special math symbols from a text file,
     * and stores them in mathSymbols
     */
    private void loadSymbols() {
        try {
            InputStream is = getClass().getResourceAsStream("/symbols.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                mathSymbols.add(line); 
            }
            NUM_OF_SYMBOLS= mathSymbols.size();
            
            is = getClass().getResourceAsStream("/mathFunctions.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            
            while ((line = bufferedReader.readLine()) != null) {
                mathSymbols.add(line); 
            }
            
            
            System.out.println("Contents of file:");
            for (int i = 0; i < mathSymbols.size(); i++) {
                System.out.println(mathSymbols.elementAt(i).toString());
            }
             
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * @param str Input substring to be used for prediction
     */
    public void predictSymbol(String input) {
        System.out.println("Prediction input: " + input);
        
        // Clear all items
        dlm.clear();
        if (input.isEmpty()) {
            return;
        }

        // Loop through each symbol, and add matching predictions to list
        for (int i = 0; i < mathSymbols.size(); i++) {
            if (input.length() <= mathSymbols.elementAt(i).length()) {
                if (input.equals(mathSymbols.elementAt(i).substring(0, input.length()))) {
                    dlm.addElement(mathSymbols.elementAt(i));
                    System.out.println("Predicted: " + mathSymbols.elementAt(i));
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
                        System.out.println("You selected: " + selection);
                                        
                        try {
                            Robot robot = new Robot();
    
                            // Type out selection                        
                            for (int i = input.length(); i < selection.length(); i++) {
                                char temp = selection.charAt(i);
                                if (Character.isUpperCase(temp)) {
                                    robot.keyPress(KeyEvent.VK_SHIFT);
                                }
                                int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)temp);
                                robot.keyPress(keyCode); robot.keyRelease(keyCode);
                                if (Character.isUpperCase(temp)) {
                                    robot.keyRelease(KeyEvent.VK_SHIFT);
                                }
                            }
                            robot.keyPress(KeyEvent.VK_SPACE);

                            dlm.clear();
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
     * @param title Sets window title
     */
    public PredictionFrame(String title) {
        super(title);
        loadFrame();
        loadSymbols();
    }
}