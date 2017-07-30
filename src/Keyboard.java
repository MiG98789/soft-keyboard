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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Keyboard{
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private ImageIcon background = new ImageIcon(getClass().getResource("/bg.png"));
	private ImageIcon backspaceIcon = new ImageIcon(getClass().getResource("/backspace.png"));
	private ImageIcon spaceIcon = new ImageIcon(getClass().getResource("/backspace.png"));
	private ImageIcon enterIcon = new ImageIcon(getClass().getResource("/backspace.png"));

	private JToggleButton mathMode = new JToggleButton("Normal Mode", false);
	private JButton[] specialButtons = new JButton[6]; // Backspace, space, enter, \, =, (
	private JButton[] arithmeticButtons = new JButton[5];
	private JButton[] numberButtons = new JButton[10];
	private JButton[] letterButtons = new JButton[26];

	private boolean shiftClick = false;
	private boolean capsClick = false;
	
	private Vector<String> mathSymbols = new Vector<String>();

	private final int BUTTON_WIDTH = 25;	// Button width
	private final int BUTTON_HEIGHT = 25;	// Button height
	private final int FRAME_WIDTH = 450;	// Frame width
	private final int FRAME_HEIGHT = 420;	// Frame height

	/* Switches between math mode and typing mode */
	private ItemListener modeToggle = new ItemListener() {
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
	};
	
	/* Converts Soft Keyboard key presses into actual keyboard presses */
	private ActionListener typing = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();

			try {
				Robot robot = new Robot();
				
				// Numbers
				if(actionCommand == "0") {robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);}
				else if(actionCommand == "1") {robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);}
				else if(actionCommand == "2") {robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);}
				else if(actionCommand == "3") {robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);}
				else if(actionCommand == "4") {robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);}
				else if(actionCommand == "5") {robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);}
				else if(actionCommand == "6") {robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);}
				else if(actionCommand == "7") {robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);}
				else if(actionCommand == "8") {robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);}
				else if(actionCommand == "9") {robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);}

				// Symbols
				else if(actionCommand == "`") {robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);}
				else if(actionCommand == "~") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "!") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "@") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "#") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "$") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "%") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "^") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "&") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else if(actionCommand == "*") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				// SPECIAL CASE: Autocompletes ), then puts cursor between ( and )
				else if(actionCommand == "(") {
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

				else if(actionCommand == "-") {robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);}
				else if(actionCommand == "_") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "=") {robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);}
				else if(actionCommand == "+") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "[") {robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);}
				else if(actionCommand == "{") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "]") {robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);}
				else if(actionCommand == "}") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "\\") {robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);}
				else if(actionCommand == "|") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == ";") {robot.keyPress(KeyEvent.VK_SEMICOLON);robot.keyRelease(KeyEvent.VK_SEMICOLON);}
				else if(actionCommand == ":") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_COLON);robot.keyRelease(KeyEvent.VK_COLON);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "'") {robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);}
				else if(actionCommand == "\"") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == ",") {robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);}
				else if(actionCommand == "<") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == ".") {robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);}
				else if(actionCommand == ">") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}

				else if(actionCommand == "/") {robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);}
				else if(actionCommand == "?") {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	};

	/* Loads special math symbols and stores them in a String Vector */
	private void loadSymbols() {
		try {
			String path = System.getProperty("user.dir") + "/res/symbols.txt"; 

			File file = new File(path);
			if(file.exists()){
				System.out.println("symbols.txt file exists");
			}
			else{
				System.out.println("symbols.txt does not exist");
			}
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
//				stringBuffer.append(line);
//				stringBuffer.append("\n");
				mathSymbols.add(line);
			}
			fileReader.close();
			System.out.println("Contents of file:");
//			System.out.println(stringBuffer.toString());
			for(int i = 0; i < mathSymbols.size(); i++) {
				System.out.println(mathSymbols.elementAt(i).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void presort(){
		
		
	}
	
	
	/* Sets up background */
	private void backgroundInit() {
		// Frame
		frame = new JFrame("Soft Keyboard");
		frame.pack();
		frame.setVisible(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);

		frame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame.setFocusableWindowState(false);
			}
		});

		// Panel
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(null);
		panel.setLayout(new BorderLayout());
		
		// Label
		label = new JLabel(background, JLabel.CENTER);
//		label.setBounds(50,  50, frame.getWidth() - 100,  (int)((frame.getWidth() - 100) * 1.18));
		label.setBackground(Color.DARK_GRAY);
		label.repaint();
		label.revalidate();

		/*Image imagey = background.getImage();
				Image imagea = imagey.getScaledInstance(frame.getWidth() - 100,  
						(int)((frame.getWidth() - 100) * 1.18), java.awt.Image.SCALE_SMOOTH);
				background = new ImageIcon(imagea);*/
		
//		label.setIcon(background);
//		label.revalidate();
//		label.repaint();
	}

	/* Set up left buttons */
	private void specialInit() {
		specialButtons[0] = new JButton(backspaceIcon);
		Image temp = backspaceIcon.getImage();	
		int tempWidth = temp.getWidth(null);
		int tempHeight = temp.getHeight(null);
		temp = temp.getScaledInstance((int)(tempWidth / 7 * (double)(frame.getWidth() / 450.0)), 
										(int)(tempHeight / 7 * (double)(frame.getWidth() / 450.0)), Image.SCALE_SMOOTH);
		backspaceIcon.setImage(temp);
		
		specialButtons[1] = new JButton(spaceIcon);
		specialButtons[2] = new JButton(enterIcon);
		specialButtons[3] = new JButton("\\");
		specialButtons[4] = new JButton("=");
		specialButtons[5] = new JButton("(");
		
		for(int i = 0; i < 6; i++) {
			specialButtons[i].setBorder(null);
			specialButtons[i].setBorderPainted(false);
			specialButtons[i].setContentAreaFilled(false);
			specialButtons[i].setOpaque(false);
			
			// Changes button appearance based on cursor
			final Integer x = new Integer(i);
			specialButtons[x].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					specialButtons[x].setBackground(Color.PINK);
					specialButtons[x].setContentAreaFilled(true);
				}

				public void mouseExited(MouseEvent e) {
					specialButtons[x].setBackground(null);
					specialButtons[x].setContentAreaFilled(false);
				}
			});

			if(i < 3) { // Backspace, space, enter
				specialButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae){
						try {
							Robot robot = new Robot();
							
							switch(x) {
							case 0: // Backspace
								robot.keyPress(KeyEvent.VK_BACK_SPACE); robot.keyRelease(KeyEvent.VK_BACK_SPACE);
								break;
							case 1: // Space
								robot.keyPress(KeyEvent.VK_SPACE); robot.keyRelease(KeyEvent.VK_SPACE);
								break;
							case 2: // Enter
								robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
								break;
							}
							
						} catch(AWTException e) {
							e.printStackTrace();
						}
					}
				});
			}
			else { // \, =, (
				specialButtons[i].removeActionListener(typing);
				specialButtons[i].addActionListener(typing);
				specialButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(frame.getWidth() / 500.0))));
			}
		}
		
		int xValue, yValue;
		
		// Backspace
		xValue = (int)(frame.getWidth() / 2) - 10;
		yValue = (int)(frame.getHeight() / 2) - 35;
		specialButtons[0].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// Space
		xValue = (int)(frame.getWidth());
		yValue = (int)(frame.getHeight());
		specialButtons[1].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// Enter
		xValue = (int)(frame.getWidth());
		yValue = (int)(frame.getHeight());
		specialButtons[2].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// \
		xValue = (int)(frame.getWidth() / 2) - 55;
		yValue = (int)(frame.getHeight() / 2) - 35;
		specialButtons[3].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);

		
		// =
		xValue = (int)(frame.getWidth());
		yValue = (int)(frame.getHeight());
		specialButtons[4].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// (
		xValue = (int)(frame.getWidth());
		yValue = (int)(frame.getHeight());
		specialButtons[5].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
	}
	
	/* Sets up letter buttons */
	private void letterInit() {
		letterButtons[0] = new JButton("a");
		letterButtons[1] = new JButton("b");
		letterButtons[2] = new JButton("c");
		letterButtons[3] = new JButton("d");
		letterButtons[4] = new JButton("e");
		letterButtons[5] = new JButton("f");
		letterButtons[6] = new JButton("g");
		letterButtons[7] = new JButton("h");
		letterButtons[8] = new JButton("i");
		letterButtons[9] = new JButton("j");
		letterButtons[10] = new JButton("k");
		letterButtons[11] = new JButton("l");
		letterButtons[12] = new JButton("m");
		letterButtons[13] = new JButton("n");
		letterButtons[14] = new JButton("o");
		letterButtons[15] = new JButton("p");
		letterButtons[16] = new JButton("q");
		letterButtons[17] = new JButton("r");
		letterButtons[18] = new JButton("s");
		letterButtons[19] = new JButton("t");
		letterButtons[20] = new JButton("u");
		letterButtons[21] = new JButton("v");
		letterButtons[22] = new JButton("w");
		letterButtons[23] = new JButton("x");
		letterButtons[24] = new JButton("y");
		letterButtons[25] = new JButton("z");
		

		
		double radian;
		int xValue, yValue;
		double initDegree = 50;
		double incrementDegree = 10.3;


		radian = Math.toRadians(initDegree);
		int midX = (int)((frame.getWidth()) / 2) - 20;
		int midY = (int)((frame.getHeight()) / 2) - 35;
		int radius = (int)((frame.getWidth()) / 2.5) - 13;
		
		for(int i = 0; i < 26; i++) {
			char temp = (char)('a' + i);
//			letterButtons[i] = new JButton(Character.toString(temp));
			char lowercase = (char)('a' + i);
			String lowerTemp = Character.toString(lowercase);
			char uppercase = (char)('A' + i);
			String upperTemp = Character.toString(uppercase);

			letterButtons[i].setBorder(null);
			letterButtons[i].setBorderPainted(false);
			letterButtons[i].setContentAreaFilled(false);
			letterButtons[i].setOpaque(false);

			// Calculates coordinates of each letter
			radian = Math.toRadians(initDegree);
			xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
			yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
			initDegree += incrementDegree;

			
			letterButtons[i].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
			letterButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(frame.getWidth() / 500.0))));
			letterButtons[i].setForeground(Color.WHITE);

			letterButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					try {
						Robot robot = new Robot();
						
						if(!shiftClick && !capsClick) { // Lower case
							int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(lowercase));
							robot.keyPress(keyCode);robot.keyRelease(keyCode);
						} 
						else if (!shiftClick && capsClick) { // Upper case
							int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(uppercase));
							robot.keyPress(KeyEvent.VK_SHIFT);
							robot.keyPress(keyCode);robot.keyRelease(keyCode);
							robot.keyRelease(KeyEvent.VK_SHIFT);
						} 
						else if(shiftClick && !capsClick) { // Upper case
							int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(uppercase));
							robot.keyPress(KeyEvent.VK_SHIFT);
							robot.keyPress(keyCode);robot.keyRelease(keyCode);
							robot.keyRelease(KeyEvent.VK_SHIFT);
							shiftClick = false;
						}
						else { // Lower case
							int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)(lowercase));
							robot.keyPress(keyCode);robot.keyRelease(keyCode);
							shiftClick = false;
						}
					} catch(AWTException e) {
						e.printStackTrace();
					}
				}
			});
			
			// Changes button appearance based on cursor
			final Integer x = new Integer(i);
			letterButtons[x].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					letterButtons[x].setBackground(Color.PINK);
					letterButtons[x].setContentAreaFilled(true);
				}

				public void mouseExited(MouseEvent e) {
					letterButtons[x].setBackground(null);
					letterButtons[x].setContentAreaFilled(false);
				}
			});
		}
	}

	/* Sets up number buttons */
	private void numberInit() {
		numberButtons[0] = new JButton("0");
		numberButtons[1] = new JButton("1");
		numberButtons[2] = new JButton("2");
		numberButtons[3] = new JButton("3");
		numberButtons[4] = new JButton("4");
		numberButtons[5] = new JButton("5");
		numberButtons[6] = new JButton("6");
		numberButtons[7] = new JButton("7");
		numberButtons[8] = new JButton("8");
		numberButtons[9] = new JButton("9");

		double radian;
		int xValue, yValue;
		
		double initDegree = 55;
		double incrementDegree = 28;
		int midX = (int)((frame.getWidth()) / 2) - 20;
		int midY = (int)((frame.getHeight()) / 2) - 32;
		int radius = (int)((frame.getWidth()) / 6);

		for(int i = 0; i < 10; i++) {
			numberButtons[i].setBorder(null);
			numberButtons[i].setBorderPainted(false);
			numberButtons[i].setContentAreaFilled(false);
			numberButtons[i].setOpaque(false);

			// Calculates coordinates of each number
			radian = Math.toRadians(initDegree);
			xValue = -1 * (int)(Math.cos(radian)*radius) + midX;
			yValue = -1 * (int)(Math.sin(radian)*radius) + midY;
			//xValue = (int)(radius * (Math.sin(radian * (i - 6))));
			//yValue = (int)(Math.sqrt(Math.pow(radius, 2) - Math.pow(xValue, 2))+midY);
			//					if(i < 5) {
			//						xValue *= -1;
			//					}
			initDegree += incrementDegree;
			numberButtons[i].setBounds(xValue, yValue, (int)(BUTTON_WIDTH * 0.8), (int)(BUTTON_HEIGHT * 0.8));
			numberButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(frame.getWidth() / 500.0))));
			numberButtons[i].setForeground(Color.WHITE);

			// Changes button appearance based on cursor
			final Integer x = new Integer(i);
			numberButtons[x].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					numberButtons[x].setBackground(Color.PINK);
					numberButtons[x].setContentAreaFilled(true);
				}

				public void mouseExited(MouseEvent e) {
					numberButtons[x].setBackground(null);
					numberButtons[x].setContentAreaFilled(false);
				}
			});
		}
	}




	private void loadGui() {
		// Background
		backgroundInit();
		
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				System.out.println("Pressed");
				int i = 0;
				for(;i<mathSymbols.size();i++){
					if(e.getKeyChar()==mathSymbols.get(i).charAt(1)){
						System.out.println(mathSymbols.get(i));
					}
				}
			}
		}); 
		
		
		// Special symbols
		specialInit();
		for(int i = 0; i < 6; i++) {
			panel.add(specialButtons[i]);
		}
		
		// Arithmetic symbols
		
		// Numbers
		numberInit();
		for(int i = 0; i < 10; i++) {
			numberButtons[i].removeActionListener(typing);
			numberButtons[i].addActionListener(typing);
			panel.add(numberButtons[i]);
		}
		
		// Letters
		letterInit();
		for(int i = 0; i < 26; i++) {
			letterButtons[i].removeActionListener(typing);
			letterButtons[i].addActionListener(typing);
			panel.add(letterButtons[i]);
		}
		
		// Add mode toggle button
		mathMode.setBounds(0,0, 110,30);
		mathMode.setFont(new Font("Arial", Font.PLAIN, 12));
		mathMode.addItemListener(modeToggle);
		panel.add(mathMode);
		
		panel.add(label);
		frame.add(panel);

		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}

	public Keyboard() {
		loadSymbols();
		loadGui();
	}

	public static void main(String[] args) {
		new Keyboard();
	}


}
