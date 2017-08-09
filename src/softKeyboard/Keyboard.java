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

@SuppressWarnings("serial")
public class Keyboard extends JFrame {
    private PredictionModel predictionModel;

    // TODO: Fix UI layout
    private int FRAME_WIDTH = 450;
    private int FRAME_HEIGHT = 420;
    private JPanel panel;
    private JLabel label;
    private ImageIcon background = new ImageIcon(getClass().getResource("/bg.png"));

    private JToggleButton mathMode = new JToggleButton("Normal Mode", false);
    private String predictionInput;
    private boolean isPredict = false; // TODO: Make it work for \ and alphabets in Math Mode

    private double SCALE_FACTOR = 1.2;
    private int currScaleCount = 0;
    private int MAX_SCALE_COUNT = 2;
    private JButton[] changeSizeButtons = new JButton[2];

    private int KEY_WIDTH = 25;
    private int KEY_HEIGHT = 25;
    private JButton[] specialKeys = new JButton[6]; // Backspace, space, enter, \, =, (
    private JButton[] arithmeticKeys = new JButton[5];
    private JButton[] numberKeys = new JButton[10];
    private JButton[] layer3Keys = new JButton[12];
    private JButton[] layer4Keys = new JButton[12]; // TODO: Put shift and caps
    private JButton[] letterKeys = new JButton[26];
    private JButton[][] keys = {specialKeys, arithmeticKeys, numberKeys, layer3Keys, layer4Keys, letterKeys};
    private ImageIcon[] specialIcons = new ImageIcon[3];
    private String[] specialURLs = {"/backspace.png", "/space.png", "/enter.png"};
    private boolean shiftClick = false;
    private boolean capsClick = false;
    private MouseAdapter keyHighlightAdapter;

