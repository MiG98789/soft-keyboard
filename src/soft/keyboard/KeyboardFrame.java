package soft.keyboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.*;
import javax.swing.*;

import soft.helper.Helper;

/**
 * <h1>Keyboard Frame</h1>
 * <p>The <b>Keyboard Frame</b> class handles the keyboard UI.</p>
 * 
 * @author  Gian Miguel Sero Del Mundo
 * @author  Jin Young Park
 */
@SuppressWarnings("serial")
public class KeyboardFrame extends JFrame {
    // Frame variables
    private static KeyboardFrame keyboardFrame = null;
    private final double DEFAULT_WIDTH = 450.0;
    private final double DEFAULT_HEIGHT = 420.0;
    private int width = 450;
    private int height = 420;
    private JPanel panel;
    private JLabel label;
    private String backgroundPath = "/backgrounds/bbg_alt.png";
    private ImageIcon background = new ImageIcon(getClass().getResource(backgroundPath));
    private boolean isStart = true;

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
    private KeyboardFrame(int width, int height) {
        super("Soft Keyboard");
        this.width = width;
        this.height = height;
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
        label.revalidate();
        label.repaint();
    }

    /**
     * Builds the GUI.
     */
    private void loadGUI() {
        // Only have to carry out load functions at startup
        if (isStart) {
            isStart = false;
            
            scaleKeyIcons();
            loadKeyIcons();
            loadKeyListeners();
            loadBackground();
            loadChangeBackgroundButton();
            loadModeButton();
            loadChangeSizeButtons();
        } else {
            panel.removeAll();
        }

        // Keys
        scaleKeyIcons();
        scaleKeyPositions();

        Row currRows[] = rows.get(getCurrentMode());
        for (Row row : currRows) {
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

        // Add change background button
        scaleChangeBackgroundButton();
        panel.add(changeBackgroundButton);

        // Add mode toggle button
        scaleModeButton();
        panel.add(modeButton);

        //change size buttons
        scaleChangeSizeButtons();
        panel.add(changeSizeButtons[0]);
        panel.add(changeSizeButtons[1]);

        panel.add(label);
        panel.revalidate();
        panel.repaint();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Plays sound.
     * @param soundPath the filepath of the sound to be played.
     */
    private void playSound(String soundPath) {
        try {
            AudioInputStream tempAudioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundPath));
            Clip tempClip = AudioSystem.getClip();
            tempClip.addLineListener(event -> {
                if(LineEvent.Type.STOP.equals(event.getType())) {
                    tempClip.close();
                }
            });
            tempClip.open(tempAudioIn);
            tempClip.start();
        } catch (Exception e) {
            e.printStackTrace();
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

    // Key variables    

    private static class Key {
        /* Currently have 3 keymaps
         * 1) Normal Keymap
         * 2) Math Keymap
         * 3) Greek Keymap
         * 
         * First (innermost) ArrayList: row of letters
         * Second ArrayList: rows of keymap
         * Third (outermost) HashMap: name of keymap, all keymaps
         */ 
        public static HashMap<String, ArrayList<ArrayList<String>>> keymaps;
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

    private HashMap<String, Row[]> rows = new HashMap<String, Row[]>();
    private final int ROW_COUNT = 9;
    private ImageIcon[] icons = new ImageIcon[6];
    private String[] iconURLs = { 
            "/icons/white backspace.png",
            "/icons/backspace.png",
            "/icons/space.png",
            "/icons/enter.png",
            "/icons/shift.png",
            "/icons/caps.png" 
    };
    private String[] keymapURLs = {
            "/keymaps/normal.txt",
            "/keymaps/math.txt",
            "/keymaps/greek.txt"
    };
    private boolean shiftClick = false;
    private boolean capsClick = false;
    private MouseAdapter keyHighlightMouseAdapter;
    private ActionListener letterActionListener; // a - z
    private ActionListener numberSymbolActionListener; // keyboard symbols
    private ActionListener iconActionListener; // backspace, shift, etc.
    private ActionListener equationActionListener; // Equation Editor symbols

    /**
     * Loads key mapping from a text file.
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
     * Toggles letters between uppercase and lowercase.
     */
    private void toggleLetterCase() {
        for (Row[] rowGroup : rows.values()) {
            for (Row row : rowGroup) {
                for (JButton key : row.keys) {
                    if (key.getName().length() != 1) {
                        continue;
                    }
    
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
     * Action listener for letter keys. 
     * Handles uppercase/lowercase letters.
     * @param b the letter button.
     */
    private void addLetterActionListener(JButton b) {
        char letter = b.getName().charAt(0);

        letterActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Deal with letter case
                int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(letter));
                if (!shiftClick && !capsClick) { // Caps lower case
                    Helper.typeKey(keyCode);
                } else if (!shiftClick && capsClick) { // Caps upper case
                    Helper.shiftKey(keyCode);
                } else if (shiftClick && !capsClick) { // Shift upper case
                    Helper.shiftKey(keyCode);
                    shiftClick = false;
                    toggleLetterCase();
                } else { // Shift lower case
                    Helper.typeKey(keyCode);
                    shiftClick = false;
                    toggleLetterCase();
                }

                // Add sounds
                String soundPath = "/sounds/letters/" + Character.toLowerCase(letter) + ".wav";
                playSound(soundPath);
            }
        };

        b.removeActionListener(letterActionListener);
        b.addActionListener(letterActionListener);
    }

    /**
     * Converts Soft Keyboard non-alphabetical (i.e. numbers/symbols) key input into actual keyboard input.
     */
    private void addNumberSymbolActionListener(JButton b) {
        numberSymbolActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String actionCommand = event.getActionCommand();

                // Numbers
                if (actionCommand.equals("0")) { Helper.typeKey(KeyEvent.VK_0); }
                else if (actionCommand.equals("1")) { Helper.typeKey(KeyEvent.VK_1); }
                else if (actionCommand.equals("2")) { Helper.typeKey(KeyEvent.VK_2); }
                else if (actionCommand.equals("3")) { Helper.typeKey(KeyEvent.VK_3); }
                else if (actionCommand.equals("4")) { Helper.typeKey(KeyEvent.VK_4); }
                else if (actionCommand.equals("5")) { Helper.typeKey(KeyEvent.VK_5); }
                else if (actionCommand.equals("6")) { Helper.typeKey(KeyEvent.VK_6); }
                else if (actionCommand.equals("7")){ Helper.typeKey(KeyEvent.VK_7); }
                else if (actionCommand.equals("8")) { Helper.typeKey(KeyEvent.VK_8); }
                else if (actionCommand.equals("9")) { Helper.typeKey(KeyEvent.VK_9); }

                // Symbols
                else if (actionCommand.equals("`")) { Helper.typeKey(KeyEvent.VK_BACK_QUOTE); }
                else if (actionCommand.equals("~")) { Helper.shiftKey(KeyEvent.VK_BACK_QUOTE); }
                else if (actionCommand.equals("!")) { Helper.shiftKey(KeyEvent.VK_1); }
                else if (actionCommand.equals("@")) { Helper.shiftKey(KeyEvent.VK_2); }
                else if (actionCommand.equals("#")) { Helper.shiftKey(KeyEvent.VK_3); }
                else if (actionCommand.equals("$")) { Helper.shiftKey(KeyEvent.VK_4); }
                else if (actionCommand.equals("%")) { Helper.shiftKey(KeyEvent.VK_5); }
                else if (actionCommand.equals("^")) { Helper.shiftKey(KeyEvent.VK_6); }
                else if (actionCommand.equals("&")) { Helper.shiftKey(KeyEvent.VK_7); }
                else if (actionCommand.equals("*")) { Helper.shiftKey(KeyEvent.VK_8); }                
                else if (actionCommand.equals(")")) { Helper.shiftKey(KeyEvent.VK_0); }
                else if (actionCommand.equals("-")) { Helper.typeKey(KeyEvent.VK_MINUS); }
                else if (actionCommand.equals("_")) { Helper.shiftKey(KeyEvent.VK_MINUS); }
                else if (actionCommand.equals("=")) { Helper.typeKey(KeyEvent.VK_EQUALS);}
                else if (actionCommand.equals("+")) { Helper.shiftKey(KeyEvent.VK_EQUALS); }
                else if (actionCommand.equals("[")) { Helper.typeKey(KeyEvent.VK_OPEN_BRACKET); }
                else if (actionCommand.equals("{")) { Helper.shiftKey(KeyEvent.VK_OPEN_BRACKET); }
                else if (actionCommand.equals("]")) { Helper.typeKey(KeyEvent.VK_CLOSE_BRACKET); }
                else if (actionCommand.equals("}")) { Helper.shiftKey(KeyEvent.VK_CLOSE_BRACKET); }
                else if (actionCommand.equals("\\")) { Helper.typeKey(KeyEvent.VK_BACK_SLASH); }
                else if (actionCommand.equals("|")) { Helper.shiftKey(KeyEvent.VK_BACK_SLASH); }
                else if (actionCommand.equals(";")) { Helper.typeKey(KeyEvent.VK_SEMICOLON); }
                else if (actionCommand.equals(":")) { Helper.shiftKey(KeyEvent.VK_SEMICOLON); }
                else if (actionCommand.equals("'")) { Helper.typeKey(KeyEvent.VK_QUOTE); }
                else if (actionCommand.equals("\"")) { Helper.shiftKey(KeyEvent.VK_QUOTE); }
                else if (actionCommand.equals(",")) { Helper.typeKey(KeyEvent.VK_COMMA); }
                else if (actionCommand.equals("<")) { Helper.shiftKey(KeyEvent.VK_COMMA); }
                else if (actionCommand.equals(".")) { Helper.typeKey(KeyEvent.VK_PERIOD); }
                else if (actionCommand.equals(">")) { Helper.shiftKey(KeyEvent.VK_PERIOD); }
                else if (actionCommand.equals("/")) { Helper.typeKey(KeyEvent.VK_SLASH); }
                else if (actionCommand.equals("?")) { Helper.shiftKey(KeyEvent.VK_SLASH); }

                // Special case
                else if (actionCommand.equals("(")) { // Autocompletes ), then puts cursor between ( and )
                    if (!getCurrentMode().equals("normal")) {
                        Helper.typeBrackets();
                    } else {
                        Helper.shiftKey(KeyEvent.VK_9);
                    }
                }

                // Add sounds
                String soundPath = "";

                // Numbers
                try {
                    if (Integer.parseInt(actionCommand) >= 0 && Integer.parseInt(actionCommand) <= 9) {
                        soundPath = "/sounds/numbers/" + actionCommand + ".wav";
                    }
                } catch (NumberFormatException e) {
                    // Special cases
                    if (actionCommand.equals("*")) {
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/asterisk.wav";
                        } else {
                            soundPath = "/sounds/symbols/multiply.wav";
                        }
                    }

                    else if (actionCommand.equals("/")) {
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/forward slash.wav";
                        } else {
                            soundPath = "/sounds/symbols/divide.wav";
                        }
                    }

                    else if (actionCommand.equals("+")) {
                        // TODO: plus, positive
                        soundPath = "/sounds/symbols/plus.wav";
                    }

                    else if (actionCommand.equals("-")) {
                        // TODO: hyphen, subtract, negative
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/subtract.wav";
                        } else {
                            soundPath = "/sounds/symbols/hyphen.wav";
                        }
                    }

                    else if (actionCommand.equals("!")) {
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/factorial.wav";
                        } else {
                            soundPath = "/sounds/symbols/!.wav";
                        }
                    }

                    else if (actionCommand.equals("<")) {
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/left arrow.wav";
                        } else {
                            soundPath = "/sounds/symbols/smaller than.wav";
                        }
                    }

                    else if (actionCommand.equals(">")) {
                        if (!getCurrentMode().equals("normal")) {
                            soundPath = "/sounds/symbols/right arrow.wav";
                        } else {
                            soundPath = "/sounds/symbols/greater than.wav";
                        }
                    }

                    else if (actionCommand.equals(":")) { soundPath = "/sounds/symbols/colon.wav"; }
                    else if (actionCommand.equals("\\")) { soundPath = "/sounds/symbols/back slash.wav"; }
                    else if (actionCommand.equals("|")) { soundPath = "/sounds/symbols/pipe.wav"; }
                    else if (actionCommand.equals("?")) { soundPath = "/sounds/symbols/question mark.wav"; }
                    else if (actionCommand.equals("\"")) { soundPath = "/sounds/symbols/quotation mark.wav"; }
                    else { soundPath = "/sounds/symbols/" + actionCommand + ".wav"; }
                }
                playSound(soundPath);
            }
        };
        
        b.removeActionListener(numberSymbolActionListener);
        b.addActionListener(numberSymbolActionListener);
    }

    /**
     * Sets listeners for icons.
     * @param b the button to add the listener to.
     */
    private void addIconActionListener(JButton b) {
        iconActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = b.getName();
                if (name.equals("white backspace")) {
                    name = "backspace";
                }
                String soundPath = "/sounds/icons/" + name + ".wav";

                if (name.equals("backspace")) {
                    Helper.typeKey(KeyEvent.VK_BACK_SPACE);
                }

                else if (name.equals("space")) {
                    Helper.typeKey(KeyEvent.VK_SPACE);
                }

                else if (name.equals("enter")) {
                    if (!getCurrentMode().equals("normal")) { // Math and Greek Mode
                        Helper.typeKey(KeyEvent.VK_ENTER);
                        Helper.robot.keyPress(KeyEvent.VK_ALT);
                        Helper.typeKey(KeyEvent.VK_EQUALS);
                        Helper.robot.keyRelease(KeyEvent.VK_ALT);
                    } else { // Normal mode
                        Helper.typeKey(KeyEvent.VK_ENTER);
                    }
                }

                else if (name.equals("shift")) {
                    shiftClick = !shiftClick;
                    toggleLetterCase();
                }

                else if (name.equals("caps")) {
                    capsClick = !capsClick;
                    toggleLetterCase();
                }

                playSound(soundPath);
            }
        };

        b.removeActionListener(iconActionListener);
        b.addActionListener(iconActionListener);
    }

    /**
     * Sets listeners for Equation Editor symbols.
     * @param b the button to add the listener to.
     */
    private void addEquationActionListener(JButton b) {
        iconActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = b.getName();
                String soundPath = "/sounds/equation/" + name + ".wav";

                if (name.equals("sqrt") || name.equals("cbrt")) {
                    Helper.typeEquationEditor(b.getName(), true);
                } else {
                    Helper.typeEquationEditor(b.getName(), false);
                    if (name.equals("dot") || name.equals("bar")) {
                        Helper.typeKey(KeyEvent.VK_SPACE);
                    }
                }

                playSound(soundPath);
            }
        };

        b.removeActionListener(equationActionListener);
        b.addActionListener(equationActionListener);
    }
    
    /**
     * Loads keys' appropriate listeners.
     */
    private void loadKeyListeners() {
        // Typing listeners
        for (Row[] rowGroup : rows.values()) {
            for (Row row : rowGroup) {
                for (JButton key : row.keys) {
                    addKeyHighlightAdapter(key);
    
                    if (Arrays.asList(iconURLs).contains(key.getName())) {
                        addIconActionListener(key);
                    } else if (key.getName().length() == 1){
                        char letter = key.getName().charAt(0);
                        if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) {
                            addLetterActionListener(key);
                        } else {
                            addNumberSymbolActionListener(key);
                        }
                    } else {
                        addEquationActionListener(key);
                    }
                }
            }
        }
    }
    
    /**
     * Scales icon sizes.
     */
    private void scaleKeyIcons() {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new ImageIcon(getClass().getResource(iconURLs[i]));
            Image temp = icons[i].getImage();    
            int tempWidth = (int)(temp.getWidth(null)*(double)(width/DEFAULT_WIDTH));
            int tempHeight = (int)(temp.getHeight(null)*(double)(height/DEFAULT_HEIGHT));
            int scale = 7;
            if (iconURLs[i].equals("/icons/enter.png") || iconURLs[i].equals("/icons/shift.png")) {
                scale = 20;
            } else if (iconURLs[i].equals("/icons/caps.png")) {
                scale = 5;
            } else {
                scale = 7;
            }
            temp = temp.getScaledInstance((int)(tempWidth/scale), (int)(tempHeight/scale), Image.SCALE_SMOOTH);
            icons[i].setImage(temp);
        }
    }

    /**
     * Loads keys' appropriate letters or icons.
     */
    private void loadKeyIcons() {
        Key.keymaps = new HashMap<String, ArrayList<ArrayList<String>>>();
        
        Pattern pattern = Pattern.compile("((?:.(?!\\/))+)(?=\\.)");
        for (String keymapURL : keymapURLs) {
            Matcher matcher = pattern.matcher(keymapURL);
            if (matcher.find()) {
                String name = matcher.group(0).substring(1);
                System.out.println("Mapping for " + name + ":");
                Key.keymaps.put(name, getKeyMapping(keymapURL));
                System.out.println();
            }
        }
        
        for (String keymap : Key.keymaps.keySet()) {
            ArrayList<ArrayList<String>> keymapRows = Key.keymaps.get(keymap);
            Row currRows[] = new Row[ROW_COUNT];
            
            int rowIndex = 0;
            for (ArrayList<String> keymapRow : keymapRows) {
                if (rowIndex <= 5) {
                    currRows[rowIndex] = new Row(rowIndex + 1);
                } else {
                    currRows[rowIndex] = new Row(6 - rowIndex);
                }
                currRows[rowIndex].keys = new ArrayList<JButton>();
                
                for (String item : keymapRow) {
                    String name = item;
                    String url = "/icons/" + name + ".png";
                    if (url.equals("/icons/backspace.png") && backgroundPath != "/backgrounds/wbg.png") {
                        name = "white backspace";
                        url = "/icons/" + name + ".png";
                    }
                    
                    // TODO: All equation editor symbols
                    if (!Arrays.asList(iconURLs).contains(url)) {
                        if (name.length() == 1) { // Any item on a physical keyboard
                            currRows[rowIndex].keys.add(new JButton(name));
                        } else {
                            currRows[rowIndex].keys.add(new JButton(name));
                        }
                    } else {
                        int index = Arrays.asList(iconURLs).indexOf(url);
                        currRows[rowIndex].keys.add(new JButton(icons[index]));
                    }
                    currRows[rowIndex].keys.get(currRows[rowIndex].keys.size() - 1).setName(name);
                }
                rowIndex++;
            }
            
            rows.put(keymap, currRows);
        }
    }
    
    /**
     * Loads keys' appropriate positions.
     */
    private void scaleKeyPositions() {
        // Right side
        Row currRows[] = rows.get(getCurrentMode());
        
        currRows[0].radius = -10;
        currRows[0].initDegree = 0;
        currRows[0].endDegree = 0;
        for (int i = 1; i <= 5; i++) {
            currRows[i].radius = width/10 + 30*(i - 1) + 5*currScaleCount*(i - 1);
            currRows[i].initDegree = 52;
            currRows[i].endDegree = 308;
        }
        System.out.println("MidX: " + currRows[0].midX);
        System.out.println("MidY: " + currRows[0].midY);

        // Left side
        currRows[6].radius = 45;
        currRows[6].initDegree = 0;
        currRows[6].endDegree = 0;
        for (int i = 7; i <= 8; i++) {
            currRows[i].radius = width/4 - 15 + 60*(i - 7);
            currRows[i].initDegree = -25;
            currRows[i].endDegree = 25;
        }

        // Place keys
        for (Row row : currRows) {
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

    // Mode variables
    
    private String[] modes = {
            "Normal Mode",
            "Math Mode",
            "Greek Mode"
    };
    private int modeIndex = 0;
    private JButton modeButton = new JButton(modes[modeIndex]);

    /**
     * Loads Mode button.
     */
    private void loadModeButton() {
        modeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeIndex = (modeIndex + 1) % 3;
                modeButton.setText(modes[modeIndex]);
                playSound("/sounds/buttons/" + modes[modeIndex].toLowerCase()+ ".wav");
                System.out.println("Switching to " + getCurrentMode());
                loadGUI();
            }
        });
    }

    /**
     * Scales Mode button.
     */
    private void scaleModeButton() {
        int modeButtonWidth = 110 + currScaleCount*20;
        int modeButtonHeight = 30 + currScaleCount*5;
        
        modeButton.setBounds(0, 0, modeButtonWidth, modeButtonHeight);
        modeButton.setFont(new Font("Arial", Font.BOLD, (int)(14*getWidth()/500.0)));
    }

    /**
     * Get the current mode in lower case without "Mode".
     * @return the current mode.
     */
    private String getCurrentMode() {
        return modeButton.getText().split(" ")[0].toLowerCase();
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
    
    // Change Background variables
    
    private JButton changeBackgroundButton = new JButton("Change BG");
    private int backgroundIndex = 0;
    private String[] backgroundFilePaths = {
            "/backgrounds/bbg_alt.png",
            "/backgrounds/bbg.png",
            "/backgrounds/wbg_alt.png",
            "/backgrounds/wbg.png"
    };

    /**
     * Loads Change Background button.
     */
    private void loadChangeBackgroundButton() {
        changeBackgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backgroundIndex = (backgroundIndex + 1) % backgroundFilePaths.length;
                backgroundPath = backgroundFilePaths[backgroundIndex];
                Image temp = new ImageIcon(getClass().getResource(backgroundPath)).getImage();
                background.setImage(Helper.getScaledImage(temp, width - 50, width - 50));
                label.revalidate();
                label.repaint();
                playSound("/sounds/buttons/change background.wav");
                
                Row currRows[] = rows.get(getCurrentMode());
                // If white bg, set keys to black
                if (backgroundPath.equals("/backgrounds/wbg.png")
                        || backgroundPath.equals("/backgrounds/wbg_alt.png")) {                    
                    // Right side
                    for (int i = 0; i <= 5; i++) {
                        for (JButton key : currRows[i].keys) {
                            key.setForeground(Color.BLACK);
                        }
                    }

                    // Left side
                    for (int i = 6; i <= 8; i++) {
                        for (JButton key : currRows[i].keys) {
                            key.setForeground(Color.BLACK);
                        }
                    }

                    for (Row row : currRows) {
                        for (JButton key : row.keys) {
                            if (key.getName().equals("white backspace")) {
                                String newName = "backspace";
                                String newURL = "/icons/" + newName + ".png";
                                int index = Arrays.asList(iconURLs).indexOf(newURL);
                                key.setIcon(icons[index]);
                                key.setName(newName);
                            }
                        }
                    }
                } else {
                    // Right side
                    for (int i = 0; i <= 5; i++) {
                        for (JButton key : currRows[i].keys) {
                            key.setForeground(Color.WHITE);
                        }
                    }

                    // Left side
                    for (int i = 6; i <= 8; i++) {
                        for (JButton key : currRows[i].keys) {
                            key.setForeground(Color.BLACK);
                        }
                    }

                    for (Row row : currRows) {
                        for (JButton key : row.keys) {
                            if (key.getName().equals("backspace")) {
                                String newName = "white backspace";
                                String newURL = "/icons/" + newName + ".png";
                                int index = Arrays.asList(iconURLs).indexOf(newURL);
                                key.setIcon(icons[index]);
                                key.setName(newName);
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * Scales Change Background button.
     */
    private void scaleChangeBackgroundButton() {
        int changeBackgroundButtonWidth = 110 + currScaleCount*20;
        int changeBackgroundButtonHeight = 30 + currScaleCount*5;

        changeBackgroundButton.setBounds((int)(width*0.96 - changeBackgroundButtonWidth), 0, changeBackgroundButtonWidth, changeBackgroundButtonHeight);
        changeBackgroundButton.setFont(new Font("Arial", Font.BOLD, (int)(14*getWidth()/500.0)));
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
                            
                            for (Row[] currRows : rows.values()) {
                                for (Row row : currRows) {
                                    row.midX = width/2 - 22;
                                    row.midY = height/2 - 40;
                                }
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
                            
                            for (Row[] currRows : rows.values()) {
                                for (Row row : currRows) {
                                    row.midX = width/2 - 22;
                                    row.midY = height/2 - 40;
                                }
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
        scaleChangeBackgroundButton();
        scaleModeButton();
    }
}
