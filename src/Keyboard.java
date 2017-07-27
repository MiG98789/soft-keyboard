import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Keyboard {
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private ImageIcon image;
	ImageIcon background;

	private JButton space, enter, delete;
	JButton[] numberButtons = new JButton[10];
	JButton[] letterButtons = new JButton[26];

	boolean shiftClick = false;
	boolean capsClick = false;

	final int BUTTON_WIDTH = 20;      // Button width
	final int BUTTON_HEIGHT = 20;      // Button height
	final int FRAME_WIDTH = 450;       // Frame width
	final int FRAME_HEIGHT = 420;      // Frame height


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

				// Letters

			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	};

	public void background() {
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
		label = new JLabel();
		label.setBounds(50,  50, frame.getWidth() - 100,  (int)((frame.getWidth() - 100) * 1.18));
		label.setBackground(Color.DARK_GRAY);
		label.repaint();
		label.revalidate();

		// Background image
		background = new ImageIcon(getClass().getResource("/bg.png"));
		/*Image imagey = background.getImage();
				Image imagea = imagey.getScaledInstance(frame.getWidth() - 100,  
						(int)((frame.getWidth() - 100) * 1.18), java.awt.Image.SCALE_SMOOTH);
				background = new ImageIcon(imagea);*/
	}

	public void letters() {
		for(int i = 0; i < 26; i++) {
			char temp = (char)('a' + i);
			letterButtons[i] = new JButton(Character.toString(temp));
			char lowercase = (char)('a' + i);
			String lowerTemp = Character.toString(lowercase);
			char uppercase = (char)('A' + i);
			String upperTemp = Character.toString(uppercase);

			letterButtons[i].setBorder(null);
			letterButtons[i].setBorderPainted(false);
			letterButtons[i].setContentAreaFilled(false);
			letterButtons[i].setOpaque(false);

			double radian = Math.toRadians(9.5);
			int midX = (int)((frame.getWidth() - 100) / 2.8 + 50);
			int midY = (int)((frame.getWidth() - 100) / 2.8 * 1.5 + 50);
			int radius = (int)((frame.getWidth() - 100) / 2.8 * 1.5);

			int xValue = (int)(radius * (Math.sin(radian * (i - 3))));
			int yValue = (int)(Math.sqrt(Math.pow(radius,  2) - Math.pow(xValue,  2)));
			if(i < 13) {
				yValue = -1 * yValue;
			}
			xValue += midX;
			yValue += midY;
			letterButtons[i].setBounds(xValue, yValue, BUTTON_WIDTH, BUTTON_HEIGHT);
			letterButtons[i].setFont(new Font("Arial", Font.PLAIN, 30));

			letterButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					if(!shiftClick && !capsClick) {

					}
				}

			});
		}
	}

	public void numbers() {
		// Numbers
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

		double radian = Math.toRadians(45);
		double initDegree = 55;
		int midX, midY, radius, xValue, yValue;

		for(int i = 0; i < 10; i++) {
			numberButtons[i].setBorder(null);
			numberButtons[i].setBorderPainted(false);
			numberButtons[i].setContentAreaFilled(false);
			numberButtons[i].setOpaque(false);
			radian = Math.toRadians(initDegree);

			midX = (int)((frame.getWidth())/2)-35;
			midY = (int)((frame.getWidth())/2)-45;
			radius = (int)((frame.getWidth())/6);
			//xValue = (int)(radius * (Math.sin(radian * (i - 6))));
			xValue = (int)(Math.cos(radian)*radius);
			yValue = (int)(Math.sin(radian)*radius);
			//yValue = (int)(Math.sqrt(Math.pow(radius, 2) - Math.pow(xValue, 2))+midY);
			//					if(i < 5) {
			//						xValue *= -1;
			//					}

			xValue *= -1;
			yValue *= -1;

			initDegree += 28;
			xValue += midX;
			yValue += midY;
			numberButtons[i].setBounds(xValue, yValue, (int)(BUTTON_WIDTH * 0.8), (int)(BUTTON_HEIGHT * 0.8));
			numberButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(frame.getWidth() / 500.0))));
			numberButtons[i].setForeground(Color.WHITE);

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

	public void gui() {
		// Background
		background();

		// Letters

		// Numbers
		numbers();

		// Display
		label.setIcon(background);
		label.revalidate();
		label.repaint();

		for(int i = 0; i < 10; i++) {
			numberButtons[i].removeActionListener(typing);
			numberButtons[i].addActionListener(typing);
			panel.add(numberButtons[i]);
		}
		panel.add(label);

		frame.add(panel);

		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}

	public Keyboard() {
		gui();
	}

	public static void main(String[] args) {
		new Keyboard();
	}
}
