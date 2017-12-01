package soft.keyboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.*;
import javax.swing.*;

import soft.helper.Helper;

/**
 * <h1>Keyboard Frame</h1>
 * <p>The <b>Keyboard Frame</b> class handles the keyboard UI.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @author  Jin Young Park
 * @since   0.0
 */
@SuppressWarnings("serial")
public class KeyboardFrame extends JFrame {
    // Frame variables
    private static KeyboardFrame keyboardFrame = null;
    private int width = 450;
    private int height = 420;
    private JPanel panel;
    private JLabel label;
    private String backgroundPath = "/backgrounds/bg.png";
    private ImageIcon background = new ImageIcon(getClass().getResource(backgroundPath));

    /**
     * Private constructor for singleton.
     */
    private KeyboardFrame() {
        super("Soft Keyboard");
        loadGUI();
    }

    /**
     * Private constructor for singleton.
     * @param w frame width.
     * @param h frame height.
     */
    private KeyboardFrame(int w, int h) {
        super("Soft Keyboard");
        this.width = w;
        this.height = h;
        loadGUI();
    }

    /**
     * Instantiates Keyboard Frame singleton when called for the first time.
     * @return Keyboard Frame singleton.
     */
    public static KeyboardFrame getInstance() {
        if (keyboardFrame == null) {
            keyboardFrame = new KeyboardFrame();
        }
        return keyboardFrame;
    }

    /**
     * Sets up background.
     */
    private void loadBackground() {
        // Frame
        this.pack();
        this.setVisible(true);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
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
        label.setBackground(Color.DARK_GRAY);
        label.repaint();
        label.revalidate();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Key variables    

    private static class Key {
        public static ArrayList<ArrayList<String>> mapping;
        public static int width = 25;
        public static int height = 25;
    }

    private class Row {
        public ArrayList<JButton> keys;
        public double initDegree;
        public double endDegree;
        public int midX = width/2 - 22;
        public int midY = height/2 - 40;
        public int radius;
        public int rowNum;

        public Row(int rowNum) {
            this.rowNum = rowNum;
        }
    }

    private Row[] rows = new Row[9];
    private ImageIcon[] icons = new ImageIcon[5];
    private String[] iconURLs = {"/icons/backspace.png", "/icons/space.png", "/icons/enter.png", "/icons/shift.png", "/icons/caps.png"};
    private boolean shiftClick = false;
    private boolean capsClick = false;
    private MouseAdapter keyHighlightMouseAdapter;
    private ActionListener letterActionListener;
    private ActionListener specialActionListener;
    private ActionListener unicodeActionListener;

    /**
     * Plays sound.
     * @param soundPath the filepath of the sound to be played.
     */
    private void playSound(String soundPath) {
        try {
            AudioInputStream tempAudioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundPath));
            Clip tempClip = AudioSystem.getClip();
            tempClip.open(tempAudioIn);
            tempClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Highlights key background when cursor is hovering over it.
     * @param b the button to add the MouseAdapter to.
     */
    private void addKeyHighlightAdapter(JButton b) {
        keyHighlightMouseAdapter = new MouseAdapter() {
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

        b.removeMouseListener(keyHighlightMouseAdapter);
        b.addMouseListener(keyHighlightMouseAdapter);
    }

    /**
     * Toggles letters between uppercase and lowercase.
     */
    private void toggleLetters() {
        for (Row row : rows) {
            for (JButton key : row.keys) {
                char letter = key.getName().charAt(0);

                if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) {
                    if (!shiftClick && !capsClick) { // Lower case
                        key.setText("" + letter);
                    } else if (!shiftClick && capsClick) { // Upper case
                        key.setText("" + Character.toUpperCase(letter));
                    } else if (shiftClick && !capsClick) { // Upper case
                        key.setText("" + Character.toUpperCase(letter));
                    } else { // Lower case
                        key.setText("" + Character.toLowerCase(letter));
                    }
                }
            }
        }
    }

    /**
     * Action listener for letter keys that handles uppercase/lowercase letters.
     */
    private void addLetterActionListener(JButton b) {
        char letter = b.getName().charAt(0);

        letterActionListener = (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Deal with letter case
                if (!shiftClick && !capsClick) { // Caps lower case
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
                    Helper.typeKey(keyCode);
                } else if (!shiftClick && capsClick) { // Caps upper case
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
                    Helper.shiftKey(keyCode);
                } else if (shiftClick && !capsClick) { // Shift upper case
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
                    Helper.shiftKey(keyCode);
                    shiftClick = false;
                    toggleLetters();
                } else { // Shift lower case
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
                    Helper.typeKey(keyCode);
                    shiftClick = false;
                    toggleLetters();
                }

                // Add sounds
                String tempPath = "/sounds/letters/" + Character.toLowerCase(letter) + ".wav";
                playSound(tempPath);
            }
        });

