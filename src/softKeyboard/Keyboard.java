package softKeyboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Keyboard extends JFrame {
//    private JFrame predictionFrame;
//    private JPanel predictionPanel;
//    private final DefaultListModel predictionDLM = new DefaultListModel();
//    private JScrollPane predictionScrollPane;
//    private final int NUM_OF_PREDICTIONS = 5;
//    private final float PREDICTION_LIST_FONT_SIZE = 22.0f;
    
    private PredictionModel predictionModel;
    
    // TODO: Fix UI layout
    private int FRAME_WIDTH = 450;
    private int FRAME_HEIGHT = 420;
    private JPanel panel;
    private JLabel label;
    private ImageIcon background = new ImageIcon(getClass().getResource("/bg.png"));
    
    private int KEY_WIDTH = 25;
    private int KEY_HEIGHT = 25;
    private ImageIcon[] specialIcons = new ImageIcon[3];
    private String[] specialURLs = {"/backspace.png", "/space.png", "/enter.png"};

    private JToggleButton mathMode = new JToggleButton("Normal Mode", false);
    
    private double SCALE_FACTOR = 1.2;
    private JButton[] changeSizeKeys = new JButton[2];
    
    /* 1) Special (left-side): backspace, space, enter, \, =, (
     * 2) Arithmetic
     * 3) Numbers
     * 4) Symbols
     * 5) Symbols
     * 6) Letters
     */
    private int NUM_OF_KEY_LAYERS = 6;
    private JButton[][] keys = new JButton[NUM_OF_KEY_LAYERS][];
    
    private JButton[] specialKeys = new JButton[6]; // Backspace, space, enter, \, =, (
    private JButton[] arithmeticKeys = new JButton[5];
    private JButton[] numberKeys = new JButton[10];
    private JButton[] layer3Keys = new JButton[12];
    private JButton[] layer4Keys = new JButton[12]; // TODO: Put shift and caps
    private JButton[] letterKeys = new JButton[26];

    private boolean shiftClick = false;
    private boolean capsClick = false;
    
//    private Vector<String> mathSymbols = new Vector<String>();
    private String predictionInput;
    private boolean isPredict = false;

    /* Converts Soft Keyboard non-alphabetical key input into actual keyboard input */
    /* TODO: In math mode, automatically clear predictionInput when typing anything,
             and press space before pressing the desired key
    */
    private ActionListener numericSymbolicListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();

            try {
                Robot robot = new Robot();
                
                isPredict = false; // Will turn true ONLY IF starting with \ or alphabet
                // TODO: Make it work for \ and alphabets in Math Mode
                
                // Numbers
                if (actionCommand == "0") {robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);}
                else if (actionCommand == "1") {robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);}
                else if (actionCommand == "2") {robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);}
                else if (actionCommand == "3") {robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);}
                else if (actionCommand == "4") {robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);}
                else if (actionCommand == "5") {robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);}
                else if (actionCommand == "6") {robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);}
                else if (actionCommand == "7") {robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);}
                else if (actionCommand == "8") {robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);}
                else if (actionCommand == "9") {robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);}

                // Symbols
                else if (actionCommand == "`") {robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);}
                else if (actionCommand == "~") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "!") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "@") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "#") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "$") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "%") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "^") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (actionCommand == "&") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }else if (actionCommand == "*") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                
                // SPECIAL CASE: Autocompletes ), then puts cursor between ( and )
                else if (actionCommand == "(") {
                    // (                    
                    robot.keyPress(KeyEvent.VK_SHIFT);                
                    robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);                
                    robot.keyRelease(KeyEvent.VK_SHIFT);

                    // )
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_SHIFT);

                    // Go between ( and )
                    robot.keyPress(KeyEvent.VK_LEFT);robot.keyRelease(KeyEvent.VK_LEFT);
                } 
                
                else if (actionCommand == ")") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "-") {robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);}
                else if (actionCommand == "_") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "=") {robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);}
                else if (actionCommand == "+") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "[") {robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);}
                else if (actionCommand == "{") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "]") {robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);}
                else if (actionCommand == "}") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "\\") {
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                    predictionInput = "\\";
                    isPredict = true;
                    predictionModel.predictSymbol(predictionInput);
                }
                else if (actionCommand == "|") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == ";") {robot.keyPress(KeyEvent.VK_SEMICOLON);robot.keyRelease(KeyEvent.VK_SEMICOLON);}
                else if (actionCommand == ":") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_SEMICOLON);robot.keyRelease(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "'") {robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);}
                else if (actionCommand == "\"") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == ",") {robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);}
                else if (actionCommand == "<") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == ".") {robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);}
                else if (actionCommand == ">") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }

                else if (actionCommand == "/") {robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);}
                else if (actionCommand == "?") {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                
                System.out.println("Input: " + predictionInput);
                if (!isPredict) {
                    predictionModel.predictSymbol("");
                }
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    };

    private static String removeLastChar(String str) {
        if (str.isEmpty()) {
            return "";
        } else {
            return str.substring(0, str.length() - 1);
        }
    }
    