    private void typeKey(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void shiftKey(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SHIFT);
            typeKey(key);
            robot.keyRelease(key);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /* Converts Soft Keyboard non-alphabetical key input into actual keyboard input */
    /* TODO: In math mode, automatically clear predictionInput when typing anything,
             and press space before pressing the desired key
     */
    private ActionListener numericSymbolicListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            isPredict = false; // Will turn true ONLY IF starting with \ or alphabet

            // Numbers
            if (actionCommand == "0") {typeKey(KeyEvent.VK_0);}
            else if (actionCommand == "1") {typeKey(KeyEvent.VK_1);}
            else if (actionCommand == "2") {typeKey(KeyEvent.VK_2);}
            else if (actionCommand == "3") {typeKey(KeyEvent.VK_3);}
            else if (actionCommand == "4") {typeKey(KeyEvent.VK_4);}
            else if (actionCommand == "5") {typeKey(KeyEvent.VK_5);}
            else if (actionCommand == "6") {typeKey(KeyEvent.VK_6);}
            else if (actionCommand == "7") {typeKey(KeyEvent.VK_7);}
            else if (actionCommand == "8") {typeKey(KeyEvent.VK_8);}
            else if (actionCommand == "9") {typeKey(KeyEvent.VK_9);}

            // Symbols
            else if (actionCommand == "`") {typeKey(KeyEvent.VK_BACK_QUOTE);}
            else if (actionCommand == "~") {shiftKey(KeyEvent.VK_BACK_QUOTE);}
            else if (actionCommand == "!") {shiftKey(KeyEvent.VK_1);}
            else if (actionCommand == "@") {shiftKey(KeyEvent.VK_2);}
            else if (actionCommand == "#") {shiftKey(KeyEvent.VK_3);}
            else if (actionCommand == "$") {shiftKey(KeyEvent.VK_4);}
            else if (actionCommand == "%") {shiftKey(KeyEvent.VK_5);}
            else if (actionCommand == "^") {shiftKey(KeyEvent.VK_6);}
            else if (actionCommand == "&") {shiftKey(KeyEvent.VK_7);}
            else if (actionCommand == "*") {shiftKey(KeyEvent.VK_8);}                
            else if (actionCommand == ")") {shiftKey(KeyEvent.VK_0);}
            else if (actionCommand == "-") {typeKey(KeyEvent.VK_MINUS);}
            else if (actionCommand == "_") {shiftKey(KeyEvent.VK_MINUS);}
            else if (actionCommand == "=") {typeKey(KeyEvent.VK_EQUALS);}
            else if (actionCommand == "+") {shiftKey(KeyEvent.VK_EQUALS);}
            else if (actionCommand == "[") {typeKey(KeyEvent.VK_OPEN_BRACKET);}
            else if (actionCommand == "{") {shiftKey(KeyEvent.VK_OPEN_BRACKET);}
            else if (actionCommand == "]") {typeKey(KeyEvent.VK_CLOSE_BRACKET);}
            else if (actionCommand == "}") {shiftKey(KeyEvent.VK_CLOSE_BRACKET);}
            else if (actionCommand == "|") {shiftKey(KeyEvent.VK_BACK_SLASH);}
            else if (actionCommand == ";") {typeKey(KeyEvent.VK_SEMICOLON);}
            else if (actionCommand == ":") {shiftKey(KeyEvent.VK_SEMICOLON);}
            else if (actionCommand == "'") {typeKey(KeyEvent.VK_QUOTE);}
            else if (actionCommand == "\"") {shiftKey(KeyEvent.VK_QUOTE);}
            else if (actionCommand == ",") {typeKey(KeyEvent.VK_COMMA);}
            else if (actionCommand == "<") {shiftKey(KeyEvent.VK_COMMA);}
            else if (actionCommand == ".") {typeKey(KeyEvent.VK_PERIOD);}
            else if (actionCommand == ">") {shiftKey(KeyEvent.VK_PERIOD);}
            else if (actionCommand == "/") {typeKey(KeyEvent.VK_SLASH);}
            else if (actionCommand == "?") {shiftKey(KeyEvent.VK_SLASH);}

            // Special cases
            else if (actionCommand == "(") { // Autocompletes ), then puts cursor between ( and )
                // TODO: Decide whether to permanently enable this
                shiftKey(KeyEvent.VK_9); // (
                shiftKey(KeyEvent.VK_0); // )
                typeKey(KeyEvent.VK_LEFT); // Go between ( and )
            } else if (actionCommand == "\\") { // Restart prediction
                typeKey(KeyEvent.VK_BACK_SLASH);
                predictionInput = "\\";
                isPredict = true;
                predictionModel.predictSymbol(predictionInput);
            }

            System.out.println("Input: " + predictionInput);
            if (!isPredict) {
                predictionModel.predictSymbol("");
            }
        }
    };

    /* Highlights key background when cursor is hovering over it */
    private void addKeyHighlightAdapter(JButton b) {
        keyHighlightAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                b.setBackground(Color.PINK);
                b.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b.setBackground(null);
                b.setContentAreaFilled(false);
            }
        };
        b.addMouseListener(keyHighlightAdapter);
    }

    /* Helper function for backspace */
    private static String removeLastChar(String str) {
        if (str.isEmpty()) {
            return "";
        } else {
            return str.substring(0, str.length() - 1);
        }
    }

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
        //      label.setBounds(50,  50, this.getWidth() - 100,  (int)((this.getWidth() - 100)*1.18));
        label.setBackground(Color.DARK_GRAY);
        label.repaint();
        label.revalidate();
    }

    /* Scale icon sizes */
    private void scaleIcons() {
        for(int i = 0; i < specialIcons.length; i++) {
            specialIcons[i] = new ImageIcon(getClass().getResource(specialURLs[i]));
            Image temp = specialIcons[i].getImage();    
            int tempWidth = (int)(temp.getWidth(null)*(double)(this.getWidth()/450.00));
            int tempHeight = (int)(temp.getHeight(null)*(double)(this.getHeight()/450.0));
            if(specialURLs[i] == "/enter.png") {
                temp = temp.getScaledInstance((int)(tempWidth/20), (int)(tempHeight/20), Image.SCALE_SMOOTH);
            } else {
                temp = temp.getScaledInstance((int)(tempWidth/7), (int)(tempHeight/7), Image.SCALE_SMOOTH);
            }
            specialIcons[i].setImage(temp);
        }
    }

    /* Sets key text/image */
    private void loadKeysButtons() {
        // Left-side buttons
        for(int i = 0; i < specialIcons.length; i++) {
            specialKeys[i] = new JButton(specialIcons[i]);
        }
        specialKeys[3] = new JButton("\\");
        specialKeys[4] = new JButton("=");
        specialKeys[5] = new JButton("(");
        
        // Arithmetic
        arithmeticKeys[0] = new JButton("+");
        arithmeticKeys[1] = new JButton("-");
        arithmeticKeys[2] = new JButton(".");
        arithmeticKeys[3] = new JButton("*");
        arithmeticKeys[4] = new JButton("/");
        
        // Numbers
        for(int i = 0; i < numberKeys.length; i++) {
            numberKeys[i] = new JButton(String.valueOf(i));
        }
        
        // Layer 3
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
        
        // Layer 4
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
        
        // Letters
        for (int i = 0; i < 26; i++) {
            letterKeys[i] = new JButton("" + (char)(i + 'a'));
        }
        
        // Change size buttons
        changeSizeButtons[0] = new JButton("-");
        changeSizeButtons[1] = new JButton("+");
    }
    
    /* Sets up left-side buttons' listeners */
    // TODO: Adjust bounds to fit better (increase size, and if possible, shape)
    private void setSpecialListener() {
        for (int i = 0; i < 6; i++) {
            final Integer x = new Integer(i);
            if (i < 3) { // Backspace, space, enter
                specialKeys[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            Robot robot = new Robot();

                            switch(x) {
                            case 0: // Backspace
                                typeKey(KeyEvent.VK_BACK_SPACE);
                                predictionInput = removeLastChar(predictionInput);
                                predictionModel.predictSymbol(predictionInput);
                                break;

                            case 1: // Space
                                typeKey(KeyEvent.VK_SPACE);
                                isPredict = false;
                                predictionModel.predictSymbol("");
                                break;

                            case 2: // Enter
                                if (mathMode.isSelected()) { // Math mode
                                    typeKey(KeyEvent.VK_ENTER);
                                    robot.keyPress(KeyEvent.VK_ALT);
                                    typeKey(KeyEvent.VK_EQUALS);
                                    robot.keyRelease(KeyEvent.VK_ALT);
                                }
                                else { // Normal mode
                                    typeKey(KeyEvent.VK_ENTER);
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
            }
        }
    }

    /* Sets size and position for left-side buttons */
    private void scaleSpecial() {
        // Size
        for(int i = 3; i < 6; i++) {
            specialKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25*(double)(this.getWidth()/500.0))));
        }
        
        // Positioning
        int xValue, yValue;

        // Backspace
        xValue = (int)(this.getWidth()/2) - 10;
        yValue = (int)(this.getHeight()/2) - 35;
        specialKeys[0].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);

        // \
        xValue = (int)(this.getWidth()/2) - 55;
        yValue = (int)(this.getHeight()/2) - 35;
        specialKeys[3].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);

        // (1) Space, (2) enter
        double radian;
        double initDegree = 23;
        double incrementDegree = -40;

        int midX = (int)((this.getWidth())/2) - 20;
        int midY = (int)((this.getHeight())/2) - 32;
        int radius = (int)((this.getWidth())/4) - 15;

        for (int i = 1; i <= 2; i++) {
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1*(int)(Math.cos(radian)*radius) + midX;
            yValue = -1*(int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            specialKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        }

        // (4) =, (5) (
        initDegree = 23;
        incrementDegree = -40;
        radius = (int)((this.getWidth())/3) - 5;

        for (int i = 4; i <= 5; i++) {
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1*(int)(Math.cos(radian)*radius) + midX;
            yValue = -1*(int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            specialKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        }
    }

    /* Sets size and position of arithmetic buttons */
    private void scaleArithmetic() {
        double radian;
        int xValue, yValue;
        double initDegree = 65;
        double incrementDegree = 56;

        int midX = (int)((this.getWidth())/2) - 20;
        int midY = (int)((this.getHeight())/2) - 32;
        int radius = (int)((this.getWidth())/9) - 5;

        for (int i = 0; i < arithmeticKeys.length; i++) {          
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1*(int)(Math.cos(radian)*radius) + midX;
            yValue = -1*(int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            arithmeticKeys[i].setBounds(xValue, yValue, (int)(KEY_WIDTH*0.8), (int)(KEY_HEIGHT*0.8));
            arithmeticKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25*(double)(this.getWidth()/500.0))));
        }
    }
    
    /* Sets size and position of number buttons */
    // TODO: Scroll through numbers
    private void scaleNumber() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 28;

        int xValue, yValue;
        int midX = (int)((this.getWidth())/2) - 20;
        int midY = (int)((this.getHeight())/2) - 32;
        int radius = (int)((this.getWidth())/6);

        for (int i = 0; i < 10; i++) {
            // Calculates coordinates of each number
            radian = Math.toRadians(initDegree);
            xValue = -1*(int)(Math.cos(radian)*radius) + midX;
            yValue = -1*(int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            numberKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            numberKeys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25*(double)(this.getWidth()/500.0))));
        }
    }
    
    /* Sets up letter buttons */
    // TODO: Seperate into two functions (load and scale)
    private void loadAndScaleLetters() {
        double radian;
        double initDegree = 50;
        double incrementDegree = 10.3;

        int xValue, yValue;
        int midX = (int) ((this.getWidth())/2) - 20;
        int midY = (int) ((this.getHeight())/2) - 35;
        int radius = (int) ((this.getWidth())/2.5) - 13;

        for (int i = 0; i < letterKeys.length; i++) {
            char temp = (char) ('a' + i);
            // letterKeys[i] = new JButton(Character.toString(temp));
            char lowercase = (char) ('a' + i);
            // String lowerTemp = Character.toString(lowercase);
            char uppercase = (char) ('A' + i);
            // String upperTemp = Character.toString(uppercase);

            // Calculates coordinates of each letter
            radian = Math.toRadians(initDegree);
            xValue = -1*(int) (Math.cos(radian)*radius) + midX;
            yValue = -1*(int) (Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            letterKeys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            letterKeys[i].setFont(new Font("Arial", Font.PLAIN, (int) (25*(double) (this.getWidth()/500.0))));
            letterKeys[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        Robot robot = new Robot();

                        if (!shiftClick && !capsClick) { // Lower case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (lowercase));
                            typeKey(keyCode);
                        } else if (!shiftClick && capsClick) { // Upper case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (uppercase));
                            shiftKey(keyCode);
                        } else if (shiftClick && !capsClick) { // Upper case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (uppercase));
                            shiftKey(keyCode);
                            shiftClick = false;
                        } else { // Lower case
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) (lowercase));
                            typeKey(keyCode);
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
        }
    }

    /* Sets size and position layer 3 buttons */    
    private void scaleLayer3() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 23;

        int xValue, yValue;
        int midX = (int) ((this.getWidth())/2) - 20;
        int midY = (int) ((this.getHeight())/2) - 35;
        int radius = (int) ((this.getWidth())/4) - 6;

        for (int i = 0; i < layer3Keys.length; i++) {
            radian = Math.toRadians(initDegree);
            xValue = -1*(int) (Math.cos(radian)*radius) + midX;
            yValue = -1*(int) (Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            layer3Keys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            layer3Keys[i].setFont(new Font("Arial", Font.PLAIN, (int) (25*(double) (this.getWidth()/500.0))));
        }
    }

    /* Sets size and position of layer 4 buttons */
    // TODO: Add shift and caps in the middle
    private void scaleLayer4() {
        double radian;
        double initDegree = 55;
        double incrementDegree = 23;

        int xValue, yValue;
        int midX = (int)((this.getWidth())/2) - 20;
        int midY = (int)((this.getHeight())/2) - 32;
        int radius = (int)((this.getWidth())/3) - 15;

        for (int i = 0; i < layer4Keys.length; i++) {
            // Calculates coordinates of each layer4
            radian = Math.toRadians(initDegree);
            xValue = -1*(int)(Math.cos(radian)*radius) + midX;
            yValue = -1*(int)(Math.sin(radian)*radius) + midY;
            initDegree += incrementDegree;

            layer4Keys[i].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
            layer4Keys[i].setFont(new Font("Arial", Font.PLAIN, (int)(25*(double)(this.getWidth()/500.0))));
        }
    }

    /* set up change size buttons, which changes size as person presses the button
     * TODO: Special icons not scaling, and fix positioning/bounds of keys
     * TODO: Change background by 1) scaling OR 2) changing background with an int to keep track
     *       with a min/max size (default is min)
     */
    private void scaleChangeSize(){
        int xValue = (int)(this.getWidth()*0.8);
        int yValue = (int)(this.getHeight()*0.8);

        for (int i=0; i<2; i++){
            changeSizeButtons[i].setBorder(BorderFactory.createBevelBorder(10, Color.red, Color.gray));
            changeSizeButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25*(double)(this.getWidth()/500.0))));
        }
        changeSizeButtons[0].setBounds(xValue, yValue, KEY_WIDTH, KEY_HEIGHT);
        changeSizeButtons[1].setBounds(xValue+KEY_WIDTH, yValue, KEY_WIDTH, KEY_HEIGHT);

    }

    // TODO: Change key width, key height
    private void scaleKeys(){
        scaleSpecial();
        scaleArithmetic();
        scaleNumber();
        scaleLayer3();
        scaleLayer4();
        loadAndScaleLetters();
        scaleChangeSize();
    }

    private void loadGUI() {
        // Graphics
        loadBackground();
        scaleIcons();
        loadKeysButtons();

        // Size and position
        setSpecialListener();
        scaleSpecial();
        scaleArithmetic();
        scaleNumber();
        scaleLayer3();
        scaleLayer4();
        loadAndScaleLetters();

        for(int i = 0; i < keys.length; i++) {
            for(int j = 0; j < keys[i].length; j++) {
                if(i > 0) {
                    if(i < 5) {
                        keys[i][j].removeActionListener(numericSymbolicListener);
                        keys[i][j].addActionListener(numericSymbolicListener);
                    }
                    keys[i][j].setForeground(Color.WHITE);
                }
                keys[i][j].setBorder(null);
                keys[i][j].setBorderPainted(false);
                keys[i][j].setContentAreaFilled(false);
                keys[i][j].setOpaque(false);
                keys[i][j].removeMouseListener(keyHighlightAdapter);
                addKeyHighlightAdapter(keys[i][j]);
                panel.add(keys[i][j]);
            }
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
                    
//                    isPredict = false;
//                    predictionInput = "";
//                    predictionModel.predictSymbol("");
                }
            }
        });
        panel.add(mathMode);

        //change size buttons
        scaleChangeSize();
        for (int i = 0; i < 2; i++){
            final Integer x = new Integer(i);
            changeSizeButtons[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked (MouseEvent e) {
                    if (x == 0) { //If smaller
                        if(currScaleCount != 0) {                       
                            setSize((int)(getWidth()/SCALE_FACTOR),(int)(getHeight()/SCALE_FACTOR));
                            scaleKeys();
                            if(currScaleCount == 1) {
                                background = new ImageIcon(getClass().getResource("/bg.png"));
                            } else {
                                background = new ImageIcon(getClass().getResource("/bg3.png"));
                            }
                            currScaleCount--;
                        }
                    } else { //If larger
                        if(currScaleCount != MAX_SCALE_COUNT) {
                            setSize((int)(getWidth()*SCALE_FACTOR),(int)(getHeight()*SCALE_FACTOR));
                            scaleKeys();
                            if(currScaleCount == 1) {
                                background = new ImageIcon(getClass().getResource("/bg4.png"));
                            } else {
                                background = new ImageIcon(getClass().getResource("/bg3.png"));   
                            }
                            currScaleCount++;
                        }
                    }
                }
            });
            panel.add(changeSizeButtons[i]);
        }

        panel.add(label);
        panel.revalidate();
        panel.repaint();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    public Keyboard(String title) {
        super(title);
        loadGUI();
        predictionModel = new PredictionModel("Prediction List");    
    }
}