        b.removeActionListener(letterActionListener);
        b.addActionListener(letterActionListener);
    }

    /**
     * Converts Soft Keyboard non-alphabetical key input into actual keyboard input.
     */
    private ActionListener numericSymbolicActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();

            // Numbers
            if (actionCommand.equals("0")) {Helper.typeKey(KeyEvent.VK_0);}
            else if (actionCommand.equals("1")) {Helper.typeKey(KeyEvent.VK_1);}
            else if (actionCommand.equals("2")) {Helper.typeKey(KeyEvent.VK_2);}
            else if (actionCommand.equals("3")) {Helper.typeKey(KeyEvent.VK_3);}
            else if (actionCommand.equals("4")) {Helper.typeKey(KeyEvent.VK_4);}
            else if (actionCommand.equals("5")) {Helper.typeKey(KeyEvent.VK_5);}
            else if (actionCommand.equals("6")) {Helper.typeKey(KeyEvent.VK_6);}
            else if (actionCommand.equals("7")){Helper.typeKey(KeyEvent.VK_7);}
            else if (actionCommand.equals("8")) {Helper.typeKey(KeyEvent.VK_8);}
            else if (actionCommand.equals("9")) {Helper.typeKey(KeyEvent.VK_9);}

            // Symbols
            else if (actionCommand.equals("`")) {Helper.typeKey(KeyEvent.VK_BACK_QUOTE);}
            else if (actionCommand.equals("~")) {Helper.shiftKey(KeyEvent.VK_BACK_QUOTE);}
            else if (actionCommand.equals("!")) {Helper.shiftKey(KeyEvent.VK_1);}
            else if (actionCommand.equals("@")) {Helper.shiftKey(KeyEvent.VK_2);}
            else if (actionCommand.equals("#")) {Helper.shiftKey(KeyEvent.VK_3);}
            else if (actionCommand.equals("$")) {Helper.shiftKey(KeyEvent.VK_4);}
            else if (actionCommand.equals("%")) {Helper.shiftKey(KeyEvent.VK_5);}
            else if (actionCommand.equals("^")) {Helper.shiftKey(KeyEvent.VK_6);}
            else if (actionCommand.equals("&")) {Helper.shiftKey(KeyEvent.VK_7);}
            else if (actionCommand.equals("*")) {Helper.shiftKey(KeyEvent.VK_8);}                
            else if (actionCommand.equals(")")) {Helper.shiftKey(KeyEvent.VK_0);}
            else if (actionCommand.equals("-")) {Helper.typeKey(KeyEvent.VK_MINUS);}
            else if (actionCommand.equals("_")) {Helper.shiftKey(KeyEvent.VK_MINUS);}
            else if (actionCommand.equals("=")) {
                Helper.typeKey(KeyEvent.VK_EQUALS);
                if (mathModeButton.isSelected()) {
                    Helper.typeKey(KeyEvent.VK_SPACE);
                }     
            }
            else if (actionCommand.equals("+")) {Helper.shiftKey(KeyEvent.VK_EQUALS);}
            else if (actionCommand.equals("[")) {Helper.typeKey(KeyEvent.VK_OPEN_BRACKET);}
            else if (actionCommand.equals("{")) {Helper.shiftKey(KeyEvent.VK_OPEN_BRACKET);}
            else if (actionCommand.equals("]")) {Helper.typeKey(KeyEvent.VK_CLOSE_BRACKET);}
            else if (actionCommand.equals("}")) {Helper.shiftKey(KeyEvent.VK_CLOSE_BRACKET);}
            else if (actionCommand.equals("\\")) {Helper.typeKey(KeyEvent.VK_BACK_SLASH);}
            else if (actionCommand.equals("|")) {Helper.shiftKey(KeyEvent.VK_BACK_SLASH);}
            else if (actionCommand.equals(";")) {Helper.typeKey(KeyEvent.VK_SEMICOLON);}
            else if (actionCommand.equals(":")) {Helper.shiftKey(KeyEvent.VK_SEMICOLON);}
            else if (actionCommand.equals("'")) {Helper.typeKey(KeyEvent.VK_QUOTE);}
            else if (actionCommand.equals("\"")) {Helper.shiftKey(KeyEvent.VK_QUOTE);}
            else if (actionCommand.equals(",")) {Helper.typeKey(KeyEvent.VK_COMMA);}
            else if (actionCommand.equals("<")) {Helper.shiftKey(KeyEvent.VK_COMMA);}
            else if (actionCommand.equals(".")) {Helper.typeKey(KeyEvent.VK_PERIOD);}
            else if (actionCommand.equals(">")) {Helper.shiftKey(KeyEvent.VK_PERIOD);}
            else if (actionCommand.equals("/")) {Helper.typeKey(KeyEvent.VK_SLASH);}
            else if (actionCommand.equals("?")) {Helper.shiftKey(KeyEvent.VK_SLASH);}

            // Special case
            else if (actionCommand.equals("(")) { // Autocompletes ), then puts cursor between ( and )
                if (mathModeButton.isSelected()) {
                    Helper.shiftKey(KeyEvent.VK_9); // (
                    Helper.shiftKey(KeyEvent.VK_0); // )
                    Helper.typeKey(KeyEvent.VK_LEFT); // Go between ( and )
                } else {
                    Helper.shiftKey(KeyEvent.VK_9);
                }
            }
            
            // Add sounds
            // Numbers
            try {
            if (Integer.parseInt(actionCommand) >= 0 && Integer.parseInt(actionCommand) <= 9) {
                playSound("/sounds/numbers/" + actionCommand + ".wav");
            }
            } catch (NumberFormatException e) {
                // Special cases
                if (actionCommand.equals("*")) { playSound("/sounds/symbols/asterisk.wav"); }
                else if (actionCommand.equals("/")) { playSound("/sounds/symbols/back slash.wav"); }
                else if (actionCommand.equals(":")) { playSound("/sounds/symbols/colon.wav"); }
                else if (actionCommand.equals("\\")) { playSound("/sounds/symbols/forward slash.wav"); }
                else if (actionCommand.equals("<")) { playSound("/sounds/symbols/left arrow.wav"); }
                else if (actionCommand.equals("|")) { playSound("/sounds/symbols/pipe.wav"); }
                else if (actionCommand.equals("?")) { playSound("/sounds/symbols/question mark.wav"); }
                else if (actionCommand.equals("\"")) { playSound("/sounds/symbols/quote.wav"); }
                else if (actionCommand.equals(">")) { playSound("/sounds/symbols/right arrow.wav"); }

                else {
                    playSound("/sounds/symbols/" + actionCommand + ".wav");
                }
            }
        }
    };

    /**
     * Sets listeners for non-numeric/symbolic/alphabetic keys.
     * @param b the button to add the ener to.
     */
    private void addSpecialActionListener(JButton b) {
        if (b.getName().contains("backspace")) {
            specialActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Helper.typeKey(KeyEvent.VK_BACK_SPACE);
                    playSound("/sounds/icons/backspace.wav");
                }
            };
        } else if (b.getName().contains("space")) {
            specialActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Helper.typeKey(KeyEvent.VK_SPACE);
                    playSound("/sounds/icons/space.wav");
                }
            };
        } else if (b.getName().contains("enter")) {
            specialActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (mathModeButton.isSelected()) { // Math mode
                        try {
                            Robot robot = new Robot();
                            Helper.typeKey(KeyEvent.VK_ENTER);
                            robot.keyPress(KeyEvent.VK_ALT);
                            Helper.typeKey(KeyEvent.VK_EQUALS);
                            robot.keyRelease(KeyEvent.VK_ALT);
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    } else { // Normal mode
                        Helper.typeKey(KeyEvent.VK_ENTER);
                    }
                    playSound("/sounds/icons/enter.wav");
                }
            };
        } else if (b.getName().contains("shift")) {
            specialActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    shiftClick = !shiftClick;
                    toggleLetters();
                    playSound("/sounds/icons/shift.wav");
                }
            };
        } else if (b.getName().contains("caps")) {
            specialActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    capsClick = !capsClick;
                    toggleLetters();
                    playSound("/sounds/icons/caps.wav");
                }  
            };
        }

        b.removeActionListener(specialActionListener);
        b.addActionListener(specialActionListener);
    }

    /**
     * Type Unicode characters.
     */
    private void addUnicodeActionListener(JButton b) {
        unicodeActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Helper.typeUnicode(b.getName());
            }  
        };

        b.removeActionListener(unicodeActionListener);
        b.addActionListener(unicodeActionListener);
    }

    /**
     * Scales icon sizes.
     */
    private void scaleKeyIcons() {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new ImageIcon(getClass().getResource(iconURLs[i]));
            Image temp = icons[i].getImage();    
            int tempWidth = (int)(temp.getWidth(null)*(double)(width/450.00));
            int tempHeight = (int)(temp.getHeight(null)*(double)(height/450.0));
            int scale = 7;
            if (iconURLs[i] == "/icons/enter.png" || iconURLs[i] == "/icons/shift.png") {
                scale = 20;
            } else if (iconURLs[i] == "/icons/caps.png") {
                scale = 5;
            } else {
                scale = 7;
            }
            temp = temp.getScaledInstance((int)(tempWidth/scale), (int)(tempHeight/scale), Image.SCALE_SMOOTH);
            icons[i].setImage(temp);
        }
    }

    /**
     * Loads key mapping from a text file,
     * and stores it in <code>Key.mapping</code>.
     * @param path key mapping file location.
     */
    private ArrayList<ArrayList<String>> getKeyMapping(String path) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<%") || line.isEmpty()) {
                    continue;
                }

                String[] tempArray = line.split("\\s+");
                ArrayList<String> tempArrayList = new ArrayList<String>();
                for (String item : tempArray) {
                    tempArrayList.add(item);
                }
                result.add(tempArrayList);
            }

            System.out.println("Mapping:");
            for (ArrayList<String> row : result) {
                for (String item : row) {
                    System.out.print(item + " ");
                }
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Loads keys' appropriate letters or icons.
     */
    private void loadKeyIcons() {
        Key.mapping = getKeyMapping("/keymap.txt");
        int rowIndex = 0;
        for (ArrayList<String> row : Key.mapping) {
            if (rowIndex <= 5) {
                rows[rowIndex] = new Row(rowIndex + 1);
            } else {
                rows[rowIndex] = new Row(6 - rowIndex);
            }        
            rows[rowIndex].keys = new ArrayList<JButton>();

            for (String item : row) {
                String name = item;
                String url = "/icons/" + item + ".png";
                if(!Arrays.asList(iconURLs).contains(url)) {
                    if (item.length() == 1) {
                        rows[rowIndex].keys.add(new JButton(item));
                    }
                    else if (item.contains("\\u")) {
                        String unicode = Helper.unicodeUnescape(item);
                        rows[rowIndex].keys.add(new JButton(unicode));
                    }
                } else {
                    name = url;
                    int index = Arrays.asList(iconURLs).indexOf(name);
                    rows[rowIndex].keys.add(new JButton(icons[index]));
                }
                rows[rowIndex].keys.get(rows[rowIndex].keys.size() - 1).setName(name);
            }
            rowIndex++;
        }
    }

    /**
     * Loads keys' appropriate listeners.
     */
    private void loadKeyListeners() {
        for (Row row : rows) {
            for (JButton key : row.keys) {
                addKeyHighlightAdapter(key);

                if (key.getName().contains(".png")) {
                    addSpecialActionListener(key);
                } else if (key.getName().contains("\\u")) {
                    addUnicodeActionListener(key);
                } else {
                    char letter = key.getName().charAt(0);
                    if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) {
                        addLetterActionListener(key);
                    } else {
                        key.removeActionListener(numericSymbolicActionListener);
                        key.addActionListener(numericSymbolicActionListener);
                    }
                }
            }
        }
    }

    /**
     * Loads keys' appropriate positions.
     */
    private void scaleKeyPositions() {
        // Right side
        rows[0].radius = -10;
        rows[0].initDegree = 0;
        rows[0].endDegree = 0;
        for (int i = 1; i <= 5; i++) {
            rows[i].radius = width/10 + 30*(i - 1) + 5*currScaleCount*(i - 1);
            rows[i].initDegree = 52;
            rows[i].endDegree = 308;
        }
        System.out.println("Radius: " + rows[5].radius);

        // Left side
        rows[6].radius = 48;
        rows[6].initDegree = 0;
        rows[6].endDegree = 0;
        for (int i = 7; i <= 8; i++) {
            rows[i].radius = width/4 - 15 + 60*(i - 7);
            rows[i].initDegree = -25;
            rows[i].endDegree = 25;
        }

        // Place keys
        for (Row row : rows) {
            double radian = Math.toRadians(row.initDegree);
            double incrementDegree = Math.toRadians((row.endDegree - row.initDegree)/(row.keys.size() - 1));
            for (JButton key : row.keys) {
                int x = -1*(int)(Math.cos(radian)*row.radius) + row.midX;
                int y = -1*(int)(Math.sin(radian)*row.radius) + row.midY;
                radian += incrementDegree;
                key.setBounds(x, y, Key.width, Key.height);
                key.setFont(new Font("Arial Unicode MS", Font.BOLD, (int)(25*width/500.0)));
            }
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////  

    // Math Mode variables
    private JToggleButton mathModeButton = new JToggleButton("Normal Mode", false);

    /**
     * Loads Math Mode button.
     */
    private void loadMathModeButton() {
        mathModeButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) { // In Math Mode
                    System.out.println("Math Mode");
                    mathModeButton.setText("Math Mode");
                    playSound("/sounds/buttons/math mode.wav");
                } else { // In Normal Mode
                    System.out.println("Normal Mode");
                    mathModeButton.setText("Normal Mode");
                    playSound("/sounds/buttons/normal mode.wav");
                }
            }
        });
    }

    /**
     * Scales Math Mode button.
     */
    private void scaleMathModeButton() {
        int mathButtonWidth = 110 + currScaleCount*20;
        int mathButtonHeight = 30 + currScaleCount*5;

        mathModeButton.setBounds(0, 0, mathButtonWidth, mathButtonHeight);
        mathModeButton.setFont(new Font("Arial", Font.BOLD, (int)(14*getWidth()/500.0)));
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////  

    // Change Size variables
    private final double SCALE_FACTOR = 1.15;
    private int currScaleCount = 0;
    private final int MAX_SCALE_COUNT = 2;
    private JButton[] changeSizeButtons = new JButton[2];

    /**
     * Sets key text/image.
     */
    private void loadChangeSizeButtons() {
        changeSizeButtons[0] = new JButton("-");
        changeSizeButtons[1] = new JButton("+");

        for (int i = 0; i < 2; i++) {
            final Integer x = new Integer(i);
            changeSizeButtons[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked (MouseEvent e) {
                    if (x == 0) { //If decreasing size
                        if (currScaleCount != 0) {  
                            currScaleCount--;
                            setSize((int)(width/SCALE_FACTOR) + 1, (int)(height/SCALE_FACTOR) + 1);
                            Key.width -= 5;
                            Key.height -= 5;
                            width = getWidth();
                            height = getHeight();
                            for (Row row : rows) {
                                row.midX = width/2 - 22;
                                row.midY = height/2 - 40;
                            }
                            Image temp = new ImageIcon(getClass().getResource(backgroundPath)).getImage();
                            background.setImage(Helper.getScaledImage(temp, width - 50, width - 50));
                            scaleKeys();
                            
                            playSound("/sounds/buttons/decrease size.wav");
                        }
                    } else { //If increasing size
                        if (currScaleCount != MAX_SCALE_COUNT) {
                            currScaleCount++;
                            setSize((int)(width*SCALE_FACTOR),(int)(height*SCALE_FACTOR));
                            Key.width += 5;
                            Key.height += 5;
                            width = getWidth();
                            height = getHeight();
                            for (Row row : rows) {
                                row.midX = width/2 - 22;
                                row.midY = height/2 - 40;
                            }
                            Image temp = new ImageIcon(getClass().getResource(backgroundPath)).getImage();
                            background.setImage(Helper.getScaledImage(temp, width - 50, width - 50));
                            scaleKeys();
                            
                            playSound("/sounds/buttons/increase size.wav");
                        }
                    }
                }
            });
            panel.add(changeSizeButtons[i]);
        }
    }

    /**
     * Scales change size buttons, which changes size as person presses the button.
     */
    private void scaleChangeSizeButtons() {
        int xValue = (int)(width*0.8);
        int yValue = (int)(height*0.8);

        for (int i = 0; i < 2; i++) {
            changeSizeButtons[i].setBorder(BorderFactory.createBevelBorder(10, Color.red, Color.gray));
            changeSizeButtons[i].setFont(new Font("Arial", Font.BOLD, (int)(25*this.getWidth()/500.0)));
        }
        changeSizeButtons[0].setBounds(xValue, yValue, Key.width, Key.height);
        changeSizeButtons[1].setBounds(xValue + Key.width, yValue, Key.width, Key.height);

    }

    /**
     * Scales all the keys based on frame dimensions.
     */
    private void scaleKeys() {
        System.out.println("Width = " + width);
        System.out.println("Height = " + height);

        scaleKeyPositions();
        scaleChangeSizeButtons();
        scaleMathModeButton();
    }

    /**
     * Builds the GUI.
     */
    private void loadGUI() {
        // Graphics
        loadBackground();
        scaleKeyIcons();
        loadKeyIcons();
        loadKeyListeners();
        scaleKeyPositions();

        for (Row row : rows) {
            for (JButton key : row.keys) {
                if (row.rowNum > 0) {
                    key.setForeground(Color.WHITE);
                }
                key.setBorder(null);
                key.setBorderPainted(false);
                key.setContentAreaFilled(false);
                key.setOpaque(false);
                panel.add(key);
            }
        }

        // Add mode toggle button
        loadMathModeButton();
        scaleMathModeButton();
        panel.add(mathModeButton);

        //change size buttons
        System.out.println("Width = " + width);
        System.out.println("Height = " + height);
        loadChangeSizeButtons();
        scaleChangeSizeButtons();

        panel.add(label);
        panel.revalidate();
        panel.repaint();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }
}