//    /* Predicts the symbols to be typed */
//    private void predictSymbol(String str) {
//        System.out.println("Prediction input: " + str);
//        
//        // Reset Prediction List Frame
//        predictionDLM.clear();
//        if (str.isEmpty()) {
//            return;
//        }
//
//        // Loop through each symbol, and add predictions to Prediction List Frame
//        for (int i = 0; i < mathSymbols.size(); i++) {
//            if (str.length() <= mathSymbols.elementAt(i).length()) {
//                if (str.equals(mathSymbols.elementAt(i).substring(0, str.length()))) {
//                    System.out.println("Predicted: ");
//                    predictionDLM.addElement(mathSymbols.elementAt(i));
//                    System.out.println(mathSymbols.elementAt(i));
//                }
//            }    
//        }
        
//        // Update Prediction List Frame
//        final JList predictionList = new JList(predictionDLM);
//        predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        predictionList.setSelectedIndex(0);
//        predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
//        predictionList.setFont(predictionList.getFont().deriveFont(PREDICTION_LIST_FONT_SIZE));
//        
//        predictionList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent evt) {
//                if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
//                    if (predictionList.getSelectedIndex() != -1) {
//                        int index = predictionList.locationToIndex(evt.getPoint());
//                        String selection = (String)predictionList.getModel().getElementAt(index);
//                        System.out.println("You selected: " + selection);
//                                        
//                        try {
//                            Robot robot = new Robot();
//    
//                            // Type out selection                        
//                            for (int i = str.length(); i < selection.length(); i++) {
//                                char temp = selection.charAt(i);
//                                if (Character.isUpperCase(temp)) {
//                                    robot.keyPress(KeyEvent.VK_SHIFT);
//                                }
//                                int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)temp);
//                                robot.keyPress(keyCode); robot.keyRelease(keyCode);
//                                if (Character.isUpperCase(temp)) {
//                                    robot.keyRelease(KeyEvent.VK_SHIFT);
//                                }
//                            }
//                            robot.keyPress(KeyEvent.VK_SPACE);
//                            isPredict = false;
//                            predictionInput = "";
//                            predictionDLM.clear();
//                        } catch (AWTException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//        
//        predictionScrollPane = new JScrollPane(predictionList);
//        predictionPanel.removeAll();
//        predictionPanel.add(predictionScrollPane);
//        
//        predictionPanel.revalidate();
//        predictionPanel.repaint();
//        predictionFrame.revalidate();
//        predictionFrame.repaint();
//    }
//    
//    /* Sets up prediction list */
//    private void predictionListInit() {
//        // Frame
//        predictionFrame = new JFrame("Prediction List");
//        predictionFrame.pack();
//        predictionFrame.setVisible(true);
//        predictionFrame.setSize(FRAME_WIDTH - 50, FRAME_HEIGHT / 2);
//        predictionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        predictionFrame.setLocationRelativeTo(null);
//        predictionFrame.setAlwaysOnTop(true);
//
//        predictionFrame.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                predictionFrame.setFocusableWindowState(false);
//            }
//        });
//        
//        // Panel
//        predictionPanel = new JPanel();
//        
//        // List
//        final JList predictionList = new JList(predictionDLM);
//        predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        predictionList.setSelectedIndex(0);
//        predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
//        predictionList.setFont(predictionList.getFont().deriveFont(PREDICTION_LIST_FONT_SIZE));
////        predictionList.addListSelectionListener(predictionListener);
//        
//        // Scroll pane
//        predictionScrollPane = new JScrollPane(predictionList);
//
//        predictionPanel.add(predictionScrollPane);
//        predictionFrame.add(predictionPanel);
//
//        predictionPanel.revalidate();
//        predictionPanel.repaint();
//        predictionFrame.revalidate();
//        predictionFrame.repaint();
//    }
    
    /* Sets up background */
    private void loadBackground() {
        // Frame
        this.pack();
        this.setVisible(true);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setFocusableWindowState(false);
            }
        });

        // Panel
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(null);
        panel.setLayout(new BorderLayout());
        
        // Label
        label = new JLabel(background, JLabel.CENTER);
//        label.setBounds(50,  50, this.getWidth() - 100,  (int)((this.getWidth() - 100) * 1.18));
        label.setBackground(Color.DARK_GRAY);
        label.repaint();
        label.revalidate();
    }
    
    /* Set up left-side buttons */
    // TODO: Adjust bounds to fit better (increase size, and if possible, shape)
    private void specialInit() {
        // Scale icon sizes
        for(int i = 0; i < specialIcons.length; i++) {
            specialIcons[i] = new ImageIcon(getClass().getResource(specialURLs[i]));
            Image temp = specialIcons[i].getImage();    
            int tempWidth = temp.getWidth(null);
            int tempHeight = temp.getHeight(null);
            if(specialURLs[i] == "/enter.png") {
                temp = temp.getScaledInstance((int)(tempWidth / 20 * (double)(this.getWidth() / 450.0)), 
                                            (int)(tempHeight / 20 * (double)(this.getWidth() / 450.0)),
                                            Image.SCALE_SMOOTH);
            } else {
                temp = temp.getScaledInstance((int)(tempWidth / 7 * (double)(this.getWidth() / 450.0)), 
                        (int)(tempHeight / 7 * (double)(this.getWidth() / 450.0)),
                        Image.SCALE_SMOOTH);
            }
            specialIcons[i].setImage(temp);
            specialKeys[i] = new JButton(specialIcons[i]);
        }
        specialKeys[3] = new JButton("\\");
        specialKeys[4] = new JButton("=");
        specialKeys[5] = new JButton("(");
        
        for (int i = 0; i < 6; i++) {
            specialKeys[i].setBorder(null);
            specialKeys[i].setBorderPainted(false);
            specialKeys[i].setContentAreaFilled(false);
            specialKeys[i].setOpaque(false);
            
            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            specialKeys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    specialKeys[x].setBackground(Color.PINK);
                    specialKeys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    specialKeys[x].setBackground(null);
                    specialKeys[x].setContentAreaFilled(false);
                }
            });

            if (i < 3) { // Backspace, space, enter
                specialKeys[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            Robot robot = new Robot();
                            
                            switch(x) {
                            case 0: // Backspace
                                robot.keyPress(KeyEvent.VK_BACK_SPACE);robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                                
                                if (predictionInput.isEmpty()) {
                                    isPredict = false;
                                } else {
                                    predictionInput = removeLastChar(predictionInput);
                                }
                                predictionModel.predictSymbol(predictionInput);
                                break;
                            
                            case 1: // Space
                                robot.keyPress(KeyEvent.VK_SPACE);robot.keyRelease(KeyEvent.VK_SPACE);
                                specialKeys[x].removeActionListener(numericSymbolicListener);
                                specialKeys[x].addActionListener(numericSymbolicListener);
                                break;
                            
                            case 2:
                                if (mathMode.isSelected()) { // Math mode
                                    robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
                                    robot.keyPress(KeyEvent.VK_ALT);
                                    robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);
                                    robot.keyRelease(KeyEvent.VK_ALT);
                                }
                                else { // Normal mode
                                    robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
                                }
                                break;
                            }
                            
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            else { // \, =, (
                specialKeys[i].removeActionListener(numericSymbolicListener);
                specialKeys[i].addActionListener(numericSymbolicListener);
                specialKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(this.getWidth() / 500.0))));
            }
        }
        
        int xValue, yValue;
        
        // Backspace
        xValue = (int)(this.getWidth() / 2) - 10;
        yValue = (int)(this.getHeight() / 2) - 35;
        specialKeys[0].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        
        // \
        xValue = (int)(this.getWidth() / 2) - 55;
        yValue = (int)(this.getHeight() / 2) - 35;
        specialKeys[3].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        
        // (1) Space, (2) enter
        double radian;
        double initDegree = 23;
        double incrementDegree = -40;
        
        int midX = (int)((this.getWidth()) / 2) - 20;
        int midY = (int)((this.getHeight()) / 2) - 32;
        int radius = (int)((this.getWidth()) / 4) - 15;
        
        for (int i = 1; i <= 2; i++) {
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
            yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;
            
            specialKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        }
        
        // (4) =, (5) (
        initDegree = 23;
        incrementDegree = -40;
        radius = (int)((this.getWidth()) / 3) - 5;
        
        for (int i = 4; i <= 5; i++) {
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
            yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;
            
            specialKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        }
    }

    /* Set up arithmetic buttons */
    private void arithmeticInit_1() {
        arithmeticKeys[0] = new JButton("+");
        arithmeticKeys[1] = new JButton("-");
        arithmeticKeys[2] = new JButton(".");
        arithmeticKeys[3] = new JButton("*");
        arithmeticKeys[4] = new JButton("/");
    }
    
    private void arithmeticInit_2() {
        double radian;
        int xValue, yValue;
        double initDegree = 65;
        double incrementDegree = 56;
        
        int midX = (int)((this.getWidth()) / 2) - 20;
        int midY = (int)((this.getHeight()) / 2) - 32;
        int radius = (int)((this.getWidth()) / 9) - 5;

        for (int i = 0; i < 5; i++) {
            arithmeticKeys[i].setBorder(null);
            arithmeticKeys[i].setBorderPainted(false);
            arithmeticKeys[i].setContentAreaFilled(false);
            arithmeticKeys[i].setOpaque(false);

            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
            yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;
            
            arithmeticKeys[i].setBounds(xValue, yValue, (int)(KEY_WIDTH * 0.8), (int)(KEY_HEIGHT * 0.8));
            arithmeticKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(this.getWidth() / 500.0))));
            arithmeticKeys[i].setForeground(Color.WHITE);

            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            arithmeticKeys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    arithmeticKeys[x].setBackground(Color.PINK);
                    arithmeticKeys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    arithmeticKeys[x].setBackground(null);
                    arithmeticKeys[x].setContentAreaFilled(false);
                }
            });
        }
    }
    
    private void arithmeticInit() {
        arithmeticInit_1();
        arithmeticInit_2(); 
        
    }
    
    /* Sets up letter buttons */
    private void letterInit_1() {
        for (int i = 0; i < 26; i++) {
            letterKeys[i] = new JButton("" + (char)(i + 'a'));
        }
    }
    
    private void letterInit_2() {
        double radian;
        double initDegree = 50;
        double incrementDegree = 10.3;

        int xValue, yValue;
        int midX = (int) ((this.getWidth()) / 2) - 20;
        int midY = (int) ((this.getHeight()) / 2) - 35;
        int radius = (int) ((this.getWidth()) / 2.5) - 13;

        for (int i = 0; i < 26; i++) {
            char temp = (char) ('a' + i);
            // letterKeys[i] = new JButton(Character.toString(temp));
            char lowercase = (char) ('a' + i);
            // String lowerTemp = Character.toString(lowercase);
            char uppercase = (char) ('A' + i);
            // String upperTemp = Character.toString(uppercase);

            letterKeys[i].setBorder(null);
            letterKeys[i].setBorderPainted(false);
            letterKeys[i].setContentAreaFilled(false);
            letterKeys[i].setOpaque(false);

            // Calculates coordinates of each letter
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int) (Math.cos(radian) * radius) + midX;
            yValue = -1 * (int) (Math.sin(radian) * radius) + midY;
            initDegree += incrementDegree;

            letterKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            letterKeys[i].setFont(new Font("Arial", Font.PLAIN, (int) (25 * (double) (this.getWidth() / 500.0))));
            letterKeys[i].setForeground(Color.WHITE);

            letterKeys[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        Robot robot = new Robot();

                        if (!shiftClick && !capsClick) { // Lower case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (lowercase));
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                        } else if (!shiftClick && capsClick) { // Upper case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (uppercase));
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        } else if (shiftClick && !capsClick) { // Upper case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (uppercase));
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                            shiftClick = false;
                        } else { // Lower case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (lowercase));
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                            shiftClick = false;
                        }

                        if (isPredict) {
                            predictionInput += temp;
                            predictionModel.predictSymbol(predictionInput);
                        }
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            letterKeys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    letterKeys[x].setBackground(Color.PINK);
                    letterKeys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    letterKeys[x].setBackground(null);
                    letterKeys[x].setContentAreaFilled(false);
                }
            });
        }
    }
    
    private void letterInit() {
        letterInit_1();
        letterInit_2();
    }

    /* Sets up layer 3 buttons */    
    private void layer3Init_1(){
        layer3Keys[0] = new JButton("[");
        layer3Keys[1] = new JButton("]");
        layer3Keys[2] = new JButton("|");
        layer3Keys[3] = new JButton("<");
        layer3Keys[4] = new JButton(">");
        layer3Keys[5] = new JButton("%");
        layer3Keys[6] = new JButton("^");
        layer3Keys[7] = new JButton("~");
        layer3Keys[8] = new JButton("`");
        layer3Keys[9] = new JButton("_");
        layer3Keys[10] = new JButton("}");
        layer3Keys[11] = new JButton("{");
    }
    
    private void layer3Init_2() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 28.5;

        int xValue, yValue;
        int midX = (int) ((this.getWidth()) / 2) - 20;
        int midY = (int) ((this.getHeight()) / 2) - 35;
        int radius = (int) ((this.getWidth()) / 4) - 6;

        for (int i = 0; i < 10; i++) {
            layer3Keys[i].setBorder(null);
            layer3Keys[i].setBorderPainted(false);
            layer3Keys[i].setContentAreaFilled(false);
            layer3Keys[i].setOpaque(false);

            radian = Math.toRadians(initDegree);
            xValue = -1 * (int) (Math.cos(radian) * radius) + midX;
            yValue = -1 * (int) (Math.sin(radian) * radius) + midY;
            initDegree += incrementDegree;

            layer3Keys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            layer3Keys[i].setFont(new Font("Arial", Font.PLAIN, (int) (25 * (double) (this.getWidth() / 500.0))));
            layer3Keys[i].setForeground(Color.WHITE);

            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            layer3Keys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    layer3Keys[x].setBackground(Color.PINK);
                    layer3Keys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    layer3Keys[x].setBackground(null);
                    layer3Keys[x].setContentAreaFilled(false);
                }
            });
        }
    }
    
    private void layer3Init(){
        layer3Init_1();
        layer3Init_2();
    }

    /* Sets up layer 4 buttons */
    // TODO: Add shift and caps in the middle
    private void layer4Init_1(){
        layer4Keys[0] = new JButton(",");
        layer4Keys[1] = new JButton(";");
        layer4Keys[2] = new JButton(":");
        layer4Keys[3] = new JButton("?");
        layer4Keys[4] = new JButton("!");
        layer4Keys[5] = new JButton("\"");
        layer4Keys[6] = new JButton("'");
        layer4Keys[7] = new JButton("@");
        layer4Keys[8] = new JButton("#");
        layer4Keys[9] = new JButton("&");
        layer4Keys[10] = new JButton("$");
        layer4Keys[11] = new JButton(")");
    }
    
    private void layer4Init_2() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 23;
        
        int xValue, yValue;
        int midX = (int)((this.getWidth()) / 2) - 20;
        int midY = (int)((this.getHeight()) / 2) - 32;
        int radius = (int)((this.getWidth()) / 3) - 15;

        for (int i = 0; i < 12; i++) {
            layer4Keys[i].setBorder(null);
            layer4Keys[i].setBorderPainted(false);
            layer4Keys[i].setContentAreaFilled(false);
            layer4Keys[i].setOpaque(false);

            // Calculates coordinates of each layer4
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
            yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;
            
            layer4Keys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            layer4Keys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(this.getWidth() / 500.0))));
            layer4Keys[i].setForeground(Color.WHITE);

            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            layer4Keys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    layer4Keys[x].setBackground(Color.PINK);
                    layer4Keys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    layer4Keys[x].setBackground(null);
                    layer4Keys[x].setContentAreaFilled(false);
                }
            });
        }
    }
    
    private void layer4Init(){
        layer4Init_1();
        layer4Init_2();
        
    }
    
    //set up change size buttons
    private void loadChangeSize(){
        changeSizeKeys[0] = new JButton("-");
        changeSizeKeys[1] = new JButton("+");
        
        int xValue = (int)(this.getWidth()*0.8);
        int yValue = (int)(this.getHeight()*0.8);
        
        for (int i=0; i<2; i++){
            changeSizeKeys[i].setBorder(BorderFactory.createBevelBorder(10, Color.red, Color.gray));
            changeSizeKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(this.getWidth() / 500.0))));
        }
        changeSizeKeys[0].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        changeSizeKeys[1].setBounds(xValue+KEY_WIDTH, yValue, KEY_WIDTH, KEY_HEIGHT);
    
    }
    
    // change size as person presses the button
    // TODO: Decrease size of special icons not working, and fix positioning of keys
    private void changeSize(){
        for (int i=0; i<2; i++){
            final Integer x = new Integer(i);
            changeSizeKeys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked (MouseEvent e) {
                    //if smaller
                    if (x==0){
                        setSize((int)(getWidth()/1.2),(int)(getHeight()/1.2));
                        changeSizeLoad();
                        background = new ImageIcon(getClass().getResource("/bg.png"));                
                    

                    }
                    //if larger
                    else{
                        
                        setSize((int)(getWidth()*1.2),(int)(getHeight()*1.2));
                        changeSizeLoad();
                        background = new ImageIcon(getClass().getResource("/bg3.png"));                        
                    }
                }
            });
        }

    }

    /* Sets up number buttons */
    // TODO: Scroll through numbers 
    private void numberInit_1() {
        numberKeys[0] = new JButton("0");
        numberKeys[1] = new JButton("1");
        numberKeys[2] = new JButton("2");
        numberKeys[3] = new JButton("3");
        numberKeys[4] = new JButton("4");
        numberKeys[5] = new JButton("5");
        numberKeys[6] = new JButton("6");
        numberKeys[7] = new JButton("7");
        numberKeys[8] = new JButton("8");
        numberKeys[9] = new JButton("9");
    }
    
    private void numberInit_2() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 28;
        
        int xValue, yValue;
        int midX = (int)((this.getWidth()) / 2) - 20;
        int midY = (int)((this.getHeight()) / 2) - 32;
        int radius = (int)((this.getWidth()) / 6);

        for (int i = 0; i < 10; i++) {
            numberKeys[i].setBorder(null);
            numberKeys[i].setBorderPainted(false);
            numberKeys[i].setContentAreaFilled(false);
            numberKeys[i].setOpaque(false);

            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
            yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;
            
            numberKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            numberKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(this.getWidth() / 500.0))));
            numberKeys[i].setForeground(Color.WHITE);

            // Changes button appearance based on cursor
            final Integer x = new Integer(i);
            numberKeys[x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    numberKeys[x].setBackground(Color.PINK);
                    numberKeys[x].setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    numberKeys[x].setBackground(null);
                    numberKeys[x].setContentAreaFilled(false);
                }
            });
        }
    }
    
    private void numberInit(){
        numberInit_1();
        numberInit_2();
    }

    private void changeSizeLoad(){
        specialInit();
        arithmeticInit_2();
        numberInit_2();
        layer3Init_2();
        layer4Init_2();
        letterInit_2();
    }

    private void loadGUI() {        
        // Prediction list
//        predictionListInit();
        // Background
//        loadBackground();

//        this.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                System.out.println("Pressed");
//                int i = 0;
//                for (;i<mathSymbols.size();i++) {
//                    if (e.getKeyChar()==mathSymbols.get(i).charAt(1)) {
//                        System.out.println(mathSymbols.get(i));
//                    }
//                }
//            }
//        }); 
            
        // Special symbols
        specialInit();
        for (int i = 0; i < 6; i++) {
            panel.add(specialKeys[i]);
        }
        
        // Arithmetic symbols
        arithmeticInit();
        for (int i = 0; i < 5; i++) {
            arithmeticKeys[i].removeActionListener(numericSymbolicListener);
            arithmeticKeys[i].addActionListener(numericSymbolicListener);
            panel.add(arithmeticKeys[i]);
        }
        
        // Numbers
        numberInit();
        for (int i = 0; i < 10; i++) {
            numberKeys[i].removeActionListener(numericSymbolicListener);
            numberKeys[i].addActionListener(numericSymbolicListener);
            panel.add(numberKeys[i]);
        }
        
        // Layer 3
        layer3Init();
        for (int i = 0; i < 12; i++) {
            layer3Keys[i].removeActionListener(numericSymbolicListener);
            layer3Keys[i].addActionListener(numericSymbolicListener);
            panel.add(layer3Keys[i]);
        }
        
        // Layer 4
        layer4Init();
        for (int i = 0; i < 12; i++) {
            layer4Keys[i].removeActionListener(numericSymbolicListener);
            layer4Keys[i].addActionListener(numericSymbolicListener);
            panel.add(layer4Keys[i]);
        }
        
        // Letters
        letterInit();
        for (int i = 0; i < 26; i++) {
            panel.add(letterKeys[i]);
        }
        
        // Add mode toggle button
        mathMode.setBounds(0,0, 110,30);
        mathMode.setFont(new Font("Arial", Font.PLAIN, 12));
        mathMode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                  System.out.println("Math Mode");
                  mathMode.setText("Math Mode");
                } else {
                  System.out.println("Normal Mode");
                  mathMode.setText("Normal Mode");
                }
              }
        });
        panel.add(mathMode);
        
        //change size buttons
        loadChangeSize();
        for (int i=0; i<2; i++){
            panel.add(changeSizeKeys[i]);
        }
        changeSize();
        
        panel.add(label);
        this.add(panel);

        panel.revalidate();
        panel.repaint();
        this.revalidate();
        this.repaint();
    }

    public Keyboard(String title) {
        super(title);
        loadBackground();
        loadGUI();
        predictionModel = new PredictionModel("Prediction List");    
    }
}